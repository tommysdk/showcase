package tommysdk.showcase.javaee6.rest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import static org.junit.Assert.*;

import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tommysdk.showcase.javaee6.ejb.AsyncService;

import javax.ejb.EJB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

/**
 * @author Tommy Tynj&auml;
 */
@RunWith(Arquillian.class)
public class AsyncTest {

    private static final Logger logger = Logger.getLogger("AsyncTest");

    @Deployment
    public static Archive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "async-javaee6.war")
                .addClass(AsyncInvoker.class)
                .addClass(AsyncService.class)
                .addClass(EnableRest.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB(mappedName = "java:module/AsyncInvoker")
    AsyncInvoker asyncInvoker;

    @Test
    public void shouldInvokeAsyncServices() {
        assertNotNull("Necessary dependency not injected?", asyncInvoker);
        logger.info(asyncInvoker.invoke().getEntity().toString());
    }

    @Test
    @RunAsClient
    public void shouldInvokeAsyncServicesThroughRestfulService() throws IOException {
        URL url = new URL("http://localhost:8080/async-javaee6/rest/async/invoke");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.connect();

        String response = null;
        try (InputStreamReader isr = new InputStreamReader(http.getInputStream());
             BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                response += line + System.lineSeparator();
            }
        }
        http.disconnect();

        assertNotNull(response);
        logger.info("Received response: " + response);
        assertTrue(response.contains("Async task"));
        assertTrue(response.contains("ran for"));
    }
}
