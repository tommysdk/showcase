package tommysdk.jaxrs;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Tommy Tynj&auml;
 */
@Stateless
@Path("case")
public class RestfulService {

    @GET
    @Path("bounce/{value}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response bounce(@PathParam("value") final String val) {
        return Response.ok(val).build();
    }
}
