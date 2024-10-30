package demo2;

class CreateThread extends Thread{
    @Override
    public void run() {
        System.out.println("通过继承Thread新建线程!");
    }
}

public class test1 {
    public static void main(String[] args) {
        Thread thread = new CreateThread();

        thread.start();

        System.out.println("main线程");
    }
}
