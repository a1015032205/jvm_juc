package juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-21 22:44
 * @Description:
 */

public class CompletableFutureTest {

    public static void main(String[] args) throws Exception {
        //   test02();
        // completableFutureWhenComplete();
        completableFutureExceptionally();
    }


    public static void test01() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100 * 10;
        });
        //立即获取方法结果，如果没有计算结束则返回传的值
        System.out.println(completableFuture.getNow(1));
        //和 get() 方法类似也是主动阻塞线程，等待计算结果。和get() 方法有细微的差别
        System.out.println(completableFuture.join());
        //该方法时阻塞方法，会等待计算结果完成
        System.out.println(completableFuture.get());
        //有时间限制的阻塞方法  超时报错
        System.out.println(completableFuture.get(1L, TimeUnit.SECONDS));
    }

    public static void test02() throws Exception {
        //以 Async 结尾并且没有 Executor 参数的，会默认使用 ForkJoinPool.commonPool()
        // 作为它的线程池执行异步代码。 以run开头的，因为以 Runable 类型为参数所以没有返回值。示例：
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> System.out.println("runAsync"));  //runAsync
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "supplyAsync");
        System.out.println(future1.get()); //null
        System.out.println(future2.get());//supplyAsync
    }

    /**
     * 参数类型为 BiConsumer<? super T, ? super Throwable> 会获取上一步计算的计算结果和异常信息。
     * 以Async结尾的方法可能会使用其它的线程去执行,如果使用相同的线程池，也可能会被同一个线程选中执行,以下皆相同。
     */
    public static void completableFutureWhenComplete() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                int i = 0 / 0;
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 20;
        }).whenCompleteAsync((v, e) -> {
            System.out.println(v); //20
            System.out.println(e);
        });
        System.out.println(future.get()); //20
    }

    /**
     * public CompletableFuture exceptionally(Function<Throwable,? extends T> fn)
     * 该方法是对异常情况的处理，当函数异常时应该的返回值
     *
     * @throws Exception
     */
    public static void completableFutureExceptionally() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10 / 0;
        }).whenCompleteAsync((v, e) -> {
            System.out.println(v);//NULL
            System.out.println(e); //java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        }).exceptionally((e) -> {
            System.out.println(e); //java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
            return 30;
        });
        System.out.println(future.get()); //30
    }

    /**
     * handle 方法和whenComplete方法类似，只不过接收的是一个 BiFunction<? super T,Throwable,? extends U> fn 类型的参数，因此有 whenComplete 方法和 转换的功能 (thenApply)
     *
     * @throws Exception
     */
    public static void completableFutureHand() throws Exception {

        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(() -> 10 / 0)
                .handle((t, e) -> {
                    System.out.println(e.getMessage());
                    return 10;
                });

        System.out.println(future.get());
    }

    /**
     * CompletableFuture 由于有回调，可以不必等待一个计算完成而阻塞着调用线程，
     * 可以在一个结果计算完成之后紧接着执行某个Action。我们可以将这些操作串联起来。
     *
     * @throws Exception
     */
    //这些方法不是马上执行的，也不会阻塞，而是前一个执行完成后继续执行下一个。
    //
    //和 handle 方法的区别是，handle 会处理正常计算值和异常，不会抛出异常。而 thenApply 只会处理正常计算值，有异常则抛出
    public static void completableFutureThenApply() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(() -> 1)
                .thenApply((a) -> {
                    System.out.println(a);//1
                    return a * 10;
                }).thenApply((a) -> {
                    System.out.println(a);//10
                    return a + 10;
                }).thenApply((a) -> {
                    System.out.println(a);//20
                    return a - 5;
                });
        System.out.println(future.get());//15
    }
}
