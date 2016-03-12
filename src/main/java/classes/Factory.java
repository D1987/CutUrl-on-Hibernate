package classes;

import DAO.Impl.EmailServicesImpl;
import DAO.Impl.ReferencesDAOImpl;
import DAO.Impl.UserDAOImpl;
import DAOInter.EmailServicesDAO;
import DAOInter.RefDAO;
import DAOInter.UserDAO;

// class for create objects
public class Factory {
    private static RefDAO refDAO = null;
    private static UserDAO userDAO = null;
    private static EmailServicesDAO emailServicesDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public RefDAO getRefDAO(){
        if (refDAO == null){
            refDAO = new ReferencesDAOImpl();
        }
        return refDAO;
    }

    public UserDAO getUserDAO(){
        if (userDAO == null){
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }

    public EmailServicesDAO getEmailServicesDAO(){
        if (emailServicesDAO == null){
            emailServicesDAO = new EmailServicesImpl();
        }
        return emailServicesDAO;
    }
}
