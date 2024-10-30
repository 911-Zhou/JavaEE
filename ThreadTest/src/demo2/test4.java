package demo2;

public class test4 {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类创建线程!");
            }
        });

        thread.start();
    }
}
