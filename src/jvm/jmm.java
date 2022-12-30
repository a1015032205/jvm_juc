package jvm;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-17 23:29
 * @Description:
 */
class MyNumber {
    volatile int i = 10;

    public void updateNum() {
        this.i = 100;
    }
}

public class jmm {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "========START==========");
        MyNumber myNumber = new MyNumber();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "====come======");
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myNumber.updateNum();
            System.out.println(Thread.currentThread().getName() + "====update num ===>100   ======");
        }, "aaa").start();

        while (myNumber.i == 10) {

        }
        System.out.println(Thread.currentThread().getName() + "========END==========");
    }
}
