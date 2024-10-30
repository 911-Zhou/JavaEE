package test;

//线程安全单例模式
public class SingleTon {
    public static volatile SingleTon instance = null;

    private static Object locker = new Object();
    public static SingleTon GetSingleTon() {
        if(instance==null) {
            synchronized (locker) {
                if (instance == null) {
                    instance = new SingleTon();
                }
            }
        }
        return instance;
    }


}
