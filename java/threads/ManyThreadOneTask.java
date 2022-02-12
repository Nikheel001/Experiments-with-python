/**
 * ManyThreadOneTask
 */
public class ManyThreadOneTask {

    public static void main(String[] args) {

        OneTask task = new OneTask();

        Thread[] arr = new Thread[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Thread(task);
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
