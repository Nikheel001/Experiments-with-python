/**
 * TwoThread
 */
public class TwoThread {

    public static void main(String[] args) {

        // 1. main thread
        System.out.println("executed by thread :" + Thread.currentThread().getName());

        OneTask task1 = new OneTask();

        // 2. thread-1
        // three ways to spawn new thread
        // way - 1
        Thread thread1 = new Thread(task1);

        execThread(thread1);

        // // way - 2
        // thread1 = new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //         System.out.println("Anonymous Task executed by : " + Thread.currentThread().getName());
        //     }
        // });

        // execThread(thread1);

        // // way - 3
        // thread1 = new Thread() {
        //     public void run() {
        //         System.out.println("Anonymous Thread.run() executed by : " + Thread.currentThread().getName());
        //     };
        // };

        // execThread(thread1);
    }

    static void execThread(Thread t) {
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println("thread Interrupted before it could finish execution");
        }
    }
}