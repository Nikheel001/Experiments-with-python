package waitnotifytry;
import java.util.logging.Logger;

/**
 * A
 */
public class ReadTask implements Runnable {

    Logger log = WaitNotifyMain.log;

    public StringBuffer data;
    public boolean ready, done;

    public ReadTask(StringBuffer sb) {
        data = sb;
        ready = false;
        done = false;
    }

    public void finished() {
        log.info("data read successfully");
        done = true;
    }

    @Override
    public void run() {
        while (!done) {
            if (!ready) {
                synchronized (data) {
                    try {
                        log.info("waiting for data");
                        data.wait();
                        ready = true;
                    } catch (InterruptedException e) {
                        log.info(" : interrupted by parent thread");
                    }
                }
            } else {
                log.info("reading data : " + data.toString());
                finished();
            }
        }
    }
}
