package waitnotifytry;
import java.util.logging.Logger;

/**
 * B
 */
public class WriteTask implements Runnable {

    Logger log = WaitNotifyMain.log;
    
    public StringBuffer data;

    public WriteTask(StringBuffer sb) {
        data = sb;
    }

    public void add(String text) {
        log.info("Adding Data");
        data.append(text);
        log.info("Notifying all waiting threads");
        synchronized (data) {
            data.notifyAll();
        }
    }

    @Override
    public void run() {
        try {
            log.info("Data will be added after 5 seconds please wait");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.info("-");
        }
        add("WELCOME HEADSHOT");
    }
}
