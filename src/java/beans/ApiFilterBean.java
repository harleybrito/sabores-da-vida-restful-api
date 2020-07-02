package beans;

import javax.ws.rs.QueryParam;

public class ApiFilterBean {
    private @QueryParam("name") String name;
    private @QueryParam("user") String user;
    private @QueryParam("start") int start;
    private @QueryParam("size") int size;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int tamanho) {
        this.size = tamanho;
    }
}
