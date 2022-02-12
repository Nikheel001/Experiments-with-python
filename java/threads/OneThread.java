/**
 * OneThread
 */
public class OneThread {

    public static void main(String[] args) {
        System.out.println("When a java program starts execution a main thread is spawned.");
        System.out.println("This program is currently executed by thread : "+Thread.currentThread().getName());

        // a class which implements Runnable interface
        OneTask task = new OneTask();
        task.run();
    }
}