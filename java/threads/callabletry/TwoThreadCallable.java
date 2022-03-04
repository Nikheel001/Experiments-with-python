package callabletry;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * TwoThreadCallable
 */
public class TwoThreadCallable {

    static Logger log = Logger.getLogger(TwoThreadCallable.class.getName());
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
        log.info("spawning task from current thread");
        OneCallableTask oct = new OneCallableTask(log);

        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    String res = oct.call();
                    log.info(res);
                } catch (Exception e) {
                    log.log(Level.SEVERE, "Callable execution cancalled", e);
                }
            }
        };
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            log.severe("spawned thread interuppted");
        }
    }
}
