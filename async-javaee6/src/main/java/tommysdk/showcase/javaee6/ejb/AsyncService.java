package tommysdk.showcase.javaee6.ejb;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * @author Tommy Tynj&auml;
 */
@Stateless
public class AsyncService {

    private Logger logger = Logger.getLogger("AsyncService");

    @Asynchronous
    public Future<String> execute(final int id) {
        final String task = "Async task " + id + " ";
        logger.info("Starting " + task);

        long start = System.currentTimeMillis();
        long waitingTime = (new Random().nextInt(5) + 1) * 1000;
        while (System.currentTimeMillis() - start < waitingTime) {
            logger.finest(task + "is running...");
        }
        return new AsyncResult<>(task + "ran for " + (System.currentTimeMillis() - start) + " ms");
    }
}
