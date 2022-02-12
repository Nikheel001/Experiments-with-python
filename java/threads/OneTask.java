/**
 * OneTask
 */
public class OneTask implements Runnable{

    @Override
    public void run() {
        System.out.println("Address: "+this+" : OneTask executed by : "+Thread.currentThread().getName());
    }   
}