package demo5;

public class test2 {
    static Object locker1 = new Object();
    static Object locker2 = new Object();
    static int num = 0;
    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
            synchronized (locker1){
                try {
                    Thread.sleep(1000);
                    System.out.println("thread1对locker1加锁完成");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("thread1开始对locker2加锁");
                synchronized (locker2){
                    try {
                        Thread.sleep(1000);
                        System.out.println("thread2对locker2加锁完成");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    num++;
                }
            }
        });

        Thread thread2 = new Thread(()->{
            synchronized (locker2){
                try {
                    Thread.sleep(1000);
                    System.out.println("thread2对locker2加锁完成");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("thread2开始对locker1加锁");
                synchronized (locker1){
                    try {
                        Thread.sleep(1000);
                        System.out.println("thread2对locker1加锁完成");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    num++;
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

        System.out.println(num);
    }
}
