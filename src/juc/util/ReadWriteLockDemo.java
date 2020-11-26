package juc.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-24 22:45
 * @Description:
 */
class MyMap {

    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) throws InterruptedException {
        readWriteLock.writeLock().lock();
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name + "====准备写入");
            TimeUnit.MILLISECONDS.sleep(300L);
            map.put(key, value);
            System.out.println(name + "====写入完成====" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void read(String key) throws InterruptedException {
        readWriteLock.readLock().lock();
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name + "准备读取");
            TimeUnit.MILLISECONDS.sleep(300L);
            Object o = map.get(key);
            System.out.println(name + "====读取完成====" + o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyMap myMap = new MyMap();
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                try {
                    myMap.put(name, finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                try {
                    myMap.read(name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
