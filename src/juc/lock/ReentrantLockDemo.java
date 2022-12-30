package juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-03 23:25
 * @Description: 可重入锁验证
 */

class Phone implements Runnable {
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "\t sendSMS");
        sendMail();
    }

    public synchronized void sendMail() {
        System.out.println(Thread.currentThread().getName() + "\t sendMail");
    }


    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    private void get() {
        //加锁2次  解锁2次  ok！！
        //加锁2次  解锁1次    T4线程会无法进入  因为T3未释放

        //lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t get");
            set();
        } finally {
            //  lock.unlock();
            lock.unlock();
            //加锁一次  释放2次  报错
            //Exception in thread "T3" Exception in thread "T4" java.lang.IllegalMonitorStateException
        }

    }

    private void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t set");
        } finally {
            lock.unlock();
        }
    }


}
//每个锁对象拥有一个锁计数器和一个指向持有该锁的线程的指针。
//当执行monitorenter时，如果目标锋对象的计数器为零，那么说明它没有被其他线程所持有，Java虚拟机会将该锁对象的持有线程设
//置为当前线程，并且将其计数器加i。
//在目标锁对象的计数器不为零的情况下，如果锁对象的持有线程是当前线程，那么Java虚拟机可以将其计数器加1，否则需要等待
//，直至持有线程释放该锁。
//当执行monitorexit时，Java虚拟机则需将锁对象的计数器减1。计数器为零代表锁已被释放。

public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(() -> phone.sendSMS(), "t1").start();
        new Thread(() -> phone.sendSMS(), "t2").start();


        TimeUnit.SECONDS.sleep(2L);

        Thread t3 = new Thread(phone, "T3");
        t3.start();
        //执行2次start 报错  因为 start以后会把 threadStatus==>0
        //Exception in thread "main" java.lang.IllegalThreadStateException
        //  t3.start();
        Thread t4 = new Thread(phone, "T4");
        t4.start();


        //线程创建出来就是new的状态。
        //start方法调用之后状态变成runnable,可以执行，如果cpu把时间片切过来就可以执行。
        //runnable执行完成则变成terminated，或者中断的时候。
        //runnable如果遇到有锁的方法，在等待的时候则是阻塞blocked的状态
        //如果调用wait或者join的方法，则变成waiting状态，
        //如果调用带时间参数的wait或者join,则变成time_waiting。
    }
}
