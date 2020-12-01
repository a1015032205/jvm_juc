package single;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-01 21:03
 * @Description: 饱汉式
 */

class SingleDemoLHS {
    //在并发获取实例的时候，可能会存在构建了多个实例的情况。所以，需要对此代码进行DCL
    private static SingleDemoLHS singleDemo;

    //重点  私有化构造
    private SingleDemoLHS() {

    }

    public static SingleDemoLHS getSingleDemoLhs() {
        if (singleDemo == null) {
            singleDemo = new SingleDemoLHS();
        }
        return singleDemo;
    }


}

public class SingleDemoLhsTest {
    public static void main(String[] args) {
        //验证  有几率第一个线程是false
        for (int j = 0; j < 20; j++) {
            new Thread(() -> test(), String.valueOf(j)).start();
        }

    }

    private static void test() {
        String name = Thread.currentThread().getName();
        SingleDemoLHS bean1 = SingleDemoLHS.getSingleDemoLhs();
        SingleDemoLHS bean2 = SingleDemoLHS.getSingleDemoLhs();
        boolean flag = bean1 == bean2;
        System.out.println(name + "====>" + flag); //true
    }
}