package juc.threadPool;

import java.util.concurrent.*;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-08 22:02
 * @Description:
 */

class Tack implements Callable {
    @Override
    public Object call() throws Exception {
        String name = Thread.currentThread().getName();
        System.out.println(name + "====>" + System.currentTimeMillis());
        return "OK!!";
    }
}

/*Executors.newScheduledThreadPool(int corePoolSize)：

线程池支持定时以及周期性执行任务，创建一个corePoolSize为传入参数，最大线程数为整形的最大数的线程池

底层使用 ScheduledThreadPoolExecutor 来实现 ScheduledThreadPoolExecutor 为ThreadPoolExecutor子类*/

//

public class ScheduledThreadPoolExecutorDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10, Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        Tack tack = new Tack();
        //执行的任务 Callable或Runnable接口实现类
        //延时执行任务的时间
        // unit：延迟时间单位

        // ScheduledFuture schedule = executor.schedule(tack, 3L, TimeUnit.SECONDS);
        //  System.out.println(schedule.get());

        //执行的任务 Callable或Runnable接口实现类
        //第一次执行任务延迟时间
        //续执行任务之间的周期，从上一个任务开始执行时计算延迟多少开始执行下一个任务，但是还会等上一个任务结束之后。
        // unit：延迟时间单位
        ScheduledFuture schedule = executor.scheduleAtFixedRate(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + "====>" + System.currentTimeMillis());
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 3L, 1L, TimeUnit.SECONDS);
        Object o = schedule.get();
// 第一次是3秒后  后面每隔2秒执行一次


        //执行的任务 Callable或Runnable接口实现类
        //第一次执行任务延迟时间
        //连续执行任务之间的周期，从上一个任务全部执行完成时计算延迟多少开始执行下一个任务
        // unit：延迟时间单位
//        ScheduledFuture schedule = executor.scheduleWithFixedDelay(() -> {
//            String name = Thread.currentThread().getName();
//            System.out.println(name + "====>" + System.currentTimeMillis());
//            try {
//                TimeUnit.SECONDS.sleep(2L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }, 3L, 1L, TimeUnit.SECONDS);
//        Object o = schedule.get();
// 第一次是3秒后  后面每隔2+1秒执行一次
        executor.shutdown();
    }
}
