package demo8;

//懒汉模式
public class SingletonLazy {
    public static volatile SingletonLazy singletonLazy = null;

    private static Object locker = new Object();

    public static SingletonLazy getSingletonLazy() {
        if(singletonLazy==null) {   //加锁消耗资源，这个null来判断是否加锁来解决不必要的加锁
            synchronized (locker) {
                if (singletonLazy == null) {
                    singletonLazy = new SingletonLazy();
                }
            }
        }
        return singletonLazy;
    }

    private SingletonLazy(){
        //执行资源初始化操作
    }
}
