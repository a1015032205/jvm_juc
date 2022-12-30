package juc.util;


import java.util.concurrent.CountDownLatch;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-24 21:48
 * @Description:
 */

public class CountDownLatchEnumDemo {

    public static void main(String[] args) throws Exception {
        /* * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
         * 其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
         * 当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。
         */
        //类似计数器  完成一个线程消耗一个  卡住主线程  不能多也不能少
        CountDownLatch downLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                System.out.println(name + "国被灭了");
                downLatch.countDown();
            }, CountryEnum.getByCode(i)).start();
        }
        downLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 秦王朝统一");
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
