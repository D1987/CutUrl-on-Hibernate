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

@WebServlet("/ReferDelete")
public class ReferDelete extends HttpServlet
{
    synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            deleteRef(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    synchronized protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            deleteRef(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRef(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String id = String.valueOf(req.getParameter("id"));
        String idUser = String.valueOf(req.getParameter("idUser"));
        References references;
        references = whoseRef(Integer.parseInt(id));
        User user;
        user = references.getUser();
            //if owner ref
           if (user.getIdUsers()==Integer.parseInt(idUser)){
                if (references.getIdU()!=null){
                    user = Factory.getInstance().getUserDAO().getUserById(references.getIdU());
                    Factory.getInstance().getRefDAO().updateReferIdUser(user, Integer.parseInt(id)); //do settime to js
                }
                //if dont existence copy ref
                else {
                    Factory.getInstance().getRefDAO().deleteRef(Integer.parseInt(id));
                }
            }
            //if dont owner ref
            else {
               Factory.getInstance().getRefDAO().updateReferIdU(Integer.parseInt(id));   //do settime to js
           }
    }

    private References whoseRef(int id) throws SQLException {
        References references;
        references = Factory.getInstance().getRefDAO().getRefById(id);
        return references;
    }
}
