package redis;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2021-01-04 21:28
 * @Description: linkedhashmap
 */
@SuppressWarnings("all")
public class LruLinkedHashMapDemo<K, V> extends LinkedHashMap<K, V> {

    private int initialCapacity;
    private float loadFactor;
    private boolean accessOrder;

    public LruLinkedHashMapDemo(int initialCapacity, float loadFactor, boolean accessOrder) {
        super((int) Math.ceil(initialCapacity / 0.75) + 1, loadFactor, accessOrder);
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.accessOrder = accessOrder;
    }

    /**
     * <tt>true</tt> for access-order,  访问顺序
     * <tt>false</tt> for insertion-order  插入顺序
     */


    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > initialCapacity;
    }

    public static void main(String[] args) {
        //访问顺序
//        LruDemo01<Object, Object> lruDemo01 = new LruDemo01<>(3, 0.75F, true);
//        extracted(lruDemo01);
//
        //插入顺序
        LruLinkedHashMapDemo<Object, Object> lruDemo02 = new LruLinkedHashMapDemo<>(3, 0.75F, false);
        extracted(lruDemo02);
    }


    private static void extracted(LruLinkedHashMapDemo<Object, Object> lruDemo) {
        lruDemo.put(1, "a");
        lruDemo.put(2, "b");
        lruDemo.put(3, "c");

        System.out.println(lruDemo.keySet());//true==》[1, 2, 3]  false==》[1, 2, 3]
        lruDemo.put(4, "d");
        System.out.println(lruDemo.keySet());//true==》[2, 3, 4]  false==》[2, 3, 4]

        lruDemo.put(3, "c");
        System.out.println(lruDemo.keySet());//true==》[2, 4, 3]  false==》[2, 3, 4]
        lruDemo.put(3, "c");
        System.out.println(lruDemo.keySet());//true==》[2, 4, 3]  false==》[2, 3, 4]
        lruDemo.put(3, "c");
        System.out.println(lruDemo.keySet());//true==》[2, 4, 3]  false==》[2, 3, 4]

        lruDemo.put(5, "x");
        System.out.println(lruDemo.keySet());//true==》[4, 3, 5]  false==》[3, 4, 5]
    }
}
