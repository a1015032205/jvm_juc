package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-18 19:59
 * @Description: 售票机  AB交替售票
 */

public class Ticket {

    private Integer size = 0;
    private Integer count = 100;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void send() {
        lock.lock();
        String name = Thread.currentThread().getName();
        try {
            if (count > 0) {
                //  notifyAll();
                System.out.println(name + "买走了第" + count-- + "张票");
                //   wait();
                //   notifyAll();
            }
        } finally {
            lock.unlock();
        }
    }



   /* public synchronized void increment() {
        String name = Thread.currentThread().getName();
        while (size == 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + "操作之后===》" + ++size);
        this.notifyAll();
    }

    public synchronized void decrement() {
        String name = Thread.currentThread().getName();
        while (size == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + "操作之后===》" + --size);
        this.notifyAll();
    }*/

    public void increment() {
        lock.lock();
        String name = Thread.currentThread().getName();
        try {
            while (size == 1) {
                condition.await();
            }
            System.out.println(name + "操作之后===》" + ++size);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        String name = Thread.currentThread().getName();
        try {
            while (size == 0) {
                condition.await();
            }
            System.out.println(name + "操作之后===》" + --size);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}



