package futureandcallable;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import callabletry.OneCallableTask;

/**
 * ScheduledCallable
 */
public class OneScheduledCallable {

    static Logger log = Logger.getLogger(OneScheduledCallable.class.getName());
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
        log.info("creating task in current thread");
        OneCallableTask oct = new OneCallableTask(log);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        log.info("spawning task");
        ScheduledFuture<String> res = scheduledExecutorService.schedule(oct, 6, TimeUnit.SECONDS);
        try {
            String result = res.get();
            log.info(result);
        } catch (InterruptedException | ExecutionException e) {
            log.severe("execution failed or interrupted");
        } finally {
            scheduledExecutorService.shutdown();
        }
    }
}