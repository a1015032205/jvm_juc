package oom;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-17 20:42
 * @Description: 堆溢出
 */

public class OutOfMemoryErrorDemo {
    public static void main(String[] args) {
        //  // 堆空间的大小 -Xms10m -Xmx10m
        byte[] bytes = new byte[1024 * 1024 * 30];//Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        System.out.println(bytes);
    }
}

