package demo3;

//实现 Runnable, 重写 run
class ThreadTest2 implements Runnable{

    @Override
    public void run() {
        System.out.println("新建线程(实现 Runnable, 重写 run)");
    }
}
public class test2 {
    public static void main(String[] args) {
        ThreadTest2 threadTest2 = new ThreadTest2();
        Thread thread = new Thread(threadTest2);

        thread.start();
    }
}
