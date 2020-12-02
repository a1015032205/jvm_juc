package juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-27 21:07
 * @Description:
 */

class MyTask extends RecursiveTask<Integer> {

    private static final int NUM = 10;
    private int start;
    private int end;
    private int result;

    public MyTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        //太小了
        if (end - start <= NUM) {
            for (int i = start; i <= end; i++) {
                result += i;
            }
        } else {
            //中间值  50
            int middle = (end + start) / 2;
            MyTask myTask01 = new MyTask(start, middle);
            MyTask myTask02 = new MyTask(middle + 1, end);
            //fork就是递归调用compute
            myTask01.fork();
            myTask02.fork();
            result = myTask01.join() + myTask02.join();
        }
        return result;
    }
}

public class ForkJoinTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0, 100);
        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask<Integer> task = pool.submit(myTask);
        System.out.println(task.get());  //5050

        pool.shutdown();
    }
}
