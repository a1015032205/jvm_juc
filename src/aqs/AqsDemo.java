package aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-24 22:58
 * @Description:
 */

public class AqsDemo {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

//银行1个窗口  三个客户  A先抢到  办理业务  BC进入阻塞  等待A解锁
        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(20L);
                System.out.println("======A thread come in=====");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "A").start();


        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("======B thread come in=====");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "B").start();


        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("======C thread come in=====");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "C").start();

    }
}
