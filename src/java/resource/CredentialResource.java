package resource;

import authorization.Authorize;
import beans.ApiFilterBean;
import java.net.URI;
import java.util.List;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import model.Credential;
import service.CredentialService;

@Path("/credential")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CredentialResource {
    CredentialService credentialService = new CredentialService();
    
    @Authorize
    @GET
    public List<Credential> get(@BeanParam ApiFilterBean filterBean){
        if(filterBean.getUser()!= null){
            String user = filterBean.getUser();
            return credentialService.getByUser(user);
        }
        
        if(filterBean.getStart()>= 0 && filterBean.getSize()> 0){
            return credentialService.getAllPaginated(filterBean.getStart(), filterBean.getSize());
        }
        
        return credentialService.getAll();
    }
      
    @Authorize
    @GET
    @Path("/{id}")
    public Credential getCredential(@PathParam("id") long id){
        return credentialService.get(id);
    }
    
    @Authorize
    @POST
    public Response save(Credential credential, @Context UriInfo uriInfo){
        Credential newCredential = credentialService.save(credential);
        String newId = String.valueOf(newCredential.getId());
        URI url = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(url).entity(newCredential).build();
    }
    
    @Authorize
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, Credential credential, @Context UriInfo uriInfo){
        credential.setId(id);
        Credential newCredential = credentialService.update(credential);
        return Response.created(uriInfo.getAbsolutePath()).entity(newCredential).build();
    }
    
    @Authorize
    @DELETE
    @Path("/{id}")
    public void deleteFuncionario(@PathParam("id") long id){
        credentialService.remove(id);
    }
}
