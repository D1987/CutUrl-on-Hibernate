package DAO.Impl;

import DAOInter.EmailServicesDAO;
import classes.EmailServices;
import org.hibernate.Session;
import persistence.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;

public class EmailServicesImpl implements EmailServicesDAO {

    public EmailServices getEmailServices(String domain) throws SQLException {
        Session session = null;
        EmailServices emailServices = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            emailServices = (EmailServices) session.createQuery("from  EmailServices e where e.domain=?")
                    .setString(0, domain).uniqueResult();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return emailServices;
    }
}
