/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2022-05-22 19:11
 * @Description:
 */

public class weimob {

    public static void main(String[] args) {
        Node node = new Node(0);
        for (int i = 1; i < 10; i++) {
            node.add(i);
        }
        node.print();

        System.out.println(findLastK(node, 3).val);


    }

    public static Node findLastK(Node head, int k) {
        if (k < 1) {
            return null;
        }
        Node p1 = head;
        Node p2 = head;
        for (int i = 0; i < k - 1 && p1 != null; i++) {
            p1 = p1.next;
        }
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }


    private static class Node {
        int val;    //数值 data
        Node next;    // 结点 node

        Node(int x) {    //可以定义一个有参构造方法，也可以定义一个无参构造方法
            val = x;
        }

        // 添加新的结点
        public void add(int newval) {
            Node newNode = new Node(newval);
            if (this.next == null)
                this.next = newNode;
            else
                this.next.add(newval);
        }

        // 打印链表
        public void print() {
            System.out.print(this.val);
            if (this.next != null) {
                System.out.print("-->");
                this.next.print();
            }
        }

    }
}
