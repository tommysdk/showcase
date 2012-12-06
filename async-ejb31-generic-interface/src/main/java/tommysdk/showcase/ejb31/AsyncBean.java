package tommysdk.showcase.ejb31;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

/**
 * @author Tommy Tynj&auml;
 */
@Stateless
public class AsyncBean implements Async<String> {

    @Asynchronous
    public void method(final String s) {
        try {
            for (int i = 1; i < 5; i++) {
                System.out.println("Async bean sleeping... (" + s + ")");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
