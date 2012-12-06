package tommysdk.showcase.ejb31;

import static org.junit.Assert.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

/**
 * Test case which demonstrates that <tt>@Asynchronous</tt> annotation
 * is not honored through local business interface with generics.<br/>
 * <br/>
 * Removal of generics in <tt>Async</tt> business interface makes the
 * test case pass. See http://blog.diabol.se/?p=353
 *
 * @author Tommy Tynj&auml;
 * @see Async
 * @see AsyncBean
 * @see Caller
 * @since EJB 3.1
 */
@RunWith(Arquillian.class)
public class TestAsyncCallOverInterfaceWithGenerics {

    @Deployment
    public static Archive deployment() {
        return ShrinkWrap.create(JavaArchive.class, "async.jar")
                .addClass(Async.class)
                .addClass(AsyncBean.class)
                .addClass(Caller.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB Caller async;

    @Test
    public void shouldBeAsync() throws InterruptedException {
        long duration = async.executeAsyncCall();
        Thread.sleep(2000);
        assertTrue("Asynchronous call was synchronous?", duration < 100);
    }
}
