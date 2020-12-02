package single;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-01 21:22
 * @Description: DCL
 */
class SingleDemoDcl {
    //在并发获取实例的时候，可能会存在构建了多个实例的情况。所以，需要对此代码进行DCL
    private volatile static SingleDemoDcl singleDemo;

    //重点  私有化构造
    private SingleDemoDcl() {

    }

    public static SingleDemoDcl getSingleDemoDcl() {
        if (singleDemo == null) {
            // a 双重检查加锁多线程情况下会出现某个线程虽然这里已经为空，但是另外一个线程已经执行到d处
            synchronized (SingleDemoDcl.class) { //b
                //c不加volitale关键字的话有可能会出现尚未完全初始化就获取到的情况。原因是内存模型允许无序写入
                if (singleDemo == null) {
                    //memory = allocate(); // 1、分配对象内存空间
                    //instance(memory); // 2、初始化对象
                    //instance = memory; // 3、设置instance指向刚刚分配的内存地址，此时instance != null
                    //步骤2 和 步骤3之间不存在 数据依赖关系


                    // d 此时才开始初始化
                    singleDemo = new SingleDemoDcl(); //不是原子 操作 可能会指令重拍  引发NPE  需要volatile

                }
            }
        }
        return singleDemo;
    }


}

public class SingleDemoDclTest {
    public static void main(String[] args) {

        //验证
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 2000; j++) {
                    new Thread(() -> demo()).start();
                }
            }).start();

        }
    }

    private static void demo() {
        SingleDemoDcl bean1 = SingleDemoDcl.getSingleDemoDcl();
        SingleDemoDcl bean2 = SingleDemoDcl.getSingleDemoDcl();
        if (bean1 != bean2) {
            System.out.println("===");
        }
    }
}
