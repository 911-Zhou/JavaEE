package demo9;

/*
阻塞队列实现
 */
class MyBlockingQueue {
    private String queue[] = new String[1000];
    private int size = 0;
    private int head = 0;
    private int tail = 0;

    //存入
    public void put(String target) throws InterruptedException {
        synchronized (this) {
            if (size == queue.length) {
                System.out.println("put等待");
                this.wait();
            }
        }
        queue[tail] = target;
        tail++;
        size++;
        if (tail >= queue.length) {
            tail = 0;
        }
        synchronized (this) {
            this.notify();
        }
    }

    //取出
    public String get() throws InterruptedException {
        synchronized (this) {
            if (size == 0) {
                System.out.println("get等待");
                this.wait();
            }
        }
        String target = queue[head];
        head++;
        size--;
        if (head >= queue.length) head = 0;
        synchronized (this) {
            this.notify();
        }
        return target;
    }

    public int getSize(){
        return size;
    }
}

public class test1 {
    public static void main(String[] args) {
        Object locker = new Object();
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue();
        Thread thread1 = new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    myBlockingQueue.put("第" + i + "个元素");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("生产第" + i + "个元素");
                i++;
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("消费" + myBlockingQueue.get());
                    System.out.println("当前还有"+myBlockingQueue.getSize()+"个元素");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("消费" + myBlockingQueue.get());
                    System.out.println("当前还有"+myBlockingQueue.getSize()+"个元素");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.start();
        thread2.start();
//        thread3.start();
    }
}
