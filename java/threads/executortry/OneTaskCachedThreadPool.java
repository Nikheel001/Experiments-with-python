package executortry;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import runnabletry.OneTask;

/**
 * OneTaskFixedThreadPool
 */
public class OneTaskCachedThreadPool {

    static Logger log = Logger.getLogger(OneTaskCachedThreadPool.class.getName());
    static Handler h = new ConsoleHandler();
    static Formatter f = new Formatter() {
        @Override
        public String format(LogRecord record) {
            return String.format(" %s : %s : %s %s",
                    Thread.currentThread().getName(), record.getMessage(),
                    new Date(record.getMillis()), System.lineSeparator());
        }
    };

    public static void main(String[] args) {
        log.setUseParentHandlers(false);
        h.setFormatter(f);
        log.addHandler(h);

        log.info("OneTask created");
        OneTask oneTask = new OneTask(log);

        log.info("CachedThreadPool created");
        ExecutorService executor = Executors.newCachedThreadPool();

        int lim = 10;
        // ctr = 0;
        var res = new Future[lim];

        for (int i = 0; i < lim; i++) {
            // ignoring failure case
            res[i] = executor.submit(oneTask);
        }
        log.info("submitted 10 OneTask");

        for (int i = 0; i < lim; i++) {
            try {
                res[i].get();
            } catch (InterruptedException | ExecutionException e) {
                log.severe(String.format("task %d failed or interrupted", i));
            }
        }

        executor.shutdown();
        log.info("finished all tasks successfully");
    }
}