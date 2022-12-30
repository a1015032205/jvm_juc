package oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-17 20:54
 * @Description: GC过多
 */

public class GcOverHeadLimitDemo {
    public static void main(String[] args) {
        // -Xms10m -Xmx10m -XX:+PrintGCDetails
        // -XX:MaxDirectMemorySize=5m （最大直接内存大小）
        int i = 0;
        List list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());//Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
            }
        } catch (Throwable e) {
            System.out.println("=================================i:" + i);//145387
            throw e;
        }
    }
}
