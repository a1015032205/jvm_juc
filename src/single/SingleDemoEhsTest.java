package single;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-01 21:03
 * @Description: 饿汉式
 */

class SingleDemoEHS {
    //饿汉式的缺点就是，可能在还不需要此实例的时候就已经把实例创建出来了，没起到lazy loading的效果。优点就是实现简单，而且安全可靠。
    private static SingleDemoEHS singleDemo = new SingleDemoEHS();

    //重点  私有化构造
    private SingleDemoEHS() {

    }

    public static SingleDemoEHS getSingleDemoEHS() {
        return singleDemo;
    }


}

public class SingleDemoEhsTest {
    public static void main(String[] args) {
        SingleDemoLHS bean1 = SingleDemoLHS.getSingleDemoLhs();
        SingleDemoLHS bean2 = SingleDemoLHS.getSingleDemoLhs();
        System.out.println(bean1 == bean2); //true
    }
}