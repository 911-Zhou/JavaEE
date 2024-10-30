package demo3;
public class test3 {
    public static void main(String[] args) {
        //继承 Thread, 重写 run, 使用匿名内部类
        Thread thread = new Thread(new Thread(){
            @Override
            public void run() {
                System.out.println("新建线程(继承 Thread, 重写 run, 使用匿名内部类)");
            }
        });
        thread.start();
    }
}
