package runnabletry;

import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * ManyThreadOneTask
 */
public class ManyThreadOneTask {

    static Logger log = Logger.getLogger(ManyThreadOneTask.class.getName());
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

        log.info("Creating OneTask");
        OneTask task = new OneTask(log);

        Thread[] arr = new Thread[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Thread(task);
        }

        log.info("spawning many threads with OneTask");
        for (Thread thread : arr) {
            thread.start();
        }

        for (Thread thread : arr) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                log.severe("parent thread interrupted");
            }
        }

    }
}