package servlets;

import classes.Factory;
import classes.References;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@WebServlet("/ReferQR")
public class ReferQR extends HttpServlet {

    synchronized protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        References references = null;
        try {
            references = Factory.getInstance().getRefDAO().getRefById(Integer.parseInt(request.getParameter("id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Blob qr = references.getQrcode();
        String filePath = "D:DIMA/IT/IPO/Diplom/DiplomHibernate/src/main/webapp/images/qr.png";
        try {
            byte[] blobBytes = qr.getBytes(1, (int) qr.length());
            FileOutputStream outputStream = new FileOutputStream(filePath);
            response.reset();
            response.setContentType("image/jpg");
            response.getOutputStream().write(blobBytes);
            response.getOutputStream().flush();
            outputStream.close();
            qr.free();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
