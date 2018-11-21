package threadPool;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * sdf
 *
 * @Author dell
 * @Create 2017-11-01 15:23
 */
public class ThreadPoolTest {

    public static void main(String[] args) {


//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ThreadFactory threadFactory = new BasicThreadFactory.Builder()
//                .namingPattern("pool-thread-%d") // 默认线程名 pool-%d-thread-%d
                .daemon(false) // true就是守护线程了, 此处应该是用户线程
                .build();

        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(), threadFactory);


        PoolThread pt = new PoolThread();
        int size = 10000;
        for (int i = 0; i < size; i++) {
//            int finalI = i;
//            executorService.execute(()->{
////                Thread.currentThread().setName("i" + (finalI + 1));
//                System.out.println(Thread.currentThread().getName());
//            });

            executorService.execute(new Thread(pt));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static class PoolThread implements Runnable{


        @Override
        public void run() {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());

        }
    }


}
