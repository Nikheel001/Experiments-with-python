package executortry;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import runnabletry.OneTask;

/**
 * OneTaskFixedThreadPool
 */
public class OneTaskFixedThreadPool {

    static Logger log = Logger.getLogger(OneTaskFixedThreadPool.class.getName());
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

        log.info("FixedThreadPoolExecutor created with size 2");
        Executor executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            executor.execute(oneTask);
        }
        log.info("submitted 10 OneTask");

        // executor's limitation. tasks submitted for execution does not return Future.
        // use ExecutorService instead.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.severe("main thread sleep interrupted");
        } finally {
            System.exit(0);
        }
    }
}