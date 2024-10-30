package demo1;

class ThreadTest2 implements Runnable{

    @Override
    public void run() {
        while (true) {
//            System.out.println("新建线程");
        }
    }
}

public class test2 {
    public static void main(String[] args) {
        ThreadTest2 threadTest2 = new ThreadTest2();
        Thread thread =  new Thread(threadTest2);
        thread.start();
        System.out.println("新建线程ID" + thread.getId());
        while(true){
            System.out.println("main线程");
            break;
        }
    }
}
