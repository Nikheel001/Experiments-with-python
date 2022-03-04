package callabletry;

import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * ManyThreadCallable
 */
public class ManyThreadCallable {

    static Logger log = Logger.getLogger(OneThreadCallable.class.getName());
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

        log.info("spawning task in current thread");

        OneCallableTask oct = new OneCallableTask(log);

        Thread[] arr = new Thread[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Thread() {
                @Override
                public void run() {
                    try {
                        String res = oct.call();
                        log.info(res);
                    } catch (Exception e) {
                        log.severe("Callable execution cancalled");
                    }
                }
            };
        }

        for (Thread thread : arr) {
            thread.start();
        }

        for (Thread thread : arr) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("-");
            }
        }
    }
}