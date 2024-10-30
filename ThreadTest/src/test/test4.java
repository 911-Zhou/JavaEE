package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class test4 {
    static int mc = 1;

    public static void main(String[] args) {
        Object locker = new Object();
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 8; i++) {
            Thread thread = new Thread(() -> {
                Thread currentThread = Thread.currentThread();
                long start = System.currentTimeMillis();
                int s = 0;
                while (s < 100) {
                    int t = (int) (Math.random() *11 + 7);
                    s += t;
                    System.out.println(currentThread.getName() + "跑了" + t + "m");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                long end = System.currentTimeMillis();
                System.out.println("第" + mc + "名是:" + currentThread.getName() + ";用时为：" + (end - start) + "ms");
                synchronized (locker) {
                    mc++;
                }
            });
            executorService.submit(thread);
        }

        try {
            sleep(1300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();
    }
}
