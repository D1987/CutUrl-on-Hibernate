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

@WebServlet("/ReferUpdate")
public class ReferUpdate extends HttpServlet {
    synchronized protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateRef(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateRef(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateRef(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String login = req.getParameter("login");
        int idR = Integer.parseInt(req.getParameter("idR"));
        String description = req.getParameter("description");
        String tag = req.getParameter("tag");
        PrintWriter out;

        if (description.equals("") && tag.equals(""))
        {
            out = resp.getWriter();
            out.print("pustyePolya");
        }
        else {
            if (description.equals("")) {
                Factory.getInstance().getRefDAO().updateReferTag(tag,idR);
                req.setAttribute("login", login);
                int idU = UserData.vuborkaIdU(req);
                out = resp.getWriter();
                out.print(idU);
            }
            else if (tag.equals(""))
            {
                Factory.getInstance().getRefDAO().updateReferDescription(description,idR);
                req.setAttribute("login", login);
                int idU = UserData.vuborkaIdU(req);
                out = resp.getWriter();
                out.print(idU);
            }
            else {
                Factory.getInstance().getRefDAO().updateReferTagDescription(tag,description,idR);
                req.setAttribute("login", login);
                int idU = UserData.vuborkaIdU(req);
                out = resp.getWriter();
                out.print(idU);
            }
        }
    }
}
