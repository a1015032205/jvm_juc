package juc.util;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-24 22:24
 * @Description:
 */

public class SemaphoreDemo {
    public static void main(String[] args) {
        //初始3个资源
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                try {
                    semaphore.acquire();  //资源减1
                    System.out.println(name + "抢到了资源");
                    TimeUnit.SECONDS.sleep(3L);//持有资源3秒
                    System.out.println(name + "释放了资源");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();  //释放资源

                }
            }, String.valueOf(i)).start();
        }
    }
}
