package tommysdk.showcase.arquillian.misc;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.Calendar;

/**
 * @author Tommy Tynj&auml;
 */
@RunWith(Arquillian.class)
public class WeekServiceTest {

    @Deployment
    public static Archive deployment() {
        return ShrinkWrap.create(JavaArchive.class, "week.jar")
                .addClass(WeekService.class);
    }

    @EJB(mappedName = "java:module/WeekService")
    WeekService service;

    @Test
    public void shouldReturnWeekOfYear() {
        Assert.assertNotNull(service.weekOfYear());
        Assert.assertEquals("" + Calendar.getInstance().get(Calendar.WEEK_OF_YEAR),
                service.weekOfYear());
    }
}
