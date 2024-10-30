package demo3;

public class test5 {
    public static void main(String[] args) {
        //使用 lambda 表达式
        Thread thread = new Thread(()->{
            System.out.println("新建线程(使用 lambda 表达式)");
        });
        thread.start();
    }
}
