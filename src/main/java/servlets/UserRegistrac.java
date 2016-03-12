package servlets;

import classes.Factory;
import classes.Mail;
import classes.User;
import org.apache.commons.codec.digest.DigestUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/UserRegistrac")
public class UserRegistrac extends HttpServlet
{
    @Override
    synchronized protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            inspectParamForRegistr(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    synchronized protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try {
            inspectParamForRegistr(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inspectParamForRegistr(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        String mail = req.getParameter("mail");
        PrintWriter out;

        //proverka sushestvovaniya parol/login/mail v base
        if(proverkaMail(mail)){
            out = resp.getWriter();
            out.print("mailIs");
        }
        else if ((mail.equals("") || mail.equals(null))) {
            out = resp.getWriter();
            out.print("mail");
        }
        else if (!testMail(mail)) {
            out = resp.getWriter();
            out.print("netTakoyiPochty");
        }
        else if (proverkaExistanceMail(mail)) {
            out = resp.getWriter();
            out.print("notMail");
        }
        else if(proverkaLogin(login)){
            out = resp.getWriter();
            out.print("loginIs");
        }
        else if(login.equals("") || login.equals(null)){
            out = resp.getWriter();
            out.print("login");
        }
        else if(!test(login)) {
            out = resp.getWriter();
            out.print("anotherSymDlinna");
        }
        else if(password.equals("") || password.equals(null)){
            out = resp.getWriter();
            out.print("password");
        }
        else if(password.length() < 5){
            out = resp.getWriter();
            out.print("passwordDlinna");
        }
        else if(password.equals(login)) {
            out = resp.getWriter();
            out.print("passwordLogin");
        }
        else if(password2.equals("") || password2.equals(null)) {
            out = resp.getWriter();
            out.print("password2");
        }
        else if(!password.equals(password2)) {
            out = resp.getWriter();
            out.print("passwordPass2");
        }else{UserMail.registrUserMail(req);}
    }

    public static boolean proverkaExistanceMail(String mail) throws SQLException {
        boolean pr = false;
        Map<String,String> map = new HashMap<String, String>();
        map = new Mail().selectDomain(mail);
        if(map == null || map.size()==0){
            pr = true;
        }
        return pr;
    }

    public static boolean proverkaMail(String mail) throws SQLException {
        boolean pr = false;
        User user;
        user = Factory.getInstance().getUserDAO().getUserByMail(mail);
        if (user!=null){
            pr = true;
        }
        return pr;
    }

    public static boolean proverkaLogin(String login) throws SQLException {
        boolean pr = false;
        User user;
        user = Factory.getInstance().getUserDAO().getUserByLogin(login);
        if (user!=null){
            pr = true;
        }
        return pr;
    }

    public static boolean proverkaPass(String password) throws SQLException {
        boolean pr = false;
        String vremPass = md5Apache(password);
        User user;
        user = Factory.getInstance().getUserDAO().getUserByPassword(vremPass);
        if (user!=null){
            pr = true;
        }
        return pr;
    }

    public static boolean test(String testString){
        Pattern p = Pattern.compile("^[A-Za-z0-9_-]{3,30}$");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    public static boolean testMail(String testString){
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    //shifr parolya
    public static String md5Apache(String st) {
        String md5Hex = DigestUtils.md5Hex(st);
        return md5Hex;
    }
}
