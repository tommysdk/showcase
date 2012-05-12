package tommysdk.showcase.javaee6.rest;

import tommysdk.showcase.javaee6.ejb.AsyncService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Tommy Tynj&auml;
 */
@Stateless
@Path("async")
public class AsyncInvoker {

    @EJB
    private AsyncService async;

    @GET
    @Path("invoke")
    @Produces(MediaType.TEXT_PLAIN)
    public Response invoke() {
        List<Future<String>> asyncResults = createAsyncTasks();
        String response = collect(asyncResults);
        return Response.status(200).entity(response).build();
    }

    private List<Future<String>> createAsyncTasks() {
        List<Future<String>> asyncResults = new LinkedList<>();
        for (int i = 0; i < 10; i = i + 1) {
            Future<String> future = async.execute(i);
            asyncResults.add(future);
        }
        return asyncResults;
    }

    private String collect(final List<Future<String>> asyncResults) {
        String response = System.lineSeparator();
        for (int i = 0; i < asyncResults.size(); i = i + 1) {
            try {
                response += asyncResults.get(i).get();
            } catch (InterruptedException | ExecutionException e) {
                response += "Async task " + i + " threw: " + e.getMessage();
            } finally {
                response += System.lineSeparator();
            }
        }
        return response;
    }

}
