package thread;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-10-21 22:18
 * @Description: 58面试题  常量池
 */

public class Demo01SpringPool58 {
    public static void main(String[] args) {
          /*
        (1).str1
        str1 会有4个对象
           一个StringBuilder、
           一个58 ldc、
           一个tongcheng ldc、
           String
           这个时候常量池中没有58tongcheng这个ldc在
        str1.intern():在jdk7后,会看常量池中是否存在,如果没有,它不会创建一个对象，
                      如果堆中已经这个字符串，那么会将堆中的引用地址赋给它
                      所以这个时候str1.intern()是获取的堆中的
        * */
        String str1 = new StringBuilder("58").append("tongchong").toString();
        System.out.println(str1); //58tongchong
        System.out.println(str1.intern());//58tongchong
        System.out.println(str1 == str1.intern());  //基本为true

        System.out.println();
   /*
        sum.misc.Version类会在JDK类库的初始化中被加载并初始化,而在初始化时它需要对静态常量字
        段根据指定的常量值(ConstantValue)做默认初始化,此时sum.misc.Version.launcher静态常
        量字段所引用的"java"字符串字面量就被intern到HotSpot VM的字符串常量池 - StringTable
        里了
        str2对象是堆中的
        str.intern()是返回的是JDK出娘胎自带的,在加载sum.misc.version这个类的时候进入常量池
        */
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2);  //java
        System.out.println(str2.intern());//java
        System.out.println(str2 == str2.intern()); //永远是false

    }
}
