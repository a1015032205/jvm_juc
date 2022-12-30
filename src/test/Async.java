package test;

import java.util.concurrent.*;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2022-02-19 15:33
 * @Description:
 */

public class Async {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService pool = new ThreadPoolExecutor(
                2,
                5,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(20),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        Future<Integer> submit = pool.submit(Async::sum);
        Integer result = submit.get();
        System.out.println("异步计算结果为：" + result);
        pool.shutdown();
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + "ms");
    }

    private static int sum() {
        return fibo(20);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}
