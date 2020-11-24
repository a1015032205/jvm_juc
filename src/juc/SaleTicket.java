package juc;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-18 20:03
 * @Description:
 */

public class SaleTicket {

    public static void main(String[] args) {
        //    Ticket ticket = new Ticket();
        PrintThread printThread = new PrintThread();
//2人售票
//        while (ticket.getCount() > 0) {
//            new Thread(ticket::send, "售票员A").start();
//            new Thread(ticket::send, "售票员B").start();
//        }

//双生产双消费
//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> ticket.increment()), "AAA").start();
//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> ticket.decrement()), "BBB").start();
//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> ticket.increment()), "CCC").start();
//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> ticket.decrement()), "DDD").start();


        /**
         *
         * @Description:
         * 多线程之间按顺序调用，实现A->B->C
         * 三个线程启动，要求如下：
         *
         * AA打印5次，BB打印10次，CC打印15次
         * 接着
         * AA打印5次，BB打印10次，CC打印15次
         * ......来10轮
         *
         */
        Vector vector = new Vector();
//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> printThread.print5()), "A").start();
//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> printThread.print10()), "B").start();
//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> printThread.print15()), "C").start();
        int corePoolSize = 10;
        int maximumPoolSize = 100;
        long keepAliveTime = 100;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(30);
        ThreadFactory threadFactory = new NameTreadFactory();
        RejectedExecutionHandler handler = new MyIgnorePolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
        executor.prestartAllCoreThreads(); // 预启动所有核心线程
        executor.execute(() -> Stream.generate(Random::new).limit(10).forEach(x -> printThread.print(1)));
        executor.execute(() -> Stream.generate(Random::new).limit(10).forEach(x -> printThread.print(2)));
        executor.execute(() -> Stream.generate(Random::new).limit(10).forEach(x -> printThread.print(3)));
//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> printThread.print(1)), "A").start();
//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> printThread.print(2)), "B").start();
//        new Thread(() -> Stream.generate(Random::new).limit(10).forEach(x -> printThread.print(3)), "C").start();


    }

    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
//          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }

}
