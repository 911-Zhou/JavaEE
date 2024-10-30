package test;

public class test3 {
    private static int sum = 0;

    public static void main(String[] args) {
        Object locker = new Object();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (locker) {
                    sum++;
                    System.out.println("线程1++");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (locker) {
                    sum++;
                    System.out.println("线程2++");
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("sum = " + sum);
    }
}
