package juc.lock;

import juc.util.CountryEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-07 21:57
 * @Description:
 */

public class ReentrantReadWriteLockDemo {
    private volatile Map<String, Object> map = new HashMap<>(16);

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void write(String k, String v) {
        lock.writeLock().lock();

        try {
            String name = Thread.currentThread().getName();
            System.out.println(name + "准备写入");
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(k, v);
            System.out.println(name + "写入成功！！！！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }

    }

    public void read(String k) {
        lock.readLock().lock();
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name + "准备读取");
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "读取成功===》" + map.get(k));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }


    public static void main(String[] args) {
        ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                demo.write(String.valueOf(finalI), String.valueOf(finalI));
            }, String.valueOf(i)).start();

        }

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                int finalI = i;
                new Thread(() -> demo.read(String.valueOf(finalI)), String.valueOf(i)).start();
            }
        }).start();

//        for (int i = 0; i < 5; i++) {
//            int finalI = i;
//            new Thread(() -> demo.read(String.valueOf(finalI)), String.valueOf(i)).start();
//        }
    }
}
