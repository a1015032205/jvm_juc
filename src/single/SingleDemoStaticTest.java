package single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-01 21:39
 * @Description: 静态内部类
 */
//通过静态内部类的方式实现单例模式是线程安全的，
// 同时静态内部类不会在Singleton类加载时就加载，
// 而是在调用getInstance()方法时才进行加载，达到了懒加载的效果。
class SingleDemoStaticDemo {

    private SingleDemoStaticDemo() {

    }

    public static SingleDemoStaticDemo getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static SingleDemoStaticDemo instance = new SingleDemoStaticDemo();
    }

}


public class SingleDemoStaticTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //通过反射破坏
        SingleDemoStaticDemo bean1 = SingleDemoStaticDemo.getInstance();
        Class<SingleDemoStaticDemo> demoClass = SingleDemoStaticDemo.class;
        Constructor<SingleDemoStaticDemo> declaredConstructor = demoClass.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        SingleDemoStaticDemo bean2 = declaredConstructor.newInstance();
        declaredConstructor.setAccessible(false);
        System.out.println(bean1 == bean2); //false

        //通过反序列化破坏  需要SingleDemoStaticDemo implements Serializable
        // <dependency>
        //    <groupId>org.apache.commons</groupId>
        //    <artifactId>commons-lang3</artifactId>
        //    <version>3.8.1</version>
        //</dependency>
        SingleDemoStaticDemo instance = SingleDemoStaticDemo.getInstance();
        //   byte[] serialize = SerializationUtils.serialize(instance);
        //   SingleDemoStaticDemo newInstance = SerializationUtils.deserialize(serialize);
        //  System.out.println(instance == newInstance);
    }
}
