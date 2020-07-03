package resource;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import model.Credential;
import service.CredentialService;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {
    
    CredentialService credentialService = new CredentialService();
    
    @POST
    public Response login(Credential credential, @Context UriInfo uriInfo){
        URI url = uriInfo.getAbsolutePathBuilder().build();
        List<Credential> validCredential = credentialService.getByUser(credential.getUser());
        
        
        try{
            if(validCredential.isEmpty()){
                return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário não encontrado.").location(url).build();
            }
            
            if(validCredential.get(0).getPassword().equals(credential.getPassword())){
                Algorithm chave = Algorithm.HMAC256("secret");
                String jwtToken = JWT.create().withIssuer("auth0").sign(chave);
                System.out.print(credential.getUser());
                System.out.print(credential.getPassword());
                return Response.status(Response.Status.OK).entity(jwtToken).build();
            }else{
                return Response.status(Response.Status.UNAUTHORIZED).entity("Senha inválida.").location(url).build();
            }
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
