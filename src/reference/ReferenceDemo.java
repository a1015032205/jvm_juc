package reference;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-16 21:03
 * @Description: 强引用
 */

public class ReferenceDemo {
    public static void main(String[] args) {
        //在等号的左边，就是一个对象的引用，存储在栈中
        //而等号右边，就是实例化的对象，存储在堆中
        //其实这样的一个引用关系，就被称为强引用
        Object o1 = new Object();
        // 使用第二个引用，指向刚刚创建的Object对象
        Object o2 = o1;
        System.out.println(o1);//java.lang.Object@14ae5a5
        System.out.println(o2);//java.lang.Object@14ae5a5
        o1 = null; // 置空
        System.gc();//GC
        System.out.println(o1);//null
        System.out.println(o2);//java.lang.Object@14ae5a5
        //输出结果我们能够发现，即使 obj1 被设置成了null，然后调用gc进行回收，
        // 但是也没有回收实例出来的对象，obj2还是能够指向该地址，也就是说垃圾回收器，并没有将该对象进行垃圾回收
    }
}
