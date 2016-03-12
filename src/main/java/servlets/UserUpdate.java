package servlets;

import classes.Factory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/UserUpdate")
public class UserUpdate extends HttpServlet
{
    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            update(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String login = req.getParameter("login");
        String passw = req.getParameter("password");
        String passw1 = req.getParameter("password1");
        String idU = req.getParameter("idU");
        PrintWriter out;

        if(UserRegistrac.proverkaLogin(login)){
            out = resp.getWriter();
            out.print("loginIs");
        }
        else if(!UserRegistrac.test(login) && !(login.equals(""))) {
            out = resp.getWriter();
            out.print("anotherSymDlinna");
        }
        else if(passw.length() >= 1 && passw.length() < 5){
            out = resp.getWriter();
            out.print("passwordDlinna");
        }
        else if (passw.equals(login) && !(passw.equals(""))) {
            out = resp.getWriter();
            out.print("passwordLogin");
        }
        else if(!passw.equals(passw1)) {
            out = resp.getWriter();
            out.print("neEq");
        }
        else if (login.equals("") && passw.equals("")) {
            out = resp.getWriter();
            out.print("pusto");
        }
        else
        {
            if (login.equals("")) {
                String password = UserRegistrac.md5Apache(passw);
                Factory.getInstance().getUserDAO().updateUserPassword(password,idU);
                req.setAttribute("mail", UserData.vuborkaMailPoId(Integer.parseInt(idU)));
                UserMail.updateUserMail(req,login,passw);
            }
            else if (passw.equals(""))
            {
                Factory.getInstance().getUserDAO().updateUserLogin(login, idU);
                req.setAttribute("mail",UserData.vuborkaMailPoId(Integer.parseInt(idU)));
                UserMail.updateUserMail(req,login,passw);
            }
            else{
                String password = UserRegistrac.md5Apache(passw);
                Factory.getInstance().getUserDAO().updateUserLoginPassword(login,password,idU);
                req.setAttribute("mail",UserData.vuborkaMailPoId(Integer.parseInt(idU)));
                UserMail.updateUserMail(req,login,passw);
            }
        }
    }
}
