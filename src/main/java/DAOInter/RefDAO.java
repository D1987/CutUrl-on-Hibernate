package DAOInter;

import classes.References;
import classes.User;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public interface RefDAO {
    public List getAllRef() throws SQLException;
    public void saveRefer(User user,String ssylka,String sokr,String desc,String tag,File file) throws SQLException;
    public List getCountAllRef() throws SQLException;
    public List getCountAllClicks() throws SQLException;
    public References getRefByCutRef(String url) throws SQLException;
    public References getRefByFullRef(String ref) throws SQLException;
    public References getRefById(Integer idR) throws SQLException;
    public References getRefByUser(Integer idU) throws SQLException;
    public List getRefByIdU(Integer id) throws SQLException;
    public void updateReferTag(String tag,Integer idR) throws SQLException;
    public void updateReferDescription(String description,Integer idR) throws SQLException;
    public void updateReferTagDescription(String tag,String description,Integer idR) throws SQLException;
    public void updateReferCount(String p,Integer idR) throws SQLException;
    public void updateReferIdU(Integer id,Integer idR) throws SQLException;
    public void updateReferIdUByIdU(Integer idU) throws SQLException;
    public void updateReferIdUByIdRef(Integer idU,Integer idR) throws SQLException;
    public void updateReferIdUser(User user,Integer idR) throws SQLException;
    public void updateReferIdU(Integer id) throws SQLException;
    public void deleteRef(Integer id) throws SQLException;

}
