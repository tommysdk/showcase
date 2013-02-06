package tommysdk.showcase.mix;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tommysdk.showcase.mix.java.JavaWeekService;
import javax.inject.Inject;
import java.util.Calendar;
import static org.junit.Assert.*;

/**
 * @author Tommy Tynj&auml;
 */
@RunWith(Arquillian.class)
public class JavaIntegrationTest {

    @Deployment
    public static JavaArchive deployment() {
        return ShrinkWrap.create(JavaArchive.class, "week.jar")
                .addClass(JavaWeekService.class)
                .addAsManifestResource(new StringAsset("<beans/>"), "beans.xml");
    }

    @Inject
    private JavaWeekService weekService;

    @Test
    public void shouldProduceGreenBar() {
        assertTrue(true);
    }

    @Test
    public void shouldReturnCurrentWeek() {
        assertNotNull("Bean not injected?", weekService);
        assertEquals(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR), weekService.currentWeek());
    }
}
