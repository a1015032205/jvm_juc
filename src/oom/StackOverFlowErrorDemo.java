package oom;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-17 20:40
 * @Description: 栈溢出
 */

//堆栈溢出，我们有最简单的一个递归调用，就会造成堆栈溢出，也就是深度的方法调用
//栈一般是512K，不断的深度调用，直到栈被撑破
public class StackOverFlowErrorDemo {
    public static void main(String[] args) {
        main(args);//Exception in thread "main" java.lang.StackOverflowError
    }
}
