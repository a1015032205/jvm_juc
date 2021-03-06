package juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-27 21:07
 * @Description:
 */

class MyTask extends RecursiveTask<Long> {

    private static final long NUM = 100000;
    private long start;
    private long end;
    private long result;

    public MyTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        //太小了
        if (end - start <= NUM) {
            for (long i = start; i <= end; i++) {
                result += i;
            }
//            try {
//                TimeUnit.SECONDS.sleep(1L);
//            } catch (InterruptedException e) {
//                e.prlongStackTrace();
//            }
        } else {
            //中间值  50
            long middle = (end + start) / 2;
            MyTask myTask01 = new MyTask(start, middle);
            MyTask myTask02 = new MyTask(middle + 1, end);
            //fork就是递归调用compute
           // myTask01.fork();
            //myTask02.fork();
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
        MyTask myTask = new MyTask(0, 1000000000);
        int core = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(core);
        long start = System.currentTimeMillis();
        Long invoke = pool.invoke(myTask);
        long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - start));
        System.out.println(invoke);  //5050
        //   System.out.prlongln(task.get());  //5050
        pool.shutdown();
    }
}
