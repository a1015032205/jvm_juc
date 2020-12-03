package test;

import java.util.stream.Stream;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-03 22:34
 * @Description:
 */

public class Test {
    public static void main(String[] args) {
        //    Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]}).limit(20).map(n -> n[0]).forEach(System.out::println);
        System.out.println(Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]}).limit(20).mapToLong(n -> n[0]).sum());
    }
}
