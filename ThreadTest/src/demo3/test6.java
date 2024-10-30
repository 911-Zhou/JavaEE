package demo3;

public class test6 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Thread(){
            @Override
            public void run() {
                System.out.println("1");
            }
        });
        thread.start();
        System.out.println("2");
    }
}
