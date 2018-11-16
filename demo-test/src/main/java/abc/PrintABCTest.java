package abc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Print ABC 测试类
 *
 * @Author dell
 * @Create 2017-11-10 15:02
 */
public class PrintABCTest {


    public static void main(String[] args) {
        PrintABC printABC = new PrintABC();

        Thread a = new Thread(printABC);
        a.setName("A");

        Thread b = new Thread(printABC);
        b.setName("B");

        Thread c = new Thread(printABC);
        c.setName("C");

        a.start();
        b.start();
        c.start();
    }
}


class PrintABC implements Runnable {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    int group = 10;
    int groupIndex = 0;
    int count = 0;

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        lock.lock();

        try {

            for (int i = 0; i < group; i++) {

                if ("A".equals(name)) {
                    while (count % 3 != 0) {
                        condition.await();
                    }
                } else if ("B".equals(name)) {
                    while (count % 3 != 1) {
                        condition.await();
                    }
                } else if ("C".equals(name)) {
                    while (count % 3 != 2) {
                        condition.await();
                    }
                }
                count++;
                if ("C".equals(name)) {
                    groupIndex++;
                }
                System.out.print(name + ("C".equals(name) ? ("--->" + groupIndex + "\n") : ""));
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
