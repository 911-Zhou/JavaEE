package demo1;

class ThreadTest extends Thread{
    private int times = 0;
    @Override
    public void run() {
        while (true) {
            System.out.println("新建线程"+times+"次执行");
            times++;
        }
    }

    public int getTimes() {
        return times;
    }
}

public class test1 {
    public static void main(String[] args) {
        int times = 0;
        ThreadTest threadTest = new ThreadTest();

        threadTest.start();

        while (true){
            times++;
            System.out.println("main线程第"+times+"次执行");
        }
    }
}
