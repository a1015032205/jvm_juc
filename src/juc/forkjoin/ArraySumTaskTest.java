package juc.forkjoin;

import java.util.List;
import java.util.SplittableRandom;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-03 21:15
 * @Description:
 */
class SumTask extends RecursiveTask<Long> {

    static final int THRESHOLD = 2000;
    Long[] array;
    int start;
    int end;

    SumTask(Long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // 如果任务足够小,直接计算:
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("compute %d~%d = %d", start, end, sum));
            return sum;
        }
        // 任务太大,一分为二:
        int middle = (end + start) / 2;
        System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
        SumTask subtask1 = new SumTask(this.array, start, middle);
        SumTask subtask2 = new SumTask(this.array, middle, end);
        invokeAll(subtask1, subtask2);
        Long subresult1 = subtask1.join();
        Long subresult2 = subtask2.join();
        Long result = subresult1 + subresult2;
        System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
        return result;
    }
}

public class ArraySumTaskTest {
    public static void main(String[] args) throws Exception {
//现在选择随机数生成器，大多使用 ThreadLocalRandom 。它会产生更高质量的随机数，并且速度非常快。
        ThreadLocalRandom.current().nextInt(10);
        //而对于 Fork Join Pool 和并行 Stream，则使用 SplittableRandom。
        SplittableRandom random = new SplittableRandom();
        random.nextInt(10, 100);


        // 创建随机数组成的数组:
        List<Long> collect = Stream.generate(() -> new SplittableRandom().nextLong(0, 1000)).limit(4000).collect(Collectors.toList());
        Long[] array = collect.toArray(new Long[]{});
        int core = Runtime.getRuntime().availableProcessors();
        // fork/join task:
        ForkJoinPool fjp = new ForkJoinPool(core); // 最大并发数core
        ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
        long startTime = System.currentTimeMillis();
        Long result = fjp.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }
}
