package redis;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2021-01-05 20:19
 * @Description: 手写LRU
 */


public class LruDemo {

    private final int cacheSize;
    private final Map<Object, Node<Object, Object>> map;
    private final DoubleLinkedList<Object, Object> doubleLinkedList;

    //map负责查找，构建一个虚拟双向链表，它里面安装的就是一个个Node节点，作为数据载体
//1.构造一个node节点作为数据载体
    static class Node<K, V> {

        K key;

        V value;
        //前指针
        Node<K, V> prev;
        //后指针
        Node<K, V> next;

        public Node() {
            this.prev = this.next = null;//初始化
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = this.next = null;
        }
    }

    //2.构造一个虚拟的双向链表，里面安放的就是我们的node
    static class DoubleLinkedList<K, V> {
        Node<K, V> head;//头节点
        Node<K, V> tail;//尾节点

        /**
         * 构造方法 初始化 首尾相连
         */
        public DoubleLinkedList() {
            this.head = new Node<>();
            this.tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        //3.添加到头
        public void addHead(Node<K, V> node) {
            node.next = head.next; //当前传进来节点的下一个节点指定头结点的下一个节点 也就是尾节点
            node.prev = head;//当前节点的前指针，指向头节点
            head.next.prev = node;//头结点的下一个节点（尾节点）的前一个节点指向当前节点
            head.next = node;//头结点的下一个节点指向当前节点
        }

        //4.删除节点
        public void removeNode(Node<K, V> node) {
            node.next.prev = node.prev;//这个节点的后一个节点的前节点 指向 这个节点的前节点
            node.prev.next = node.next;//这个节点的前节点的后一个节点 指向 这个节点的后一个节点
            node.prev = null;//这个节点的前指针 指向null
            node.next = null;//这个节点的  后指针  指向null
        }

        //5.获得最后一个节点
        public Node<K, V> getLast() {
            return tail.prev;
        }

    }


    public LruDemo(int cacheSize) {
        this.cacheSize = cacheSize;//坑位
        map = new HashMap<>(16);//查找
        doubleLinkedList = new DoubleLinkedList<>();
    }

    public Object get(Object key) {
        if (!map.containsKey(key)) {
            return null;
        }
        //有人使用  应该提升到头部  使用顺序
        Node<Object, Object> node = map.get(key);
        doubleLinkedList.removeNode(node); //从链表删掉
        doubleLinkedList.addHead(node);//加到头部
        return node.value;
    }

    public void put(Object key, Object value) {
        if (map.containsKey(key)) {//update
            Node<Object, Object> node = map.get(key);
            node.value = value;
            map.put(key, node);
        } else {
            if (map.size() == cacheSize) {//坑位满了  删除最久没有使用的
                Node<Object, Object> lastNode = doubleLinkedList.getLast();
                map.remove(lastNode.key);
                doubleLinkedList.removeNode(lastNode);
            }
            //新增一个到头部
            Node<Object, Object> newNode = new Node<>(key, value);
            map.put(key, newNode);
            doubleLinkedList.addHead(newNode);
        }
    }

    public static void main(String[] args) {

        LruDemo lruCacheDemo = new LruDemo(3);

        lruCacheDemo.put(1, 1);
        lruCacheDemo.put(2, 2);
        lruCacheDemo.put(3, 3);
        System.out.println(lruCacheDemo.map.keySet());

        lruCacheDemo.put(4, 1);
        System.out.println(lruCacheDemo.map.keySet());

        lruCacheDemo.put(3, 1);
        System.out.println(lruCacheDemo.map.keySet());
        lruCacheDemo.put(3, 1);
        System.out.println(lruCacheDemo.map.keySet());
        lruCacheDemo.put(3, 1);
        System.out.println(lruCacheDemo.map.keySet());
        lruCacheDemo.put(5, 1);
        System.out.println(lruCacheDemo.map.keySet());

    }

}
