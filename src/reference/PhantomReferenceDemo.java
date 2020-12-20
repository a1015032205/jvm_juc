package reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-17 20:03
 * @Description: 虚引用
 */
//虚引用又称为幽灵引用，需要java.lang.ref.PhantomReference 类来实现
//顾名思义，就是形同虚设，与其他几种引用都不同，虚引用并不会决定对象的生命周期。
//如果一个对象持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收，它不能单独使用也不能通过它访问对象，虚引用必须和引用队列ReferenceQueue联合使用。
//虚引用的主要作用和跟踪对象被垃圾回收的状态，仅仅是提供一种确保对象被finalize以后，做某些事情的机制。
//PhantomReference的get方法总是返回null，因此无法访问对象的引用对象。其意义在于说明一个对象已经进入finalization阶段，可以被gc回收，用来实现比finalization机制更灵活的回收操作
//换句话说，设置虚引用关联的唯一目的，就是在这个对象被收集器回收的时候，收到一个系统通知或者后续添加进一步的处理，Java技术允许使用finalize()方法在垃圾收集器将对象从内存中清除出去之前，做必要的清理工作
//这个就相当于Spring AOP里面的后置通知
public class PhantomReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        // 创建引用队列
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        // 创建一个弱引用
        WeakReference<Object> reference = new WeakReference<>(o1, queue);

        // 创建一个虚引用
        //     PhantomReference<Object> weakReference = new PhantomReference<>(o1, queue);

        System.out.println(o1);//java.lang.Object@14ae5a5
        System.out.println(reference.get());//java.lang.Object@14ae5a5
        // 取队列中的内容
        System.out.println(queue.poll());//null

        o1 = null;
        System.gc();
        System.out.println("执行GC操作");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(o1);//null
        System.out.println(reference.get());//null
        // 取队列中的内容
        System.out.println(queue.poll());//java.lang.ref.WeakReference@7f31245a

        //从这里我们能看到，在进行垃圾回收后，我们弱引用对象，也被设置成null，
        // 但是在队列中还能够导出该引用的实例，这就说明在回收之前，该弱引用的实例被放置引用队列中了，我们可以通过引用队列进行一些后置操作
    }
}
