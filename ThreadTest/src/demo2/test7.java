package demo2;

import static java.lang.Thread.sleep;

public class test7 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            Thread currentThread = Thread.currentThread();
            while(!currentThread.isInterrupted()) {
                System.out.println("Hello Thread!");
            }
        });


        thread.start();
        System.out.println("线程是否中断："+thread.isInterrupted());

        sleep(1500);

        thread.interrupt();

        System.out.println("线程是否中断：" + thread.isInterrupted());
    }
}
