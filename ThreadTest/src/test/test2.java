package test;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class test2 {
    static Object locker = new Object();

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> threadArrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                synchronized (locker) {
                    try {
                        locker.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(finalI);
                }
            });
            threadArrayList.add(thread);
            thread.start();
        }
        sleep(3000);
        synchronized (locker) {
            locker.notifyAll();
        }
        threadArrayList.forEach(i -> {
            try {
                i.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("OK");
    }
}
