package tommysdk.showcase.arquillian.drone;

import com.thoughtworks.selenium.DefaultSelenium;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

/**
 * @author Tommy Tynj&auml;
 */
@RunWith(Arquillian.class)
public class SeleniumUiTest {

    @Drone
    DefaultSelenium driver;

    @ArquillianResource
    URL contextPath;

    private final static String greeting = "Hello World!";

    @Deployment
    public static Archive createWebArchiveDeployment() {
        Archive a = ShrinkWrap.create(WebArchive.class, "ui.war")
                .addAsWebResource(new StringAsset("<html><body><p>" + greeting + "</p></body></html>"), "index.html")
                .setWebXML("web.xml");
        a.writeTo(System.err, Formatters.VERBOSE);
        return a;
    }

    @Test
    @RunAsClient
    public void importantElementsShouldBePresent() throws MalformedURLException {
        driver.open(contextPath.toString());
        driver.waitForPageToLoad("3");
        assertTrue(driver.isTextPresent(greeting));
    }

}
