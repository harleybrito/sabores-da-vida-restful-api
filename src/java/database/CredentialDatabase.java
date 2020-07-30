package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import model.Credential;

public class CredentialDatabase {
    
    public static Map<Long, Credential> getAll(){
        Map<Long, Credential> credentials = new HashMap<>();
        
        try{
            Connection databaseConnection = ConnectionDB.Connect();
            PreparedStatement statement = databaseConnection.prepareStatement("select * from credential");
            ResultSet data = statement.executeQuery();
            
            while(data.next()){
                Credential credential = new Credential();
                credential.setId(data.getLong(1));
                credential.setUser(data.getString(2));
                credential.setPassword(data.getString(3));
                credential.setEmployee_id(data.getLong(4));
                credentials.put(credential.getId(), credential);
            }
            
            databaseConnection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return credentials;
    }
    
    public static void save(Credential credential){
        try{
            Connection databaseConnection = ConnectionDB.Connect();
            PreparedStatement statement = databaseConnection.prepareStatement("insert into credential (user, password) values (?, ?)");
            statement.setString(1, credential.getUser());
            statement.setString(2, credential.getPassword());
            statement.executeUpdate();
            databaseConnection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void update(Credential credential){
        try{
            Connection databaseConnection = ConnectionDB.Connect();
            PreparedStatement statement = databaseConnection.prepareStatement("update credential set user = ?, password = ? where id = ? ");
            statement.setString(1, credential.getUser());
            statement.setString(2, credential.getPassword());
            statement.setLong(3, credential.getId());
            statement.executeUpdate();
            databaseConnection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void remove(long id){
        try{
            Connection databaseConnection = ConnectionDB.Connect();
            PreparedStatement statement = databaseConnection.prepareStatement("delete from credential where id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            databaseConnection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
