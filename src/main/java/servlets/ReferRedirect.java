package servlets;

import classes.Factory;
import classes.References;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

//servlet dlya perehoda po ssylke iznutri prilozeniya
@WebServlet("/ReferRedirect")
public class ReferRedirect extends HttpServlet
{
    synchronized protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            redirect(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        String id = request.getParameter("id");
        int id1 = Integer.parseInt(id);
        String p;
        Integer countNew;

        References references;
        references = Factory.getInstance().getRefDAO().getRefById(id1);
        response.sendRedirect(references.getFull_ref());
        String c = references.getCount();

        if (c==null){
            p="1";
        }
        else {
            countNew = Integer.parseInt(c);
            countNew++;
            p = Integer.toString(countNew);
        }
        Factory.getInstance().getRefDAO().updateReferCount(p,id1);
    }
}
