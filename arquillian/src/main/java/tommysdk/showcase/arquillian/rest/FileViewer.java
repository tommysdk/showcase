package tommysdk.showcase.arquillian.rest;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONStringer;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author Tommy Tynj&auml;
 */
@Named
@Path("view")
public class FileViewer {

    static final String exposedDirectory = "D:\\temp\\";

    @GET
    @Path("{filename}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response get(@PathParam("filename") final String file) {
        String fileContents = read(file);
        if (fileContents == null) return error();
        else return Response.status(200).entity(fileContents).build();
    }

    @GET
    @Path("list")
    @Produces(MediaType.TEXT_PLAIN)
    public Response list() {
        String[] files = getFileList();
        String list = "";
        for (String f : files) {
            list += f + "\r\n";
        }
        return Response.status(200).entity(list).build();
    }

    @GET
    @Path("{filename}/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response json(@PathParam("filename") final String file) {
        try {
            String fileContents = read(file);
            JSONStringer json = new JSONStringer();
            json.object().key(file).value(fileContents).endObject();
            return Response.status(200).entity(json.toString()).build();
        } catch (JSONException e) {
            return Response.status(500).entity("Internal error").build();
        }
    }

    private String[] getFileList() {
        File dir = new File(exposedDirectory);
        return dir.list();
    }

    private String read(final String filename) {
        if (filename == null) return null;
        try (FileInputStream is = new FileInputStream(exposedDirectory + filename)) {
            return IOUtils.toString(is, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Response error() {
        return Response.status(404).entity("File not found").build();
    }
}
