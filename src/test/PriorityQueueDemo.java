package test;

import java.util.PriorityQueue;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2021-07-30 16:16
 * @Description:
 */

public class PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(5);
        pq.add(1);
        pq.add(10);
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}
