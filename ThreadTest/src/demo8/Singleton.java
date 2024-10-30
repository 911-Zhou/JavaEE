package demo8;

//饿汉模式
public class Singleton {
    private static Singleton singleton = new Singleton();

    public static Singleton getSingleton() {
        return singleton;
    }

    private Singleton(){

    }
}
