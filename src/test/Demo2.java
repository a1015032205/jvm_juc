package test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2022-02-19 15:47
 * @Description:
 */

public class Demo2 {

    public static void main(String[] args) {
        String a = "中国";
        String b = "中国 浙江";
        String f = "中国 广东";
        String c = "中国 浙江 杭州";
        String e = "中国 浙江 宁波";
        String g = "中国 广东 东莞";
        String d = "中国 浙江 杭州 余杭区";

        List<List<String>> collect = Stream.of(a, b, c, d, e, f, g).map(x -> Arrays.asList(x.split(" "))).sorted((o1, o2) -> o2.size() - o1.size()).collect(Collectors.toList());
        Map<String, Object> result;

        if (!collect.isEmpty()) {
            result = getLastMap(collect);
            collect.remove(collect.size() - 1);
            for (List<String> list : collect) {
                int size = list.size();
                result.values();
                for (int i = 0; i < list.size(); i++) {
                    String s = list.get(i);
                    boolean flag = result.get(s) == null;
                    if (flag) {
                        result.put(s, new HashMap<>(2));
                    }
                }
            }
        }


        Map<Integer, List<List<String>>> map = Stream.of(a, b, c, d, e, f, g).map(x -> Arrays.asList(x.split(" "))).sorted(Comparator.comparingInt(List::size)).collect(Collectors.groupingBy(List::size));


    }

    private static Map<String, Object> getLastMap(List<List<String>> collect) {
        List<String> lastList = collect.get(0);
        Map<String, Object> map = new HashMap<>(2);
        for (int i = lastList.size() - 1; i >= 0; i--) {
            if (i == lastList.size() - 1) {
                map.put(lastList.get(i), new HashMap<>(2));
            } else {
                Map<String, Object> m = new HashMap<>(2);
                m.put(lastList.get(i), map);
                map = m;
            }
        }
        return map;
    }


}
