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

@WebServlet("/by/*")
public class ReferRedirectOut extends HttpServlet
{
   @Override
    synchronized protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try {
           redirect(req, resp);
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

    @Override
    synchronized protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try {
            redirect(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String url = String.valueOf(req.getRequestURL().substring(7));
        References references;
        references = Factory.getInstance().getRefDAO().getRefByCutRef(url);

        if (references==null) {
            resp.sendRedirect("http://localhost:81/notFound.jsp");
        }
        else {
            resp.sendRedirect("http://localhost:81/ReferRedirect?id="+references.getIdRef());
        }
    }
}
