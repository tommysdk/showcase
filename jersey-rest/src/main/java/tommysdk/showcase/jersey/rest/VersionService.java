package tommysdk.showcase.jersey.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("version")
public class VersionService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "1.0";
    }

    @GET
    @Path("vendor")
    @Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
    public String getVendor() {
        return "Tommy Tynjä";
    }
}
