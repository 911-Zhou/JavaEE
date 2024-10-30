package demo1;

import static java.lang.Thread.sleep;

public class test5 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            Thread currentThread = Thread.currentThread();
            while(!currentThread.isInterrupted()) {
                System.out.println("新建线程");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //throw new RuntimeException(e);
                    break;
                }
            }
        });

        thread.start();

        System.out.println("main线程");

        sleep(3000);
        thread.interrupt();

        System.out.println(thread.isInterrupted());
    }
}
