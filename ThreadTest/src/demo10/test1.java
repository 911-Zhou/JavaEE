package demo10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class test1 {
    private static int sum = 0;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Object locker = new Object();

        executorService.submit(()->{
            for (int i = 0; i < 10000; i++) {
                Thread currentThread = Thread.currentThread();
                synchronized (locker) {
                    sum++;
                }
                System.out.println("当前" + currentThread.getName() + "++");
            }
        });

        executorService.submit(()->{
            for (int i = 0; i < 10000; i++) {
                Thread currentThread = Thread.currentThread();
                synchronized (locker) {
                    sum++;
                }
                System.out.println("当前" + currentThread.getName() + "++");
            }
        });

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();

        System.out.println("sum = " + sum);
    }
}
