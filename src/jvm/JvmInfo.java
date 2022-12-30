package jvm;

import java.util.Random;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-17 21:16
 * @Description:
 */

public class JvmInfo {
    public static void main(String[] args) {
        //运行时数据区的抽象对象
        Runtime runtime = Runtime.getRuntime();
        int core = runtime.availableProcessors();
        System.out.println("CPU core size:" + core);
        //返回java虚拟机能使用的最大内存量
        long maxRaw = runtime.maxMemory();  //默认未是总内存的1/4  堆上限
        System.out.println("-Xmx：MAX_MEMORY：" + (maxRaw / (double) 1024 / 1024) + "MB");
        //返回java虚拟机中的内存总量     默认初始  1/64
        long total = runtime.totalMemory();
        System.out.println("-Xms：TOTAL_MEMORY：" + (total / (double) 1024 / 1024) + "MB");

        //byte[] bytes = new byte[40 * 1024 * 1024];
        String str = "MD";
        int i = 0;
        while (true) {
            System.out.println(++i);
            str += str + new Random().nextInt(8888888) + new Random().nextInt(999999999);
        }

    }
}
