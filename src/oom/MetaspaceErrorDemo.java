package oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-17 22:04
 * @Description: 元空间溢出
 */
//元空间就是我们的方法区，存放的是类模板，类信息，常量池等
//Metaspace是方法区HotSpot中的实现，它与持久代最大的区别在于：Metaspace并不在虚拟内存中，而是使用本地内存，也即在java8中，class metadata（the virtual machines internal presentation of Java class），被存储在叫做Matespace的native memory
//永久代（java8后背元空间Metaspace取代了）存放了以下信息：
//虚拟机加载的类信息
//常量池
//静态变量
//即时编译后的代码
//模拟Metaspace空间溢出，我们不断生成类 往元空间里灌输，类占据的空间总会超过Metaspace指定的空间大小
public class MetaspaceErrorDemo {
    static class OomTest {

    }

    //-XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
    public static void main(String[] args) {
        int i = 0;

        try {
            while (true) {
                i++;
                // 使用Spring的动态字节码技术  CGLIB
                Enhancer enhancer = new Enhancer();
                //反射
                enhancer.setSuperclass(OomTest.class);
                //不用缓存
                enhancer.setUseCache(false);
                //回调函数
                enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invoke(o, args));
                //创建
                enhancer.create();  //Caused by: java.lang.OutOfMemoryError: Metaspace
            }
        } catch (Exception e) {
            System.out.println("=========================>I:" + i);//517
            e.printStackTrace();
        }

    }
}
