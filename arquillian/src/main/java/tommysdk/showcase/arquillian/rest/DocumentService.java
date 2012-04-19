package tommysdk.showcase.arquillian.rest;

import tommysdk.showcase.arquillian.ejb.DocumentManager;
import tommysdk.showcase.arquillian.entity.Document;

import javax.ejb.EJB;
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
@Path("document")
public class DocumentService {

    @EJB(name = "java:app/showcase/DocumentManager")
    private DocumentManager documentManager;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") final String id) {
        if (id == null || documentManager == null) return error();
        Document document = documentManager.get(id);
        if (document == null) return error();
        else return Response.status(200).entity(document.getContent()).build();
    }

    private Response error() {
        return Response.status(404).entity("{\n   \"content\": \"N/A\"\n}").build();
    }

}
