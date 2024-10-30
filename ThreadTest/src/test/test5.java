package test;

public class test5 {
    public static void main(String[] args) {
        Object locker1 = new Object();
        Object locker2 = new Object();

        Thread thread1 = new Thread(()->{
           synchronized (locker1){
               System.out.println("线程1上锁locker1成功");
               synchronized (locker2){
                   System.out.println("线程1上锁locker2成功");
               }
           }
        });

        Thread thread2 = new Thread(()->{
            synchronized (locker2){
                System.out.println("线程2上锁locker2成功");
                synchronized (locker1){
                    System.out.println("线程2上锁locker1成功");
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
    }
}
