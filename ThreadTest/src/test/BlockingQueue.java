package test;

import java.util.AbstractQueue;
import java.util.LinkedList;
import java.util.PrimitiveIterator;
import java.util.Queue;

public class BlockingQueue {
    private Queue<Runnable> queue = new LinkedList<>();
    private final int MaxNum = 1000;
    private Object locker = new Object();
    //添加任务
    public void add(Runnable runnable) throws InterruptedException {
        synchronized (locker) {
            if (queue.size() == MaxNum) {
                locker.wait();
            }
        }
        queue.add(runnable);

    }
}
