package runnabletry;

import java.util.logging.Logger;

/**
 * OneTask
 */
public class OneTask implements Runnable {

    Logger log;

    public OneTask(Logger _log) {
        log = _log;
    }

    @Override
    public void run() {
        log.info("OneTask executed");
    }
}