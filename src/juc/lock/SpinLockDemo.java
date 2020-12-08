package juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-07 21:10
 * @Description: 自选锁
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒，B随后进来发现当前有线程持有锁，不是null，
 * 所以只能通过自旋等待，直到A释放锁后B随后抢到
 */

public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        while (!atomicReference.compareAndSet(null, thread)) {
            System.out.println(name + "===正在自旋");
        }
        System.out.println(name + "===持有锁");
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        while (atomicReference.compareAndSet(thread, null)) {
            System.out.println(name + "释放锁");
        }


    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            spinLockDemo.myUnLock();
        }, "AA").start();

// 让main线程暂停1秒，使得t1线程，先执行
        TimeUnit.SECONDS.sleep(1L);

        new Thread(() -> {
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        }, "BB").start();

    }
}
