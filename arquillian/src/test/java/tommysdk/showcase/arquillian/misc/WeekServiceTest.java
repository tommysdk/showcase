package tommysdk.showcase.arquillian.misc;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author Tommy Tynj&auml;
 */
@RunWith(Arquillian.class)
public class WeekServiceTest {

    @Deployment
    public static Archive deployment() {
        return ShrinkWrap.create(JavaArchive.class, "week.jar")
                .addClass(WeekService.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    WeekService service;

    @Test
    public void shouldReturnWeekOfYear() {
        assertNotNull(service.weekOfYear());
        assertThat(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR), is(equalTo(service.weekOfYear())));
    }
}
