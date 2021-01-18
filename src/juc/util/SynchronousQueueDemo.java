package juc.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author 秒度
 * @Email fangxin.md@Gmail.com
 * @Date 2020/11/26 5:34 下午
 * @Description SynchronousQueue
 */
public class SynchronousQueueDemo {
	public static void main(String[] args) {

		BlockingQueue blockingQueue = new SynchronousQueue();

		new Thread(() -> {
			try {
				System.out.println(Thread.currentThread().getName() + "\t put A ");
				blockingQueue.put("A");

				System.out.println(Thread.currentThread().getName() + "\t put B ");
				blockingQueue.put("B");

				System.out.println(Thread.currentThread().getName() + "\t put C ");
				blockingQueue.put("C");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "t1").start();


		new Thread(() -> {
			try {

				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				blockingQueue.take();
				System.out.println(Thread.currentThread().getName() + "\t take A ");

				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				blockingQueue.take();
				System.out.println(Thread.currentThread().getName() + "\t take B ");

				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				blockingQueue.take();
				System.out.println(Thread.currentThread().getName() + "\t take C ");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "t2").start();

	}
}
