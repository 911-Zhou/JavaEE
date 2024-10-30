package test;

import java.util.Random;

public class test1 {
    static Object locker = new Object();
    static long res = 0;
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        long start,end;
        Thread thread1 =  new Thread(()->{
                    for(int i = 1;i<=10000000;i+=2){
                            int num = random.nextInt(100)+1;
                            synchronized (locker){
                                res+=num;
                            }
                    }
        });

        Thread thread2 =  new Thread(()->{
                    for(int i = 2;i<=10000000;i+=2){
                            int num = random.nextInt(100)+1;
                            synchronized (locker){
                                res+=num;
                            }
                    }
        });

        start = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        end = System.currentTimeMillis();

        System.out.println("结果："+res);
        System.out.println("总用时："+(end-start)+"ms");
    }
}
