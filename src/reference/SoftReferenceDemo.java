package reference;

import java.lang.ref.SoftReference;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-16 21:12
 * @Description: 软引用
 */
//软引用是一种相对弱化了一些的引用，需要用Java.lang.ref.SoftReference类来实现，可以让对象豁免一些垃圾收集，对于只有软引用的对象来讲：
//当系统内存充足时，它不会被回收
//当系统内存不足时，它会被回收
//软引用通常在对内存敏感的程序中，比如高速缓存就用到了软引用，内存够用 的时候就保留，不够用就回收

public class SoftReferenceDemo {
    public static void main(String[] args) {
        // softRefMemoryEnough();
        softRefMemoryNoEnough();
    }

    /**
     * 内存足够
     */
    public static void softRefMemoryEnough() {
        // 创建一个强应用
        Object o1 = new Object();
        // 创建一个软引用
        SoftReference<Object> reference = new SoftReference<>(o1);
        System.out.println(o1); //java.lang.Object@14ae5a5
        System.out.println(reference.get());//java.lang.Object@14ae5a5
        o1 = null;
        System.gc();
        System.out.println(o1);//NULL
        System.out.println(reference.get());//java.lang.Object@14ae5a5

    }

    /**
     * JVM配置，故意产生大对象并配置小的内存，让它的内存不够用了导致OOM，看软引用的回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRefMemoryNoEnough() {
        // 创建一个强应用
        Object o1 = new Object();
        // 创建一个软引用
        SoftReference<Object> reference = new SoftReference<>(o1);
        System.out.println(o1); //java.lang.Object@14ae5a5
        System.out.println(reference.get());//java.lang.Object@14ae5a5
        o1 = null;

        try {
            // 创建10M的大对象
            // 模拟OOM自动GC
            byte[] bytes = new byte[1024 * 1024 * 10];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1);//NULL
            System.out.println(reference.get());//NULL
        }

    }
}
