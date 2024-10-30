package demo6;

import java.util.Scanner;

public class test1 {
    private static volatile int n=0;

    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
            while(n==0){
//                System.out.println(5);
            }
            System.out.println(n);
        });

        Thread thread2 = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Scanner scanner = new Scanner(System.in);
            n = 7;
        });

        thread1.start();
        thread2.start();
    }
}
