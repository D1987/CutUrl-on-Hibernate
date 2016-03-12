package DAOInter;

import classes.EmailServices;

import java.sql.SQLException;

public interface EmailServicesDAO {
    public EmailServices getEmailServices(String domain) throws SQLException;
}
