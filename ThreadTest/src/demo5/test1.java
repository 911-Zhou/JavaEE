package demo5;

public class test1 {
    static int num = 0;
    static Object locker = new Object();

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            synchronized (locker) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (locker) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (locker) {
                        num++;
                    }
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(num);
    }
}
