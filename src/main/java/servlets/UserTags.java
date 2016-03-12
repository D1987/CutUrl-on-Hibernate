package servlets;

import classes.Factory;
import classes.References;
import classes.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/UserTags")
public class UserTags extends HttpServlet
{
    @Override
    synchronized protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            tags(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    synchronized protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            tags(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void tags(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {
        Boolean where = Boolean.valueOf(req.getParameter("where"));
        String loginTags = req.getParameter("loginTags");
        String tag = req.getParameter("tag");
        Map<References,String> allRefTags = new HashMap<References, String>();
        List<References> listRef;
        User user;
        listRef = (List) Factory.getInstance().getRefDAO().getAllRef();

        for (References aListR : listRef) {
            if (aListR.getTag().equals(tag)) {
                user = aListR.getUser();
                allRefTags.put(aListR,user.getLogin());
            }
        }
             req.setAttribute("tags", allRefTags);
             req.setAttribute("tag", tag);

         //proverka na kakuyu stranicu otpravlyat
         if (where) {
             String login = req.getParameter("login");
             req.setAttribute("login",login);
             req.setAttribute("loginTags",loginTags);
             req.setAttribute("idU",req.getParameter("idU"));
             req.getRequestDispatcher("sokr.jsp").forward(req, resp);

         }else {
             req.setAttribute("login",loginTags);
             int id = UserData.vuborkaIdU(req);
             req.getRequestDispatcher("userCabinet.jsp?idU="+id).forward(req, resp);
         }
    }
}



