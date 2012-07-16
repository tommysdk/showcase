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
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

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
    private final static String title = "UI test";

    @Deployment
    public static Archive createWebArchiveDeployment() {
        return ShrinkWrap.create(WebArchive.class, "ui.war")
                .addAsWebResource(new StringAsset("<html><head><title>" + title + "</title></head><body><p>" + greeting + "</p></body></html>"), "index.html")
                .setWebXML("web.xml");
    }

    @Test
    @RunAsClient
    public void importantElementsShouldBePresent() {
        driver.open(contextPath.toString());
        driver.waitForPageToLoad("3");
        assertTrue(driver.isTextPresent(greeting));
        assertTrue(driver.getTitle().equals(title));
    }

}
