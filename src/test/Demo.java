package test;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-03 22:07
 * @Description:
 */

public class Demo {
    public static void main(String[] args) {
        int a = 1_1;
        int b = 1_0;
        int c = 1_1_1;
        int d = 1_1_0;
        int e = 1_0_1;
        int f = 2_1_1;
        int g = 1_1_2;
        int h = 1_1000_1000;
        System.out.println(a);//11
        System.out.println(b);//10
        System.out.println(c);//111
        System.out.println(d);//110
        System.out.println(e);//101
        System.out.println(f);//211
        System.out.println(g);//112
        System.out.println(h);//110001000

    }
}
