package demo4;

public class test4 {
    private static int num = 0;
    public static void main(String[] args) {
        Object locker = new Object();
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                synchronized (locker){
                    num++;
                }
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                synchronized (locker){
                    num++;
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(num);
    }
}
