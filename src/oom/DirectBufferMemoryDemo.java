package oom;

import java.nio.ByteBuffer;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-17 21:11
 * @Description: 直接内存溢出
 */

public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("可使用直接内存大小：" + (sun.misc.VM.maxDirectMemory() / (double) 1024 / 1024) + "MB");//32G==>7264.0MB
//我们使用 -XX:MaxDirectMemorySize=5m 配置能使用的堆外物理内存为5M
        //-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
        //然后我们申请一个6M的空间
        //   ByteBuffer allocate = ByteBuffer.allocate(6 * 1024 * 1024);//分配的事jvm堆内存  属于GC管辖  需要内存拷贝  速度慢


        //  Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024); //直接分配OS本地内存  不属于GC管辖范围  所以不需要内存拷贝  速度快
    }
}
