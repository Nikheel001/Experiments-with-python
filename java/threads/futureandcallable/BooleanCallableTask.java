package futureandcallable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

/**
 * BooleanCallableTask
 */
public class BooleanCallableTask implements Callable<Boolean> {

    Random r = new Random();
    Logger log;

    public BooleanCallableTask(Logger _log) {
        log = _log;
    }

    @Override
    public Boolean call() throws Exception {
        return r.nextBoolean();
    }

}