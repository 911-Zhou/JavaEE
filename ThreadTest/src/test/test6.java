package test;

public class test6 {
    static Object locker1 = new Object();
    static Object locker2 = new Object();
    static volatile boolean mark1 = false;
    static volatile boolean mark2 = false;
    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            synchronized (locker2) {
                try {
                    mark2 = true;
                    locker2.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Thread CurrentThread = Thread.currentThread();
                System.out.println("线程a");
            }
        });
        Thread b = new Thread(() -> {
            synchronized (locker1) {
                try {
                    mark1 = true;
                    locker1.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Thread CurrentThread = Thread.currentThread();
                System.out.println("线程b");
            }
            while(mark1!=true);
            synchronized (locker2) {
                locker2.notify();
            }
        });
        Thread c = new Thread(() -> {
            Thread CurrentThread = Thread.currentThread();
            System.out.println("线程c");
            while(mark1!=true);
            synchronized (locker1) {
                locker1.notify();
            }
        });

        a.start();
        b.start();
        c.start();

        try {
            a.join();
            b.join();
            c.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
