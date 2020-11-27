package juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-25 20:44
 * @Description:
 */

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
