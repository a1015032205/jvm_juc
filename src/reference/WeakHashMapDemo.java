package reference;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-16 21:37
 * @Description:
 */

public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();
        System.out.println("===================================");
        myWeakHashMap();
    }

    private static void myHashMap() {
        Map<Integer, Object> map = new HashMap<>(4);
        Integer key = new Integer(1);
        String value = "myHashMap";
        map.put(key, value);
        System.out.println(map);//{1=myHashMap}
        key = null;
        System.out.println(map);//{1=myHashMap}
        System.gc();
        System.out.println(map + "\t" + map.size());//{1=myHashMap}	1
    }

    private static void myWeakHashMap() {
        Map<Integer, Object> map = new WeakHashMap<>(4);
        Integer key = new Integer(2);
        String value = "myWeakHashMap";
        map.put(key, value);
        System.out.println(map);//{2=myWeakHashMap}
        key = null;
        System.out.println(map);//{2=myWeakHashMap}
        System.gc();
        System.out.println(map + "\t" + map.size());//{}	0
    }


}
