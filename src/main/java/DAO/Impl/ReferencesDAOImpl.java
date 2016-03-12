package DAO.Impl;

import DAOInter.RefDAO;
import classes.Factory;
import classes.References;
import classes.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import persistence.HibernateUtil;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public class ReferencesDAOImpl implements RefDAO{

    //select all ref. for main page
    public List<References> getAllRef() throws SQLException {
        Session session = null;
        List<References> list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = session.createQuery("from References").list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    //save refer
    public void saveRefer(User user,String ssylka,String sokr,String desc,String tag,File file) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            InputStream inputStream = new FileInputStream(file);
            References references = new References();
            references.setUser(user);                                                //get id user
            references.setFull_ref(ssylka);
            references.setCut_ref(sokr);
            references.setDescription(desc);
            references.setTag(tag);
            Blob blob = Hibernate.getLobCreator(session).createBlob(inputStream, file.length());
            references.setQrcode(blob);
            session.save(references);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    //count all ref
    public List<Integer> getCountAllRef() throws SQLException {
        Session session = null;
        List<Integer> list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = (List) session.createQuery("select count(*) from References r").list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    //count clicks
    public List<Integer> getCountAllClicks() throws SQLException {
        Session session = null;
        List<Integer> list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = (List) session.createQuery("SELECT SUM(r.count) FROM References r").list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    // update ref tag
    public void updateReferTag(String tag, Integer idR) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("update References r set r.tag =? where r.idRef=?").setString(0,tag).setParameter(1, idR)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // update ref description
    public void updateReferDescription(String description, Integer idR) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("update References r set r.description =? where r.idRef=?").setString(0,description)
                    .setParameter(1, idR).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // update ref tag description
    public void updateReferTagDescription(String tag, String description, Integer idR) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("update References r set r.description=?, r.tag =? where r.idRef=?")
                    .setString(0, description).setString(1,tag).setParameter(2, idR).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // update ref count
    public void updateReferCount(String p, Integer idR) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("update References r set r.count =? where r.idRef=?").setParameter(0, p)
                    .setParameter(1, idR).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // update ref idU
    public void updateReferIdU(Integer id,Integer idR) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("update References r set r.idU =? where r.idRef=?").setParameter(0, id)
                    .setParameter(1, idR).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // update ref idUser,if owner ref, for delete
    public void updateReferIdUser(User user,Integer id) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("update References r set r.user =?, r.idU=? where r.idRef=?")
                    .setParameter(0,user).setString(1, null).setParameter(2, id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // update ref idUser, if dont owner ref, for delete
    public void updateReferIdU(Integer id) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("update References r set r.idU =? where r.idRef=?").setParameter(0, null)
                    .setParameter(1, id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // update ref idU by IdU for delete user
    public void updateReferIdUByIdU(Integer idU) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("update References r set r.idU =? where r.idU=?").setParameter(0, null)
                    .setParameter(1, null).setParameter(1,idU).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateReferIdUByIdRef(Integer idU,Integer idR) throws SQLException {
        Session session = null;
        User user;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            user = (User) session.get(User.class,idU);
            session.createQuery("update References r set r.user=?, r.idU =? where r.idRef=?").setParameter(0, user)
                    .setParameter(1, null).setParameter(1,idR).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // delete ref
    public void deleteRef(Integer id) throws SQLException {
        Session session = null;
        References references = new References();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("delete from References r where r.idRef=?").setParameter(0,id).executeUpdate();
            session.delete(references);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // get ref by id
    public References getRefById(Integer idR) throws SQLException {
        Session session = null;
        References references = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            references = (References) session.get(References.class, idR);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return references;
    }

    // get ref by idU (copy ref)
    public List<References> getRefByIdU(Integer id) throws SQLException {
        Session session = null;
        List<References> list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            list = (List)session.createQuery("from References r where r.idU=?").setParameter(0,id).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    // get ref by cutRef
    public References getRefByCutRef(String url) throws SQLException {
        Session session = null;
        References references = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            references = (References) session.createQuery("from References r where r.cut_ref=?").setString(0, url).uniqueResult();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return references;
    }

    public References getRefByFullRef(String ref) throws SQLException {
        Session session = null;
        References references = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            references = (References) session.createQuery("from References r where r.full_ref=?").setString(0,ref).uniqueResult();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return references;
    }

    // get ref by User
    public References getRefByUser(Integer idU) throws SQLException {
        Session session = null;
        References references = null;
        User user;
        user = Factory.getInstance().getUserDAO().getUserById(idU);
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            references = (References) session.createQuery("SELECT r.idRef,r.idU FROM References r WHERE r.idU IS NOT NULL AND r.user=?")
                    .setParameter(0, user).uniqueResult();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return references;
    }
}
