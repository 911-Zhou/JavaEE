package demo3;

//继承thread 重写run
class  ThreadTest1 extends Thread{
    @Override
    public void run() {
        System.out.println("新建线程！");
    }
}

public class test1 {
    public static void main(String[] args) {
        Thread thread = new ThreadTest1();
        thread.start();
    }
}
