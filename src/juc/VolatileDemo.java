package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-26 21:32
 * @Description:
 */
/*Volatile是Java虚拟机提供的轻量级的同步机制（三大特性）
保证可见性
不保证原子性
禁止指令重排*/

    /*JMM关于同步的规定：

线程解锁前，必须把共享变量的值刷新回主内存
线程解锁前，必须读取主内存的最新值，到自己的工作内存
加锁和解锁是同一把锁*/


/**
 * 假设是主物理内存
 */
class MyData {

    volatile int number = 0;
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addTo60() {
        this.number = 60;
    }

    public void add() {
        // i++不是原子操作
        number++;
    }

    public void atomicAdd() {
        // 相当于 atomicInter ++
        atomicInteger.getAndIncrement();
    }
}

/**
 * 验证volatile的可见性
 * 1. 假设int number = 0， number变量之前没有添加volatile关键字修饰
 */
public class VolatileDemo {

    public static void main(String args[]) {

        // 资源类
        MyData myData = new MyData();
        //可见性验证
        // visibility(myData);
        atomicity(myData);

    }

    private static void atomicity(MyData myData) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.add();
                    myData.atomicAdd();
                }
            }, String.valueOf(i)).start();
        }
        // 需要等待上面20个线程都计算完成后，在用main线程取得最终的结果值
        // 这里判断线程数是否大于2，为什么是2？因为默认是有两个线程的，一个main线程，一个gc线程
        while (Thread.activeCount() > 2) {
            // yield表示不执行
            Thread.yield();
        }
        System.out.println("volatile不保证原子性=======>" + myData.number);//19744
        System.out.println("AtomicInteger保证原子性=======>" + myData.atomicInteger);//20000
    }

    private static void visibility(MyData myData) {
        // AAA线程 实现了Runnable接口的，lambda表达式
        new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "\t come in");

            // 线程睡眠3秒，假设在进行运算
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 修改number的值
            myData.addTo60();

            // 输出修改后的值
            System.out.println(Thread.currentThread().getName() + "\t update number value:" + myData.number);

        }, "AAA").start();

        while (myData.number == 0) {
            // main线程就一直在这里等待循环，直到number的值不等于零
        }

        // 按道理这个值是不可能打印出来的，因为主线程运行的时候，number的值为0，所以一直在循环
        // 如果能输出这句话，说明AAA线程在睡眠3秒后，更新的number的值，重新写入到主内存，并被main线程感知到了
        System.out.println(Thread.currentThread().getName() + "\t mission is over");

        /**
         * 最后输出结果：
         * AAA     come in
         * AAA     update number value:60
         * 最后线程没有停止，并行没有输出  mission is over 这句话，说明没有用volatile修饰的变量，是没有可见性
         */}
}