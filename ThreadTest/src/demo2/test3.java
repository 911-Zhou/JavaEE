package demo2;

public class test3 {

    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            System.out.println("Lambda表达式新建线程!");
        });

        thread.start();
    }
}
