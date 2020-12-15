package jvm;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-15 22:57
 * @Description:
 */

public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("HelloGC");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
    //jps：查看java的后台进程
    //jinfo：查看正在运行的java程序
    //jps -l得到进程号
    //我们使用jinfo -flag 然后查看是否开启PrintGCDetails这个参数java -XX:+PrintFlagsInitial
    //jinfo -flag PrintGCDetails 12608
    //jinfo -flags ***
    //-Xms 等价于 -XX:InitialHeapSize ：初始化堆内存（默认只会用最大物理内存的64分1）
    //-Xmx 等价于 -XX:MaxHeapSize ：最大堆内存（默认只会用最大物理内存的4分1）
}
