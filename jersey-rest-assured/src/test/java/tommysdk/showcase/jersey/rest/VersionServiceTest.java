package tommysdk.showcase.jersey.rest;

import com.jayway.restassured.response.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.net.URL;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Tommy Tynj&auml;
 */
@RunAsClient
@RunWith(Arquillian.class)
public class VersionServiceTest {

    @Deployment
    public static Archive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "jersey-rest.war")
                .addClass(VersionService.class)
                .addClass(ContextPaths.class)
                .setWebXML(new File("src/main/webapp", "WEB-INF/web.xml"));
    }

    @ArquillianResource URL restServiceUrl;

    @Test
    public void shouldInvokeVersionRestService() {
        final String url = restServiceUrl + ContextPaths.VERSION;
        Response response = get(url);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.asString(), is(VersionService.VERSION));
    }

    @Test
    public void shouldInvokeVendorRestService() {
        final String url = restServiceUrl + ContextPaths.VERSION + ContextPaths.VENDOR;
        Response response = get(url);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.asString(), is(VersionService.VENDOR));
    }

}
