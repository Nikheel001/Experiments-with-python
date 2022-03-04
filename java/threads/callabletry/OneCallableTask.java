package callabletry;

import java.util.concurrent.Callable;
import java.util.logging.Logger;

/**
 * OneCallableTask
 */
public class OneCallableTask implements Callable<String> {
    Logger log ;

    public OneCallableTask(Logger _log) {
        log = _log;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        log.info("called successfully");
        return "Done";
    }
}