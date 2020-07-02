package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String url = "jdbc:mysql://localhost/lanchonete-sabores?serverTimezone=UTC";
    private static final String usr = "root";
    private static final String pas = "";
    
    public static Connection Connect() throws SQLException{
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        
        return DriverManager.getConnection(url, usr, pas);
    }
}