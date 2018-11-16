package abc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dell
 * @version 1.0.0
 * @create 2018-11-08 16:17
 */
public class PrintABCTest1 {

    public static void main(String[] args) {
        PrintABC abc = new PrintABC();
        for (int i = 0; i < 10; i++) {
            new Thread(abc, "C").start();
            new Thread(abc, "B").start();
            new Thread(abc, "A").start();
        }
//        System.out.println("ACBACCACCBBCABBABAABBABBCCACAC".length());
    }

    public static class PrintABC implements Runnable {
        private ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        int turn = 10;
        int count = 0;

        @Override
        public void run() {
            lock.lock();


            try {
                String name = Thread.currentThread().getName();
                if ("A".equals(name)) {
                    if (count % 3 != 0)
                        condition.wait();
                } else if ("B".equals(name)) {
                    if (count % 3 != 1)
                        condition.await();
                } else {
                    if (count % 3 != 2)
                        condition.await();
                }

                count++;
                System.out.print(name);
                condition.signalAll();
            } catch (
                    InterruptedException e)

            {
                e.printStackTrace();
            } finally

            {
                lock.unlock();
            }
        }
    }
}
