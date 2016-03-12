package classes;

//class user
public class User {

    private Integer idUsers;
    private String login;
    private String password;
    private String mail;

    public User() {}

    public void setIdUsers(Integer idUsers) {this.idUsers = idUsers; }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getIdUsers() {return idUsers; }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }
}
