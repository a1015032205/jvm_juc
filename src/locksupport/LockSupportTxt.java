package locksupport;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-23 22:00
 * @Description: 文档
 */

public class LockSupportTxt {
    //  LockSupport是用来创建锁和其他同步类的基本线程阻塞原语。
    //    下面这句话，后面详细说
    //    LockSupport中的park()和unpark()的作用分别是阻塞线程和解除阻塞线程

///LockSupport是用来创建锁和其他同步类的基本线程阻塞原语。
//LockSupport类使用了一种名为Permit(许可）的概念来做到阻塞和唤醒线程的功能，每个线程都有一个许可(permit),
//permit只有两个值1和零，默认是零。
//可以把许可看成是一种(0,1)信号量(Semaphore），但与Semaphore不同的是，许可的累加上限是1。


//permit默认是O，所以一开始调用park()方法，当前线程就会阻塞，直到别的线程将当前线程的permit设置为1时,park方法会被唤醒，
//然后会将permit再次设置为O并返回。

    //调用unpark(thread)方法后，就会将thread线程的许可permit设置成1(注意多次调用unpark方法，不会累加，permit值还是1)会自动唤醒thread线程，
    //即之前阻塞中的LockSupport.park()方法会立即返回。


    //LockSupport是用来创建锁和其他同步类的基本线程阻塞原语
    //LockSupport是一个线程阻塞工具类，所有的方法都是静态方法，可以让线程在任意位置阻塞，阻塞之后也有对应的唤醒方法。归根
    //结底，LockSupport调用的Unsafe中的native代码。
    //LockSupport提供park()和unpark()方法实现阻塞线程和解除线程阻塞的过程
    //LockSupport和每个使用它的线程都有一个许可(permit)关联。permit相当于1，0的开关，默认是0，
    //调用一次unpark就加1变成1，
    //调用一次park会消费permit，也就是将1变成o，同时park立即返回。
    //如再次调用park会变成阻塞(因为permit为零了会阻塞在这里，一直到permit变为1)，这时调用unpark会把permit置为1。
    //每个线程都有一个相关的permit, permit最多只有一个，重复调用unpark也不会积累凭证。
    //形象的理解
    //线程阻塞需要消耗凭证(permit)，这个凭证最多只有1个。
    //当调用park方法时
    //    *如果有凭证，则会直接消耗掉这个凭证然后正常退出;
    //    *如果无凭证，就必须阻塞等待凭证可用;
    //而unpark则相反，它会增加一个凭证，但凭证最多只能有1个，累加无效。





    //为什么可以先唤醒线程后阻塞线程?
    //因为unpark获得了一个凭证，之后再调用park方法，就可以名正言顺的凭证消费，故不会阻塞。
    //为什么唤醒两次后阻塞两次，但最终结果还会阻塞线程?
    //因为凭证的数量最多为1，连续调用两次unpark和调用一次unpark效果一样，只会增加一个凭证;
    //而调用两次park却需要消费两个凭证，证不够，不能放行。












    // Hotspot implementation via intrinsics API
    private static final sun.misc.Unsafe UNSAFE = null;

    //设置阻塞当前线程的操作者，一般保存阻塞操作的线程Thread对象
    //parkBlocker对象为线程Thread.class的属性parkBlocker
    private static void setBlocker(Thread t, Object arg) {
        // Even though volatile, hotspot doesn't need a write barrier here.
        UNSAFE.putObject(t, parkBlockerOffset, arg);
    }

    //唤醒给定线程；
    //1、若给定线程的许可（permit）不可用，则赋予给定线程许可；
    //2、若给定线程被park阻塞，则唤醒给定线程；
    //3、若给定线程正常运行，则本次unpark会保证下次针对该线程的park不会阻塞该线程
    //4、当线程还未启动，umpark操作不会有任何作用
    public static void unpark(Thread thread) {
        if (thread != null)
            UNSAFE.unpark(thread);
    }

    //阻塞当前当前线程，直到获得许可；
    //当许可可用时，park会立即返回;
    //当许可不可用时，当前线程将会阻塞，直到以下事件发生
    //1、其他线程调用unpark唤醒此线程
    //2、其他线程调用Thread#interrupt中断线程
    //3、调用应不可知错误返回
    //park方法不会报告是什么原因导致的调用返回
    //调用者需在返回时自行检查是什么条件导致调用返回
    public static void park(Object blocker) {
        Thread t = Thread.currentThread();
        setBlocker(t, blocker);
        UNSAFE.park(false, 0L);
        setBlocker(t, null);
    }

    //功能与park基本相同，此方法添加阻塞时间参数
    public static void parkNanos(Object blocker, long nanos) {
        if (nanos > 0) {
            Thread t = Thread.currentThread();
            setBlocker(t, blocker);
            UNSAFE.park(false, nanos);
            setBlocker(t, null);
        }
    }

    //功能与park基本相同，此方法添加阻塞到某个时间点的参数
    public static void parkUntil(Object blocker, long deadline) {
        Thread t = Thread.currentThread();
        setBlocker(t, blocker);
        UNSAFE.park(true, deadline);
        setBlocker(t, null);
    }

    //返回给定线程的parkBlocker参数
    public static Object getBlocker(Thread t) {
        if (t == null)
            throw new NullPointerException();
        return UNSAFE.getObjectVolatile(t, parkBlockerOffset);
    }

    private static final long parkBlockerOffset = 0;
}
