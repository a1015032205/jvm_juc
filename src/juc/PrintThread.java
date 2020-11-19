package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-19 20:54
 * @Description:
 */

public class PrintThread {

    private int num = 1;

    private final Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    private final Condition[] conditions = {condition1, condition2, condition3};


    public void print5() {
        String name = Thread.currentThread().getName();
        lock.lock();
        try {
            while (num != 1) {
                condition1.await();
            }
            Stream.iterate(1, i -> i + 1).limit(5).forEach(x -> System.out.println(name + "========>" + x));
            num = 2;
            condition2.signal();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void print10() {
        String name = Thread.currentThread().getName();
        lock.lock();
        try {
            while (num != 2) {
                condition2.await();
            }
            Stream.iterate(1, i -> i + 1).limit(10).forEach(x -> System.out.println(name + "========>" + x));
            num = 3;
            condition3.signal();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void print15() {
        String name = Thread.currentThread().getName();
        lock.lock();
        try {
            while (num != 3) {
                condition3.await();
            }
            Stream.iterate(1, i -> i + 1).limit(15).forEach(x -> System.out.println(name + "========>" + x));
            num = 1;
            condition1.signal();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print(int index) {
        String name = Thread.currentThread().getName();
        lock.lock();
        try {
            while (num != index) {
                System.out.println("休眠:" + getName(index));
                conditions[index - 1].await();
            }
            Stream.iterate(1, i -> i + 1).limit(5 * index).forEach(x -> System.out.println(name + "========>" + x));
            num = ++num > 3 ? 1 : num;
            System.out.println("唤醒:" + getName(num));
            conditions[num - 1].signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String getName(int index) {
        switch (index) {
            case 1:
                return "A";
            case 2:
                return "B";
            default:
                return "C";
        }
    }


}
