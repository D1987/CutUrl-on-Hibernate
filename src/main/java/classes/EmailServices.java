package classes;

//class dlya vyborki poctovogo servisa, rabotaet s CSV
public class EmailServices {
    private Integer id_es;
    private String domain;
    private String name;
    private String url;

    public EmailServices() { }

    public Integer getId_es() {
        return id_es;
    }

    public void setId_es(Integer id_es) {
        this.id_es = id_es;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
