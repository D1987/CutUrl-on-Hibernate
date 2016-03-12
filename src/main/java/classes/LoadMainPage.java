package classes;

import java.sql.SQLException;
import java.util.*;

//class for start main page
public class LoadMainPage {
    //vyborka vseh ssylok dlya zagruzki glavnoi stranicy
    synchronized public Map<References,String> showAllRefer() throws SQLException {
        Map<References,String> map = new HashMap<References, String>();
        List<References> list;
        list = (List) Factory.getInstance().getRefDAO().getAllRef();
        User user;
        for(References references: list){
           user = references.getUser();
            map.put(references,user.getLogin());
        }
        return map;
    }

    //vyborka kollichestva ssylok i klikov animaciya
    synchronized public Map countRefAndClick() throws SQLException {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> list;
        List<Integer> list1;
        list = (List) Factory.getInstance().getRefDAO().getCountAllRef();
        list1 = (List)Factory.getInstance().getRefDAO().getCountAllClicks();
        map.put(list.get(0), list1.get(0));
        return map;
    }
}
