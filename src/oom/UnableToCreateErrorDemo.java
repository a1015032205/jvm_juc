package oom;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-17 21:36
 * @Description: 不能够创建更多的新的线程了，也就是说创建线程的上限达到了
 */
//高并发请求服务器时，经常会出现如下异常java.lang.OutOfMemoryError:unable to create new native thread，准确说该native thread异常与对应的平台有关
//导致原因：
//应用创建了太多线程，一个应用进程创建多个线程，超过系统承载极限
//服务器并不允许你的应用程序创建这么多线程，linux系统默认运行单个进程可以创建的线程为1024个，如果应用创建超过这个数量，就会报 java.lang.OutOfMemoryError:unable to create new native thread
//解决方法：
//想办法降低你应用程序创建线程的数量，分析应用是否真的需要创建这么多线程，如果不是，改代码将线程数降到最低
//对于有的应用，确实需要创建很多线程，远超过linux系统默认1024个线程限制，可以通过修改linux服务器配置，扩大linux默认限制
public class UnableToCreateErrorDemo {
    public static void main(String[] args) {
        //linux如何查看线程数  ulimit -u  不同权限的值不同
        //linux查看修改不同用户的线程数   root用户无上限    vi /etc/security/limits.d/20-nproc.conf
        for (int i = 0; ; i++) {
            System.out.println("************** i = " + i); //win==》i = 242114
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start(); //Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
        }
    }
}
