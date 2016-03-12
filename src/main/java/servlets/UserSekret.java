package servlets;

import classes.Factory;
import classes.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/UserSekret")
public class UserSekret extends HttpServlet
{
    synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            vspomnit(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void vspomnit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");
        PrintWriter out;

        if ((mail.equals("") || mail.equals(null)))
        {
            out = resp.getWriter();
            out.print("true");
        }
        else if (!UserRegistrac.testMail(mail)) {
            out = resp.getWriter();
            out.print("neKorrektno");
        }
        else if(!UserRegistrac.proverkaMail(mail)){
            out = resp.getWriter();
            out.print("mailNo");
        }
        else if(password.equals("") || password.equals(null)){
            out = resp.getWriter();
            out.print("password");
        }
        else if(UserRegistrac.proverkaPass(password)){
            out = resp.getWriter();
            out.print("passwordIs");
        }
        else if(loginEqPassw(mail,password)){
            out = resp.getWriter();
            out.print("loginEqPassw");
        }
        else if(password.length() < 5){
            out = resp.getWriter();
            out.print("passwordDlinna");
        }
        else
        {
            String login;
            String passw = UserRegistrac.md5Apache(password);
            Factory.getInstance().getUserDAO().updateUserPasswordForgot(passw,mail);
            User user;
            user = Factory.getInstance().getUserDAO().getUserByMail(mail);
            login = user.getLogin();
            UserMail.sekret(mail, login, password);
            out = resp.getWriter();
            out.print("proverMail");
        }
    }

    private boolean loginEqPassw(String mail,String password) throws SQLException {
        boolean pr = false;
        User user;
        user = Factory.getInstance().getUserDAO().getUserByMail(mail);

        if (password.equals(user.getLogin())){
            pr = true;
        }
        return pr;
    }
}
