package jvm;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-15 21:55
 * @Description: 死锁
 */
//死锁产生的四个必要条件
//互斥
//解决方法：把互斥的共享资源封装成可同时访问
//占有且等待
//解决方法：进程请求资源时，要求它不占有任何其它资源，也就是它必须一次性申请到所有的资源，这种方式会导致资源效率低。
//非抢占式
//解决方法：如果进程不能立即分配资源，要求它不占有任何其他资源，也就是只能够同时获得所有需要资源时，才执行分配操作
//循环等待
//解决方法：对资源进行排序，要求进程按顺序请求资源。
//死锁是指两个或多个以上的进程在执行过程中，因争夺资源而造成一种互相等待的现象，
// 若无外力干涉那他们都将无法推进下去。如果资源充足，进程的资源请求都能够得到满足，死锁出现的可能性就很低，否则就会因争夺有限的资源而陷入死锁。
public class DeadLock {
    private final static byte[] A = new byte[0];
    private final static byte[] B = new byte[0];


    public static void main(String[] args) {
        new Thread(() -> {
            try {
                demoA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAAAAAAAAAAAAA").start();
        new Thread(() -> {
            try {
                demoB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBBBBBBBBBBBBB").start();

    }


    public static void demoA() throws InterruptedException {
        synchronized (A) {
            String name = Thread.currentThread().getName();
            System.out.println("线程:" + name + "拿到锁A,尝试获取锁B");
            TimeUnit.SECONDS.sleep(1L);
            synchronized (B) {
                System.out.println("线程:" + name + "拿到锁B");
            }
        }
    }

    public static void demoB() throws InterruptedException {
        synchronized (B) {
            String name = Thread.currentThread().getName();
            System.out.println("线程:" + name + "拿到锁B,尝试获取锁A");
            TimeUnit.SECONDS.sleep(1L);
            synchronized (A) {
                System.out.println("线程:" + name + "拿到锁A");
            }
        }
    }
}
