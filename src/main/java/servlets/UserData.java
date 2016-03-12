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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/UserData")
public class UserData extends HttpServlet {
    synchronized protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try {
            vuborkaPersonData(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map vuborkaPersonData(HttpServletRequest req) throws SQLException {
        String idU =  (String)req.getAttribute("id");
        Integer id = Integer.parseInt(idU);
        Map<Integer, References> map = new HashMap<Integer, References>();
        List<References> listRef;
        List<References> list;
        User user;

        listRef = (List) Factory.getInstance().getRefDAO().getAllRef();

        for (References aListR : listRef) {
            user = aListR.getUser();
            if (user.getIdUsers().equals(id)) {
                map.put(aListR.getIdRef(), new References(aListR.getCut_ref(), aListR.getDescription(),
                        aListR.getCount(), aListR.getTag()));
            }
        }

        list = Factory.getInstance().getRefDAO().getRefByIdU(id);

        //select ref that repeating another user
        for (int i = 0; i < list.size(); i++){
            map.put(list.get(i).getIdRef(), new References(list.get(i).getCut_ref(), list.get(i).getDescription(),
                    list.get(i).getCount(), list.get(i).getTag()));
        }
        return  map;
    }

    // vyborka id user po login*/
    public static int vuborkaIdU(HttpServletRequest req) throws SQLException {
        String login = (String) req.getAttribute("login");
        int id;
        User user;
        user = Factory.getInstance().getUserDAO().getUserByLogin(login);
        id = user.getIdUsers();
        return  id;
    }

    // vyborka maila po id
    public static String vuborkaMailPoId(Integer idU) throws SQLException {
        String mail;
        User user;
        user = Factory.getInstance().getUserDAO().getUserById(idU);
        mail = user.getMail();
        return  mail;
    }
}
