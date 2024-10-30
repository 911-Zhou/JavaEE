package demo4;

import static java.lang.Thread.sleep;

public class test1 {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            for (int i = 0; i < 7; i++) {
                System.out.println("线程等待："+i);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();

        try {
            System.out.println("线程开始等待");
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("main线程等待结束");
    }
}
