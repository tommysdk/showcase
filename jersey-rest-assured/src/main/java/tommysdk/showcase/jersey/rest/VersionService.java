package tommysdk.showcase.jersey.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(ContextPaths.VERSION)
public class VersionService {

    final static String VERSION = "1.0";
    final static String VENDOR = "Tommy Tynjä";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return VERSION;
    }

    @GET
    @Path(ContextPaths.VENDOR)
    @Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
    public String getVendor() {
        return VENDOR;
    }
}
