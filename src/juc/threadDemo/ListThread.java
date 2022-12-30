package juc.threadDemo;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-24 20:36
 * @Description:
 */

public class ListThread {
    public static void main(String[] args) {
/*写时复制
     CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，
     而是先将当前容器Object[]进行Copy，复制出一个新的容器Object[] newElements，然后向新的容器Object[] newElements里添加元素。
     添加元素后，再将原容器的引用指向新的容器setArray(newElements)。
     这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
     所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
*/
        // 优化
//        List<String> list = new Vector<>();
        //  List<String> list = Collections.synchronizedList(new ArrayList<>());
        //一个人写 多人读  写的时候是copy一份  写完替换

     /*   Object[] elements = getArray();  那到当前数据
        int len = elements.length;  当前长度
        Object[] newElements = Arrays.copyOf(elements, len + 1);  复制一份  扩容+1
        newElements[len] = e;  写到最后
        setArray(newElements);  覆盖
        return true; */
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();// 底层就是CopyOnWriteArrayList
        TreeSet treeSet = new TreeSet();//===>NavigableMap
        HashSet<Object> objects = new HashSet<>();//===>HashMap
        // List<String> list = new ArrayList<>();
        //每次运行不一致
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        //扩容错误
        //Exception in thread "23" java.util.ConcurrentModificationException
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
    }
}
