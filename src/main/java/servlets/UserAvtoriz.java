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

@WebServlet("/UserAvtoriz")
public class UserAvtoriz extends HttpServlet {
    @Override
    synchronized protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            avtorizaciya(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    synchronized protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            avtorizaciya(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void avtorizaciya(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String login = req.getParameter("login");
        String passw = req.getParameter("password");
        PrintWriter out;

        String password = UserRegistrac.md5Apache(passw);

        //proverka na pustye polya
        if ((login.equals("") || login.equals(null))) {
            out = resp.getWriter();
            out.print("pustoiLogin");
        }else if(password.equals("") || password.equals(null)){
            out = resp.getWriter();
            out.print("pustoiPassword");
        }
        else
        {
            User user;
            user = Factory.getInstance().getUserDAO().getUserByLogin(login);
            //proverka na otsutstvie takogo user
            if (user==null){
                out = resp.getWriter();
                out.print("true");
            }
            else {
                if (password.equals(user.getPassword())) {
                    out = resp.getWriter();
                    out.print(user.getIdUsers() + "/" + user.getLogin());
                }else {
                    out = resp.getWriter();
                    out.print("true");
                }
            }
        }
    }
}
