package demo12;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class test2 {
    static int num = 0;
    public static void main(String[] args) {
        Object locker = new Object();
        Semaphore semaphore = new Semaphore(5);
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                semaphore.tryAcquire(1000);
                synchronized (locker) {
                    System.out.println("获取信号量" + ++num);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (locker) {
                    semaphore.release();
                    System.out.println("释放信号量：" + --num);
                }
            });
            executorService.submit(thread);
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("结束");
    }
}
