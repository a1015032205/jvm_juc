package juc.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-24 22:11
 * @Description:
 */

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //等待7个线程完后  执行Runnable
        CyclicBarrier barrier = new CyclicBarrier(7, () -> System.out.println("完成"));

        for (int i = 0; i < 7; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "完成" + finalI);
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
