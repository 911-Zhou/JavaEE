package demo12;

import javax.sound.midi.Soundbank;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test1 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CountDownLatch countDownLatch = new CountDownLatch(20);
        
        for (int i = 0; i < 20; i++) {
            System.out.println("线程任务："+i);
            int number = i;
            executorService.submit(()->{
                System.out.println("下载线程"+number+"开始执行");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("下载线程"+number+"结束执行");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("所有任务下载完毕");
    }
}
