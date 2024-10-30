package demo1;

public class test4 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("新建线程");
            }
        });

        thread.start();

        System.out.println("main线程");
    }
}
