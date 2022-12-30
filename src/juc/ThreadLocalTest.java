package juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2022-05-17 19:30
 * @Description:
 */

public class ThreadLocalTest {

    private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getId());
        new Thread(() -> {
            new Thread(() -> {
                try {
                    s(thread);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
            new Thread(() -> {
                try {
                    s(thread);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        Thread.sleep(10000L);
   /*     ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                executorService.execute(()->opThreadLocal(getThreadName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            THREAD_LOCAL.remove();
        }

        executorService.shutdown();*/

    }

    public static void s(Thread thread) throws ExecutionException, InterruptedException {

        LockSupport.unpark(thread);
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(1);
        Future<String> submit = newFixedThreadPool.submit(() -> "123");
        submit.get();
        LockSupport.park();
        System.out.println("done");

    }

    private static void opThreadLocal(Long value) {

        THREAD_LOCAL.set(value);
        long threadName = getThreadName();
        Long result = THREAD_LOCAL.get();
        System.out.println("threadName:" + threadName + ",result:" + result);

    }

    private static long getThreadName() {
        return Thread.currentThread().getId();
    }
}
