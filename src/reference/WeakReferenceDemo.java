package reference;

import java.lang.ref.WeakReference;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-16 21:12
 * @Description: 弱引用
 */
//不管内存是否够，只要有GC操作就会进行回收
//弱引用需要用 java.lang.ref.WeakReference 类来实现，它比软引用生存期更短
//对于只有弱引用的对象来说，只要垃圾回收机制一运行，不管JVM的内存空间是否足够，都会回收该对象占用的空间。

public class WeakReferenceDemo {
    public static void main(String[] args) {
        // 创建一个强应用
        Object o1 = new Object();
        // 创建一个软引用
        WeakReference<Object> reference = new WeakReference<>(o1);
        System.out.println(o1); //java.lang.Object@14ae5a5
        System.out.println(reference.get());//java.lang.Object@14ae5a5
        o1 = null;
        System.gc();
        System.out.println(o1);//NULL
        System.out.println(reference.get());//null

        //场景：假如有一个应用需要读取大量的本地图片
        //如果每次读取图片都从硬盘读取则会严重影响性能
        //如果一次性全部加载到内存中，又可能造成内存溢出
        //此时使用软引用可以解决这个问题
        //设计思路：使用HashMap来保存图片的路径和相应图片对象关联的软引用之间的映射关系，在内存不足时，JVM会自动回收这些缓存图片对象所占的空间，从而有效地避免了OOM的问题
        //Map<String, SoftReference<String>> imageCache = new HashMap<String, SoftReference<Bitmap>>();
    }


}
