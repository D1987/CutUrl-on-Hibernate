package classes;

import java.sql.Blob;

//class ssylok
public class References {
    private Integer idRef;
    private String full_ref;
    private String cut_ref;
    private String description;
    private String count;
    private String tag;
    private Blob qrcode;
    private User user;
    private Integer idU;

    public References() {}

    public References(String cut_ref) {
        this.cut_ref = cut_ref;
    }

    public References(Integer id) {
        this.idRef = id;
    }

    public References(String login, String cut_ref) {
        //this.login=login;
        this.cut_ref = cut_ref;
    }

    public References(String login, String full_ref, String cut_ref,  String description, String tag) {
        //this.login=login;
        this.full_ref=full_ref;
        this.cut_ref=cut_ref;
        this.description = description;
        this.tag = tag;
    }

    public References(String cut_ref, String description, String count, String tag) {       //use
        this.cut_ref = cut_ref;
        this.description = description;
        this.count = count;
        this.tag = tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCut_ref(String cut_ref) {
        this.cut_ref = cut_ref;
    }

    public void setFull_ref(String full_ref) {
        this.full_ref = full_ref;
    }

    public void setIdRef(Integer id) {this.idRef = id;}

    public void setIdU(Integer idU) {this.idU = idU; }

    public void setUser(User user) {this.user = user;}

    public void setQrcode(Blob qrcode) {this.qrcode = qrcode;}

    public User getUser() {return user;}

    public Integer getIdRef() { return idRef; }

    public String getFull_ref() {return full_ref; }

    public String getCut_ref() { return cut_ref; }

    public String getDescription() {return description; }

    public String getCount() { return count;}

    public String getTag() { return tag;}

    public Blob getQrcode() { return qrcode;}

    public Integer getIdU() { return idU;}

}
