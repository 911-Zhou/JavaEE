package demo10;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static java.lang.Thread.sleep;

class MyFixedThreadPool {
    private ArrayList<Thread> ThreadList = new ArrayList<>();
    private BlockingQueue<Runnable> runnableBlockingQueue = new ArrayBlockingQueue<>(1000);
    //初始化构造线程池
    public  MyFixedThreadPool(int N) {
        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(()->{
                Thread currentThread = Thread.currentThread();
                while(!currentThread.isInterrupted()){
                    try {
                        while(!runnableBlockingQueue.isEmpty()) {
                            Runnable runnable = runnableBlockingQueue.take();
                            runnable.run();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(currentThread.getName()+"线程结束");
            });
            thread.start();
            ThreadList.add(thread);
        }
    }

    public void submit(Runnable runnable){
        try {
            runnableBlockingQueue.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown(){
        ThreadList.forEach(i->{
            i.interrupt();
        });
    }

}

public class test2 {
    public static void main(String[] args) {
        MyFixedThreadPool myFixedThreadPool =  new MyFixedThreadPool(4);
        for (int i = 0; i < 2000; i++) {
            int j = i;
            myFixedThreadPool.submit(()->{
                int f = 0;
                Thread currentThread = Thread.currentThread();
                System.out.println(currentThread.getName() + "执行第"+j);
                for (int k = 0;k<4;k++){
                    f++;
                }
            });
        }

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        myFixedThreadPool.shutdown();
    }
}
