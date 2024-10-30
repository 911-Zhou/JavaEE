package demo2;

class CreateThread2 implements Runnable{

    @Override
    public void run() {
        System.out.println("通过实现Runnable接口创建线程!");
    }
}

public class test2 {
    public static void main(String[] args) {
        CreateThread2 createThread2 = new CreateThread2();
        Thread thread = new Thread(createThread2);

        thread.start();

        System.out.println("main线程");
    }
}
