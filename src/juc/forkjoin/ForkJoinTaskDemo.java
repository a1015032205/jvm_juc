package juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

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
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            //中间值  50
            int middle = (end + start) / 2;
            MyTask myTask01 = new MyTask(start, middle);
            MyTask myTask02 = new MyTask(middle + 1, end);
            //fork就是递归调用compute
            myTask01.fork();
            myTask02.fork();
            invokeAll(myTask01, myTask02);
            result = myTask01.join() + myTask02.join();
        }
        return result;
    }
}

/**
 * @author 秒度
 */
public class ForkJoinTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0, 100);
        int core = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(core);
        long start = System.currentTimeMillis();
        Integer invoke = pool.invoke(myTask);
        long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - start));
        System.out.println(invoke);  //5050
        //   System.out.println(task.get());  //5050
        pool.shutdown();
    }
}
