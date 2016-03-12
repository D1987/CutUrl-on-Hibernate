package classes;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Mail {
    synchronized public Map selectDomain(String mail) throws SQLException {
        Map<String,String> map = new HashMap<String, String>();
        String domain;
        domain = parsingMail(mail);  //poluchaem domen 2 urovnya
        EmailServices emailServices;
        emailServices = Factory.getInstance().getEmailServicesDAO().getEmailServices(domain);

        if (emailServices!=null){
            map.put(emailServices.getName(),emailServices.getUrl());
        }
        return map;
    }
    //poluchaem domen 2 urovnya
    synchronized public String parsingMail(String mail){
        String domain;
        String[] str = mail.split("@");
        domain = str[1];
        return domain;
    }
}
