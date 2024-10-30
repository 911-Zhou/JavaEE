package test;

public class test7 {
    static int times = 0;
    static Object locker1 = new Object();
    static Object locker2 = new Object();
    static Object locker3 = new Object();
    static boolean mark1 = false;
    static boolean mark2 = false;
    static boolean mark3 = false;
    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            while (times<10) {
                synchronized (locker3) {
                    System.out.print("A");
                }
                while(mark1!=true);
                synchronized (locker1) {
                    mark1 = false;
                    locker1.notify();
                }
                try {
                    synchronized (locker3) {
                        mark3 = true;
                        locker3.wait();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            synchronized (locker1) {
                locker1.notify();
            }
        });
        Thread threadB = new Thread(() -> {
            while (times<9) {
                synchronized (locker1) {
                    try {
                        mark1 = true;
                        locker1.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.print("B");
                }
                while(mark2!=true);
                synchronized (locker2) {
                    mark2 = false;
                    locker2.notify();
                }
            }
            synchronized (locker2) {
                locker2.notify();
            }
        });
        Thread threadC = new Thread(() -> {
            Thread currentThread = Thread.currentThread();
            while (times<10) {
                synchronized (locker2) {
                    try {
                        mark2 = true;
                        locker2.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("C"+times);
                }
                while(mark3!=true);
                synchronized (locker3) {
                    times++;
                    mark3 = false;
                    locker3.notify();
                }
            }
            synchronized (locker3) {
                times++;
                locker3.notify();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
