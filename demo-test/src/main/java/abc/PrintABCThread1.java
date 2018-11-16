package abc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 打印ABC线程
 *
 * @Author dell
 * @Create 2017-09-30 15:17
 */
public class PrintABCThread1 implements Runnable {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        while (true) {
            lock.lock();

            try {

                boolean flag = false;
                if (label == null) {
                    if ("A".equals(name)) {
                        flag = true;
                    }
                } else {
                    if ("A".equals(label) && "B".equals(name)) {
                        flag = true;
                    } else if ("B".equals(label) && "C".equals(name)) {
                        flag = true;
                    } else if ("C".equals(label) && "A".equals(name)){
                        flag = true;
                    }
                }

                if (flag) {
                    label = name;
                    System.out.print(name);
                    condition.signalAll();
                    return;
                } else {
                    condition.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }


    }

    public static void main(String[] args) {
        PrintABCThread1 abcRunnable = new PrintABCThread1();

        int group = 10;
        for (int i = 0; i < group; i++) {

            Thread a = new Thread(abcRunnable);
            a.setName("A");
            a.start();

            Thread b = new Thread(abcRunnable);
            b.setName("B");
            b.start();

            Thread c = new Thread(abcRunnable);
            c.setName("C");
            c.start();
        }

    }
}
