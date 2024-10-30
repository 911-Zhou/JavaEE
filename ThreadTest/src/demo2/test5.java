package demo2;

public class test5 {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            System.out.println("Hello Thread");
        });

        System.out.println("线程状态：" + thread.getState());
        thread.start();

        System.out.println("线程ID："+thread.getId());
        System.out.println("线程Name：" + thread.getName());
        System.out.println("线程状态：" + thread.getState());
        System.out.println("线程优先级：" + thread.getPriority());
    }
}
