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
import java.util.Map;

@WebServlet("/UserDelete")
public class UserDelete extends HttpServlet {
    synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            deletUser(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deletUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        int idU = Integer.parseInt(request.getParameter("idU"));
        String mail;
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        User user;

        user = Factory.getInstance().getUserDAO().getUserById(idU);
        mail = user.getMail();
        //inspect is copy ref.
        map = inspectIduIsNull(idU);

        // if 'no' delete user
        if (map==null || map.size()==0){
            // if it existance delete copy ref.
            Factory.getInstance().getRefDAO().updateReferIdUByIdU(idU);
            Factory.getInstance().getUserDAO().deleteUser(idU);
            UserMail.deleteUserMail(request, mail);
        }
        else {
            // if 'yes' update 'id_user' and delete user
            for (Map.Entry<Integer,Integer> entry:map.entrySet()){
                Factory.getInstance().getRefDAO().updateReferIdUByIdRef(entry.getValue(),entry.getKey());
            }
            Factory.getInstance().getUserDAO().deleteUser(idU);
            UserMail.deleteUserMail(request, mail);
        }
        response.sendRedirect("sokr.jsp");
    }

    private Map inspectIduIsNull(Integer idU) throws SQLException {
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        References references;
        references = Factory.getInstance().getRefDAO().getRefByUser(idU);
              if (references!=null){map.put(references.getIdRef(),references.getIdU());}
        return map;
    }
}

