package java8;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-01 23:05
 * @Description:
 */

public class Group {

    public static void main(String[] args) {


        // 将Stream转换成容器或Map
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Map<String, String> collect3 = stream.collect(Collectors.toMap(String::toUpperCase, Function.identity()));
        System.out.println(collect3);
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("hepengju", "男", 20000));
        personList.add(new Person("lisi"    , "男", 40000));
        personList.add(new Person("wangwu"  , "男", 50000));
        personList.add(new Person("zhaoliu" , "男", 60000));
        personList.add(new Person("zhangsan", "男", 33333));
        personList.add(new Person("wgr", "男", 10000));
        Map<String, Person> collect = personList.stream().collect(Collectors.toMap(Person::getName, Function.identity()));
        collect.forEach((name,p) -> System.out.println(name + ":"+p));


        List<String> items = Arrays.asList("apple", "banana", "apple", "orange", "banana");
        //, Function.identity()
        Collector<Object, ?, Map<Object, Long>> objectMapCollector = Collectors.groupingBy(Function.identity(), Collectors.counting());
        Map<Object, Long> collectx = items.stream().collect(objectMapCollector);
        System.out.println(collectx);
        //{orange=1, banana=2, apple=2}


        List<Person> arrayList = new ArrayList<>();
        // 四个参与测试的小伙伴
        Person tom = new Person("tom", "男", 11);
        Person amy = new Person("amy", "女", 13);
        Person ali = new Person("ali", "男", 12);
        Person daming = new Person("daming", "男", 13);
        arrayList.add(tom);
        arrayList.add(amy);
        arrayList.add(ali);
        arrayList.add(daming);
        // 对小伙伴按照性别age进行分组
        Map<String, Set<String>> resultMap = arrayList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.mapping(Person::getName, Collectors.toSet())));
        System.out.println(resultMap.toString()); //{女=[amy], 男=[tom, daming, ali]}

        List<String> list = Stream.of("a", "bb", "cc", "ddd").collect(Collectors.toList());

        //根据长度分组
        Map<Integer, List<String>> listMap = list.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(listMap);//{1=[a], 2=[bb, cc], 3=[ddd]}

        //分组到自定义MAP
        TreeMap<Integer, List<String>> collect1 = list.stream().collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()));
        System.out.println(collect1);//{1=[a], 2=[bb, cc], 3=[ddd]}


        Map<Integer, TreeSet<String>> result = list.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toCollection(TreeSet::new)));
        System.out.println(result); // {1=[a], 2=[bb, cc], 3=[ddd]}

        //分组计数
        Map<Integer, Long> collect2 = list.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(collect2);


        //reducing缩减操作
//      list.stream()
//                .map(String::toCharArray)
//                .collect(Collectors.groupingBy(List::size, Collectors.reducing(new ArrayList<>(), (o1, o2) -> Stream.concat(Stream.of(o1.toString()), Stream.of(o2.toString())).collect(Collectors.groupingByConcurrent())))));
//        System.out.println(collect3);
    }
}
