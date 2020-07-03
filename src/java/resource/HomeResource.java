package resource;

import authorization.Authorize;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/home")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HomeResource {
    
    @Authorize
    @GET
    public Response home(@Context UriInfo uriInfo){
        System.out.print("/home");
        return Response.status(Response.Status.OK).build();
    }
}
