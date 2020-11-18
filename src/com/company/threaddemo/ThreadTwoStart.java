package com.company.threaddemo;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-16 21:55
 * @Description:
 */

public class ThreadTwoStart {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        t1.start();
        t1.start();//Exception in thread "main" java.lang.IllegalThreadStateException
    }
}
