package juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-02 20:51
 * @Description: juc.cas
 */
/*
CAS的全称是Compare-And-Swap，它是CPU并发原语
它的功能是判断内存某个位置的值是否为预期值，如果是则更改为新的值，这个过程是原子的
*/
public class CasDemo {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        //默认是0
        System.out.println(atomicInteger.get());

        // 期望值，要修改值
        atomicInteger.compareAndSet(0, 100);
        atomicInteger.compareAndSet(0, 1000);
                                 //内存值，内存偏移量（内存地址0，期望值，要修改值
        //   return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
/*1.UnSafe
 是CAS的核心类 由于Java 方法无法直接访问底层 ,需要通过本地(native)方法来访问,UnSafe相当于一个后面,基于该类可以直接操作特额定的内存数据.UnSafe类在于sun.misc包中,其内部方法操作可以向C的指针一样直接操作内存,因为Java中CAS操作的助兴依赖于UNSafe类的方法.
注意UnSafe类中所有的方法都是native修饰的,也就是说UnSafe类中的方法都是直接调用操作底层资源执行响应的任务
 2.变量ValueOffset,便是该变量在内存中的偏移地址,因为UnSafe就是根据内存偏移地址获取数据的*/
        System.out.println(atomicInteger.get());//100
    }
}
