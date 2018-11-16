package threadPool;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

        ExecutorService executorService = new ThreadPoolExecutor(5,5, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(), threadFactory);


        PoolThread pt = new PoolThread();
        int size = 100;
        for (int i = 0; i < size; i++) {
//            int finalI = i;
//            executorService.execute(()->{
////                Thread.currentThread().setName("i" + (finalI + 1));
//                System.out.println(Thread.currentThread().getName());
//            });

            executorService.execute(new Thread(pt));
        }
        executorService.shutdown();

    }

    private static class PoolThread implements Runnable{


        @Override
        public void run() {

            System.out.println(Thread.currentThread().getName());

        }
    }
}
