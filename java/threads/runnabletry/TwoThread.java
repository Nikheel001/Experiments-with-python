package runnabletry;

import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * TwoThread
 */
public class TwoThread {

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

        // 1. main thread
        log.info("creating OneTask in main");

        OneTask task1 = new OneTask(log);

        // 2. thread-1
        // three ways to spawn new thread
        // way - 1
        Thread thread1 = new Thread(task1);

        execThread(thread1);

        // // way - 2
        // thread1 = new Thread(new Runnable() {
        // @Override
        // public void run() {
        // System.out.println("Anonymous Task executed by : " +
        // Thread.currentThread().getName());
        // }
        // });

        // execThread(thread1);

        // // way - 3
        // thread1 = new Thread() {
        // public void run() {
        // System.out.println("Anonymous Thread.run() executed by : " +
        // Thread.currentThread().getName());
        // };
        // };

        // execThread(thread1);
    }

    static void execThread(Thread t) {
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            log.severe("thread Interrupted before it could finish execution");
        }
    }
}