package demo2;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static java.lang.Thread.sleep;

public class test6 {
    static int i=0,j = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            Thread currentThread = Thread.currentThread();
            while(!currentThread.isInterrupted()) {
                System.out.println("线程1运行了" + i++ + "次");
            }
        });

        Thread thread2 = new Thread(()->{
            Thread currentThread = Thread.currentThread();
            while(!currentThread.isInterrupted()) {
                System.out.println("线程2运行了" + j++ + "次");
            }
        });
        thread1.setPriority(1);

        thread1.start();
        thread2.start();

        sleep(50000);

        thread1.interrupt();
        thread2.interrupt();
    }
}
