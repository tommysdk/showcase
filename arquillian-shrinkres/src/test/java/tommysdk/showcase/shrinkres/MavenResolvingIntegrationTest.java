package tommysdk.showcase.shrinkres;

import static org.junit.Assert.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.File;

/**
 * @author Tommy Tynj&auml;
 */
@RunWith(Arquillian.class)
public class MavenResolvingIntegrationTest {

    @Deployment
    public static WebArchive createDeployment() {
        File lib = Maven.resolver()
                .resolve("joda-time:joda-time:2.1")
                .withoutTransitivity()
                .asSingle(File.class);
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(WeekService.class)
                .addAsLibrary(lib)
                .addAsWebInfResource(new StringAsset("<beans/>"), "beans.xml")
                .addAsWebInfResource(new StringAsset("<web-app></web-app>"), "web.xml");
    }

    @Inject
    @Default
    private WeekService weekService;

    @Test
    public void shouldInjectInstance() {
        assertNotNull(weekService);
    }

}
