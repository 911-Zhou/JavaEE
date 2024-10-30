package demo3;

public class test4 {
    public static void main(String[] args) {
        //实现 Runnable, 重写 run, 使用匿名内部类
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("新建线程(实现 Runnable, 重写 run, 使用匿名内部类)");
            }
        });
        thread.start();
    }
}
