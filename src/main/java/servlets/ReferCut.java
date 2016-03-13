package servlets;

import classes.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/ReferCut")
public class ReferCut extends HttpServlet {

    synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String ssylka = request.getParameter("ssylka");
        Integer id = Integer.parseInt(request.getParameter("idU"));
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        PrintWriter out;
        int idU = 0;
        int idR = 0;

        if ((ssylka.equals("") || ssylka.equals(null))) {
            out = response.getWriter();
            out.print("pustayaSsylka");
        }
        else if(!provURL(ssylka)) {
        out = response.getWriter();
        out.print("neCorr");
        }
        else
        {
            String sokr = sokrashtel();
            File file = QRCodeGenerator.qrGeneration(sokr);

            //proverka ili ref existance
            try {
                map = provRef(ssylka);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (map!=null) {
                for (Map.Entry entry : map.entrySet()) {
                    idR = (Integer) entry.getKey();
                    idU = (Integer) entry.getValue();
                }
                    if (idU != id) {
                        try {
                            insertCopyRef(request, response, ssylka, id, idR);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
            }else {
                try {
                    insertRefrence(request, response, ssylka, sokr, file, id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //proverka ili long ref existane
    public Map provRef(String ref) throws SQLException {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        References references;
        User user ;
        references = Factory.getInstance().getRefDAO().getRefByFullRef(ref);

       if (references==null){
            map = null;
        }
        else {
               user = references.getUser();
               map.put(references.getIdRef(),user.getIdUsers());
        }
        return map;
    }

    public String sokrashtel() {
    String  sokr = "cut-linki.rhcloud.com/by/";
    char symbol;
       while (sokr.length() < 31) {
            int chislo = (int) (Math.random() * 122);
            if ((chislo >= 0 && chislo <= 9) || (chislo >= 65 && chislo <= 90) || (chislo >= 97 && chislo <= 122)) {
                if (chislo >= 0 && chislo <= 9)             //dlya chisel
                {
                    sokr += String.valueOf(chislo);
                } else                                      //dlya vseh
                {
                    symbol = (char) chislo;
                    sokr += symbol;
                }
            }
        }
        return sokr;
    }

    public void insertRefrence(HttpServletRequest req, HttpServletResponse resp,String ssylka,String sokr, File file,int id) throws ServletException, IOException, SQLException {
        String login = req.getParameter("login");
        String desc = req.getParameter("description");
        String tag = req.getParameter("tag");
        PrintWriter out;
        User user;
        user = Factory.getInstance().getUserDAO().getUserById(id);
        Factory.getInstance().getRefDAO().saveRefer(user,ssylka,sokr,desc,tag,file);
        req.setAttribute("login", login);
        req.setAttribute("ref", sokr);
        int idUser = UserData.vuborkaIdU(req);
        out = resp.getWriter();
        out.print(idUser);
    }

    public void insertCopyRef(HttpServletRequest req, HttpServletResponse resp,String sokr,int id,int idR) throws IOException, SQLException {
        String login = req.getParameter("login");
        PrintWriter out;
        Factory.getInstance().getRefDAO().updateReferIdU(id,idR);
        req.setAttribute("login", login);
        req.setAttribute("ref", sokr);
        int idUser = UserData.vuborkaIdU(req);
        out = resp.getWriter();
        out.print(idUser);
    }
    public boolean provURL(String ssylka) {
        Pattern p = Pattern.compile(".*https://.*|.*http://.*");
        Matcher m = p.matcher(ssylka);
        return m.matches();
    }
}
