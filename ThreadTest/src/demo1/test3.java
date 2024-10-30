package demo1;

public class test3 {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true) {
//                    System.out.println("新建线程");
                }
            }
        };

        thread.start();
        System.out.println("新建线程ID" + thread.getId());
        while(true){
//            System.out.println("main线程");
//            break;
        }
    }
}
