package test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-03 22:34
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        int i = 0;
        while (true) {
            System.out.println("================>" + i);
            Map<String, Object> map = new HashMap<>(16);
            map.put(String.valueOf(i++), new byte[1024 * 500]);
        }
        //    Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]}).limit(Long.MAX_VALUE).map(n -> n[0]).forEach(System.out::println);
        // System.out.println(Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]}).limit(20).mapToLong(n -> n[0]).sum());
    }
}
