package juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
   /*BlockingQueue阻塞队列是属于一个接口，底下有七个实现类
ArrayBlockQueue：由数组结构组成的有界阻塞队列
LinkedBlockingQueue：由链表结构组成的有界（但是默认大小 Integer.MAX_VALUE）的阻塞队列
有界，但是界限非常大，相当于无界，可以当成无界
PriorityBlockQueue：支持优先级排序的无界阻塞队列
DelayQueue：使用优先级队列实现的延迟无界阻塞队列
SynchronousQueue：不存储元素的阻塞队列，也即单个元素的队列
生产一个，消费一个，不存储元素，不消费不生产
LinkedTransferQueue：由链表结构组成的无界阻塞队列
LinkedBlockingDeque：由链表结构组成的双向阻塞队列
这里需要掌握的是：ArrayBlockQueue、LinkedBlockingQueue、SynchronousQueue*/

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-25 20:44
 * @Description:
 */
/*抛出异常	当阻塞队列满时：在往队列中add插入元素会抛出 IIIegalStateException：Queue full 当阻塞队列空时：再往队列中remove移除元素，会抛出NoSuchException
特殊性	插入方法，成功true，失败false 移除方法：成功返回出队列元素，队列没有就返回空
一直阻塞	当阻塞队列满时，生产者继续往队列里put元素，队列会一直阻塞生产线程直到put数据or响应中断退出， 当阻塞队列空时，消费者线程试图从队列里take元素，队列会一直阻塞消费者线程直到队列可用。
超时退出	当阻塞队列满时，队里会阻塞生产者线程一定时间，超过限时后生产者线程会退出*/

public class ArrayBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
//        System.out.println(blockingQueue.add("A"));//底层是offer
//        System.out.println(blockingQueue.add("B"));
//        System.out.println(blockingQueue.add("C"));
        //   blockingQueue.add("X");// 队列满了   throw new IllegalStateException("Queue full");
//
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());  //底层是poll
        //   System.out.println(blockingQueue.remove()); //java.util.NoSuchElementException  为空会报错

        //     System.out.println(blockingQueue.element()); //返回队列首个元素

        //  System.out.println(blockingQueue.offer("A"));//  满了就是false
        //      System.out.println(blockingQueue.poll()); //没有返回null

//        blockingQueue.put("A"); //线程安全  队列满了就阻塞
//        blockingQueue.put("A");
//        blockingQueue.put("A");
        //  blockingQueue.put("A");
        //   blockingQueue.take(); //线程安全  队列空了就阻塞

        //   blockingQueue.offer("A", 3L, TimeUnit.SECONDS); //超时退出
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));// 空了后  会输出null


    }
}
