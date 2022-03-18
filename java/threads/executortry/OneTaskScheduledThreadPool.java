package executortry;

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

import runnabletry.OneTask;

/**
 * OneTaskScheduledThreadPool
 */
public class OneTaskScheduledThreadPool {

    static Logger log = Logger.getLogger(OneTaskScheduledThreadPool.class.getName());
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

        log.info("ScheduledThreadPool created with size 2");
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        int tasksToSchedule = 3;
        ScheduledFuture<?>[] res = new ScheduledFuture[tasksToSchedule];
        long[] delays = { 10, 3, 7 };

        for (int i = 0; i < tasksToSchedule; i++) {
            log.info(String.format("scheduled OneTask to execute at %s",
                    new Date(System.currentTimeMillis() + (delays[i] * 1000))));
            res[i] = executor.schedule(oneTask, delays[i], TimeUnit.SECONDS);
        }
        log.info("scheduled 3 OneTask");

        for (ScheduledFuture<?> scheduledFuture : res) {
            try {
                scheduledFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                log.severe("task interrupted or failed");
            }
        }
        executor.shutdown();
    }
}