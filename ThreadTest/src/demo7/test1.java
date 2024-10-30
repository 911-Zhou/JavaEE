package demo7;

public class test1 {
    static Object locker1 = new Object();
    static Object locker2 = new Object();
    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
            System.out.println("A");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (locker1){
                System.out.println("Thread1通知");
                locker1.notify();
            }
        });

        Thread thread2 = new Thread(()->{
            synchronized (locker1){
                try {
                    System.out.println("Thread2进入等待");
                    locker1.wait();
                    System.out.println("Thread2结束等待");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("B");
            }
            synchronized (locker2) {
                System.out.println("Thread2通知");
                locker2.notify();
            }
        });

        Thread thread3 = new Thread(()->{
            synchronized (locker2){
                try {
                    System.out.println("Thread3进入等待");
                    locker2.wait();
                    System.out.println("Thread3结束等待");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("C");
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
