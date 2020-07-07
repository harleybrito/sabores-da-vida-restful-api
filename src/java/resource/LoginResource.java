package resource;

import beans.ApiFilterBean;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import static com.mysql.cj.MysqlType.JSON;
import java.net.URI;
import java.util.List;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import model.Credential;
import model.Token;
import service.CredentialService;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {
    
    CredentialService credentialService = new CredentialService();
    
    @GET
    public Response getUsersString(@BeanParam ApiFilterBean filterBean){
        System.out.print("login?user=" + filterBean.getUser());
        if(filterBean.getUser()!= null){
            String user = filterBean.getUser();
            List<Credential> found = credentialService.getByUser(user);
            
            if(found.size() > 0){
                if(found.size() == 1){
                    return Response.status(Response.Status.OK).entity("true").build();
                }else{
                    return Response.status(Response.Status.OK).entity("false").build();
                }
            }else{
                return Response.status(Response.Status.OK).entity("false").build();
            }
            
        }else{
            return Response.status(Response.Status.OK).entity("false").build();
        }
    }
    
    @POST
    public Response login(Credential credential, @Context UriInfo uriInfo){
        System.out.print("login");
        List<Credential> validCredential = credentialService.getByUser(credential.getUser());
        
        
        try{
            if(validCredential.isEmpty()){
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
            
            if(validCredential.get(0).getPassword().equals(credential.getPassword())){
                Algorithm chave = Algorithm.HMAC256("secret");
                Token token = new Token(JWT.create().withIssuer("auth0").sign(chave));
                return Response.status(Response.Status.OK).entity(token).build();
            }else{
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
