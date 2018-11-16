package threadPool;

/**
 * 守护线程测试
 *
 * @author wude
 * @create 2017-11-02 15:11
 */
public class DaemonThreadTest implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + "running");
        }
    }

    public static void main(String[] args) {
        DaemonThreadTest d = new DaemonThreadTest();
        Thread t = new Thread(d);
        // 设置后台线程
        t.setDaemon(true);
        t.setName("后台线程");
        t.start();
        // 一个进程只有后台线程在运行时，这个进程就会停止
        while (true) {
            System.out.println("gansu");
        }
    }


}
