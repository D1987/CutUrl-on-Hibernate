package DAOInter;

import classes.User;
import java.sql.SQLException;

public interface UserDAO {
    public User getUserByMail(String mail) throws SQLException;
    public User getUserByLogin(String login) throws SQLException;
    public User getUserByPassword(String password) throws SQLException;
    public void updateUserPasswordForgot(String password,String mail) throws SQLException;
    public User userFirstEnterToPersonCabinet(String login,String password,String mail) throws SQLException;
    public User getUserById(Integer idU) throws SQLException;
    public void updateUserPassword(String password,String idU) throws SQLException;
    public void updateUserLogin(String login,String idU) throws SQLException;
    public void updateUserLoginPassword(String login,String password,String idU) throws SQLException;
    public void deleteUser(Integer idU) throws SQLException;
}
