package demo4;

public class test3 {
    private static StringBuffer str = new StringBuffer("sss");
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                str.append('s');
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                str.append('s');
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("字符串长度："+str.length());
    }
}
