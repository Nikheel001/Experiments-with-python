package waitnotifytry;

import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * WaitNotifyMain
 */
public class WaitNotifyMain {

    static Logger log = Logger.getLogger(WaitNotifyMain.class.getName());
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

        StringBuffer sb = new StringBuffer();

        ReadTask readTask = new ReadTask(sb);
        WriteTask writeTask = new WriteTask(sb);

        Thread[] arr = new Thread[5];
        arr[0] = new Thread(writeTask);

        for (int i = 1; i < arr.length; i++) {
            arr[i] = new Thread(readTask);
            log.info("spawning ReadTask " + arr[i].getName());
            execThread(arr[i]);
        }
        log.info("spawning WriteTask " + arr[0].getName());
        execThread(arr[0]);

        for (Thread thread : arr) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("-");
            }
        }
    }

    static void execThread(Thread t) {
        t.start();
    }
}