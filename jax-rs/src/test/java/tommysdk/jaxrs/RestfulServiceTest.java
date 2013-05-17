package tommysdk.jaxrs;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

/**
 * @author Tommy Tynj&auml;
 */
@RunWith(Arquillian.class)
public class RestfulServiceTest {

    private final String path = "http://localhost:8080/jax-rs/";

    @org.jboss.arquillian.container.test.api.Deployment
    public static Archive getDeployment() {
        return Deployment.forRestfulService();
    }

    @Test
    @RunAsClient
    public void shouldConvertSpecifiedStringToLowerCase() {
        final String inputValue = "Arbitrary_text";
        Assert.assertEquals(inputValue, Http.get(path, "service/case/bounce/" + inputValue));
    }
}
