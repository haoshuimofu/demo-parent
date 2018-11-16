package abc;

/**
 * @author dell
 * @version 1.0.0
 * @create 2018-11-12 10:28
 */
public class SyncPrintABCTest implements Runnable {

    int count = 0;
    int group = 10;

    @Override
    public synchronized void run() {
//        synchronized (this) {
        try {
            String name = Thread.currentThread().getName();
            for (int i = 0; i < group; i++) {
                if ("A".equals(name)) {
                    while (count % 3 != 0) {
                        this.wait();
                    }
                } else if ("B".equals(name)) {
                    while (count % 3 != 1) {
                        this.wait();
                    }
                } else if ("C".equals(name)) {
                    while (count % 3 != 2) {
                        this.wait();
                    }
                }
                count++;
                System.out.print(name);
                this.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//    }

    }

    public static void main(String[] args) {
        SyncPrintABCTest runnable = new SyncPrintABCTest();
        new Thread(runnable, "A").start();
        new Thread(runnable, "B").start();
        new Thread(runnable, "C").start();
    }
}