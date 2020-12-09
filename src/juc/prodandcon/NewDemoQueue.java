package juc.prodandcon;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-08 21:03
 * @Description:
 */

class Resource {
    private volatile Boolean flag = true;

    private AtomicInteger atomicInteger = new AtomicInteger();

    private BlockingQueue<String> queue;

    public Resource(BlockingQueue queue) {
        this.queue = queue;
        System.out.println(queue.getClass().getName());
    }

    public void open() {
        flag = false;
    }

    public void prod() throws InterruptedException {
        int data;
        boolean offer;
        while (flag) {
            data = atomicInteger.incrementAndGet();
            offer = queue.offer(String.valueOf(data), 2L, TimeUnit.SECONDS);
            if (offer) {
                System.out.println(Thread.currentThread().getName() + "\t" + "生产成功=====》" + data);
            } else {
                System.out.println( Thread.currentThread().getName()+ "生产失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"生产被叫停！！！！！");
    }

    public void consumer() throws InterruptedException {
        String poll;
        while (flag) {
            poll = queue.poll(2L, TimeUnit.SECONDS);
            if (null == poll || "".equalsIgnoreCase(poll)) {
                flag = false;
                System.out.println("消费2秒没有拿到东西！！退出消费！");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "消费成功=====》" + poll);
        }

    }
}

public class NewDemoQueue {
    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource(new ArrayBlockingQueue(10));
        new Thread(() -> {
            System.out.println("启动生产者.....");
            try {
                resource.prod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "pord").start();
        new Thread(() -> {
            System.out.println("启动消费者.....");
            try {
                resource.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "consumer").start();
        TimeUnit.SECONDS.sleep(5L);
        System.out.println("===============MAIN函数叫停==============");
        resource.open();
    }
}
