package test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2022-05-19 1:11
 * @Description:
 */

public class HashTest {
    public static void main(String[] args) {
        demo2();
    }

    public static void demo() {

        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 1; i <= 10000000; i++) {
            int id = i;
            if (i > 10000) {
                id = i * 8;
            }
            int index = id % 8;
            if (map.containsKey(index)) {
                Integer count = map.get(index);
                map.put(index, count + 1);
            } else {
                map.put(index, 0);
            }
        }

        System.out.println(map);
    }

    public static void demo2() {
        Map<Integer, Integer> map = new HashMap<>(16);
        int id = 8;
        for (int i = 1; i <= 123456789; i++) {
            int node = id % 200;
            int index = node % 8;
            if (map.containsKey(index)) {
                Integer count = map.get(index);
                map.put(index, count + 1);
            } else {
                map.put(index, 1);
            }
        }

        System.out.println("库下标--->数据量");
        System.out.println(map);
        Supplier<IntStream> callable = () -> map.values().stream().mapToInt(Integer::valueOf);
        int sum = callable.get().sum();
        int max = callable.get().max().getAsInt();
        int min = callable.get().min().getAsInt();
        System.out.println("最大差：" + (max - min));

        System.out.println("总数:" + sum);
    }
}
