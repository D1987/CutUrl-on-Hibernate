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

@WebServlet("/UserFirstEnter")
public class UserFirstEnter extends HttpServlet {
    synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            insertUserRegistr(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    synchronized protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            insertUserRegistr(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertUserRegistr(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String mail = req.getParameter("mail");
        PrintWriter out;
        User user;
        user = Factory.getInstance().getUserDAO().userFirstEnterToPersonCabinet(login,password,mail);
        //get id user
        out = resp.getWriter();
        out.print(user.getIdUsers());
    }
}
