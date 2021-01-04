package juc.threadDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-24 21:26
 * @Description:
 */
class MyThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("++++++++++");
        TimeUnit.SECONDS.sleep(4L);
        return "HELLO WORLD";
    }
}

public class MyCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(new MyThread());
        new Thread(task, "A").start();
        System.out.println(task.get());
    }
    //System.out.println(futureTask.get());
    //System.out.println(futureTask2.get());
    //1、一般放在程序后面，直接获取结果
    //2、只会计算结果一次
}
