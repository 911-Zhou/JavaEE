package demo11;

import java.util.Comparator;
import java.util.PriorityQueue;

class Mytimertask {
    public long delay;
    private Runnable runnable;

    public Mytimertask(long delay, Runnable runnable) {
        this.delay = delay;
        this.runnable = runnable;
    }

    public void run() {
        runnable.run();
    }
}

class Mytimer {
    private PriorityQueue<Mytimertask> TaskQueue = new PriorityQueue<>(new Comparator<Mytimertask>() {
        @Override
        public int compare(Mytimertask o1, Mytimertask o2) {
            return (int) (o1.delay-o2.delay);
        }
    });

    private Object locker = new Object();

    public void schedule(Runnable runnable, long delay) {
        synchronized (locker) {
            TaskQueue.add(new Mytimertask(System.currentTimeMillis() + delay, runnable));
            locker.notify();
        }
    }

    public Mytimer() {
        Thread thread = new Thread(() -> {
            while(true) {
                synchronized (locker) {
                    while (TaskQueue.isEmpty()) {
                        try {
                            locker.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    Mytimertask mytimertask = TaskQueue.peek();

                    if (mytimertask.delay <= System.currentTimeMillis()) {
                        mytimertask.run();
                        TaskQueue.poll();
                    } else {
                        try {
                            locker.wait(mytimertask.delay - System.currentTimeMillis());
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
        thread.start();
    }
}

public class test1 {
    public static void main(String[] args) {
        Mytimer mytimer = new Mytimer();
        mytimer.schedule(()->{
            System.out.println("任务1延迟3000");
        },3000);
        mytimer.schedule(()->{
            System.out.println("任务2延迟2000");
        },2000);
        mytimer.schedule(()->{
            System.out.println("任务3延迟1000");
        },1000);

    }
}
