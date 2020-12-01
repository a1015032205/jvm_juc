package single;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-01 22:03
 * @Description: 枚举
 */
//利用枚举的特性，让JVM来帮我们保证线程安全和单一实例的问题。除此之外，写法还特别简单。
class SingleDemoEnum {
    public static void main(String[] args) {
        SingleDemoEnumTest bean1 = SingleDemoEnumTest.INSTANCE.getInstance();
        SingleDemoEnumTest bean2 = SingleDemoEnumTest.INSTANCE.getInstance();
        System.out.println(bean1 == bean2);
    }
}
//而枚举类不能被反射，所以可以防止反射攻击
//避免序列化问题（任何一个readObject方法，不管是显式的还是默认的，它都会返回一个新建的实例，这个新建的实例不同于该类初始化时创建的实例）
// Java transient关键字使用小记    https://www.cnblogs.com/lanxuezaipiao/p/3369962.html
//枚举类的构造器只能使用private权限修饰符
//枚举不能再类外直接实例化，也不能被继承
public enum SingleDemoEnumTest {
    //系统会自动添加public static final修饰
    INSTANCE;

    public void doSomething() {
        System.out.println("doSomething");
    }
    public SingleDemoEnumTest getInstance(){
        return INSTANCE;
    }
}
