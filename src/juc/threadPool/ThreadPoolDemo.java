package juc.threadPool;

import java.util.concurrent.*;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-25 21:30
 * @Description:
 */

public class ThreadPoolDemo {
    public static void main(String[] args) {
        /*
        * 1）FixedThreadPool和SingleThreadPool:允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
        2）CachedThreadPool:允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM*/

        //  ExecutorService pool = Executors.newFixedThreadPool(5);//模拟银行5个柜台
        //  ExecutorService pool = Executors.newSingleThreadExecutor();//模拟银行1个柜台
        //   ExecutorService pool = Executors.newCachedThreadPool();//模拟银行N个柜台
        // ExecutorService pool = Executors.newScheduledThreadPool(1);
        ExecutorService pool = new ThreadPoolExecutor(
                2, //常驻核心数
                5,  //最大线程数
                3L, //最大空闲时间
                TimeUnit.SECONDS, //时间单位
                new LinkedBlockingQueue<>(3), //阻塞队列
                Executors.defaultThreadFactory(), //线程工程
                //java.util.concurrent.RejectedExecutionException: Task juc.ThreadPoolDemo$$Lambda$1/558638686@6d03e736 rejected from
                // java.util.concurrent.ThreadPoolExecutor@568db2f2[Running, pool size = 5, active threads = 5, queued tasks = 3, completed tasks = 0]
                //      new ThreadPoolExecutor.AbortPolicy() //拒绝策略  抛异常

                //  new ThreadPoolExecutor.CallerRunsPolicy()// 不抛弃  不报错  回退给调用者执行
                //new ThreadPoolExecutor.DiscardPolicy()// 默默丢弃  不通知  不异常  如果允许消息丢失
                new ThreadPoolExecutor.DiscardOldestPolicy()// 抛弃等待队列中等待最久的任务 让把当前任务加入队列  再次尝试提交当前任务

        );

        try {
            for (int i = 0; i < 90; i++) {
//                TimeUnit.SECONDS.sleep(1L);
                pool.execute(() -> System.out.println(Thread.currentThread().getName() + "===>处理"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }

    /*在创建了线程池后，等待提交过来的任务请求

当调用execute()方法添加一个请求任务时，线程池会做出如下判断

如果正在运行的线程池数量小于corePoolSize，那么马上创建线程运行这个任务
如果正在运行的线程数量大于或等于corePoolSize，那么将这个任务放入队列
如果这时候队列满了，并且正在运行的线程数量还小于maximumPoolSize，那么还是创建非核心线程like运行这个任务；
如果队列满了并且正在运行的线程数量大于或等于maximumPoolSize，那么线程池会启动饱和拒绝策略来执行
当一个线程完成任务时，它会从队列中取下一个任务来执行

当一个线程无事可做操作一定的时间(keepAliveTime)时，线程池会判断：

如果当前运行的线程数大于corePoolSize，那么这个线程就被停掉
所以线程池的所有任务完成后，它会最终收缩到corePoolSize的大小*/
}
