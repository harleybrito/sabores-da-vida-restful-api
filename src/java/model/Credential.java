package model;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlTransient;

public class Credential{
    
    private long id;
    private String user;
    private String password;
    private long employee_id;
    private Map<Long, Credential> credentials = new HashMap<>();

    public Credential() { }
    
    public Credential(long id, String user, String password, long employee_id) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.employee_id = employee_id;
    }
    
    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Map<Long, Credential> getCredentials() {
        return credentials;
    }

    public void setCredentials(Map<Long, Credential> credentials) {
        this.credentials = credentials;
    }
}
