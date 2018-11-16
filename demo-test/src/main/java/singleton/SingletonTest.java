package singleton;

/**
 * 单例模式测试
 *
 * @Author rex.wu
 * @Create 2017-10-26 11:24
 */
public class SingletonTest {

    public static void main(String[] args) {

        SingletonThread st = new SingletonThread();
        Thread t1 = new Thread(st);
        Thread t2 = new Thread(st);
        Thread t3 = new Thread(st);
        Thread t4 = new Thread(st);
        Thread t5 = new Thread(st);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

}

class SingletonThread implements Runnable{

    @Override
    public void run() {

        System.out.println(Singleton.getSingletonInstance().hashCode());

    }
}
