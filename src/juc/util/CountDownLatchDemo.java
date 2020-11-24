package juc.util;


import java.util.concurrent.CountDownLatch;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-24 21:48
 * @Description:
 */

public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception {
        //类似计数器  完成一个线程消耗一个  卡住主线程  不能多也不能少
        CountDownLatch downLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                System.out.println(name + "\t 开了教室");
                downLatch.countDown();
            }, String.valueOf(i)).start();

        }
        downLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 班长关门");
    }

    public void errorCode() {
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                System.out.println(name + "\t 开了教室");
            }, String.valueOf(i)).start();

        }
        System.out.println(Thread.currentThread().getName() + "\t 班长关门");
    }
}
