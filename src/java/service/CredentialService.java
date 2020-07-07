package service;

import database.CredentialDatabase;
import exception.DataNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Credential;

public class CredentialService {
    private Map<Long, Credential> credentials = CredentialDatabase.getAll();
    
    public List<Credential> getAll(){
        return new ArrayList<Credential>(credentials.values());
    }
    
    public List<Credential> getByUser(String user){
        List<Credential> credentialsByUser = new ArrayList<Credential>();
        String credentialUser;
        
        for(Credential credential: credentials.values()){
            credentialUser = credential.getUser();
            
            if(credentialUser.equals(user)){
                credentialsByUser.add(credential);
            }
        }
        return credentialsByUser;
    }
    
    public List<Credential> getAllPaginated(int start, int size){
        ArrayList<Credential> list = new ArrayList<>(credentials.values());
        
        if(start + size > list.size()){
            return new ArrayList<>();
        }
        
        return list.subList(start, start + size);
    }
    
    public Credential get(long id){
        Credential credential = credentials.get(id);
        
        if(credential == null){
            throw new DataNotFoundException("Usuário com id{" + id + "} não encontrado.");
        }
        
        return credential;
    }
    
    public Credential save(Credential credential){
        CredentialDatabase.save(credential);
        return credential;
    }
    
    public Credential update(Credential credential){
        if(credential.getId() <= 0){
            return null;
        }
        
        CredentialDatabase.update(credential);
        return credential;
    }
    
    public void remove(long id){
        CredentialDatabase.remove(id);
    }
}