package tommysdk.showcase.ejb31;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * @author Tommy Tynj&auml;
 */
@Stateless
public class Caller {

    @EJB
    private Async<String> async;

    public long executeAsyncCall() {
        System.out.println("Caller: Calling async method");
        long pre = System.currentTimeMillis();
        async.method("<>");
        System.out.println("Caller: Proceeding after async method call");
        return System.currentTimeMillis() - pre;
    }
}
