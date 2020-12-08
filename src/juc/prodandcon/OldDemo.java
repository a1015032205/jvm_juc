package juc.prodandcon;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-08 20:17
 * @Description: 一个初始为0的变量  两个线程对其交替操作  一个+1  一个-1  来5轮
 */

class Result {
    private int i;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void add() throws InterruptedException {
        lock.lock();
        try {
            while (i != 0) {
                condition.await();
            }
            String name = Thread.currentThread().getName();
            System.out.println(name + ++i);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void del() throws InterruptedException {
        lock.lock();
        try {
            while (i != 1) {
                condition.await();
            }
            String name = Thread.currentThread().getName();
            System.out.println(name + --i);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }


}

public class OldDemo {
    public static void main(String[] args) {
        Result result = new Result();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    result.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    result.del();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    result.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    result.del();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
