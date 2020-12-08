package test;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-07-29 20:10
 * @Description: HashMap8
 */

public class MyStack {
    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        String str = "hello world ！！！";

        Stack<String> stack = new Stack<>();

        boolean empty = stack.empty();
        System.out.println(empty);

        System.out.println(stack.search("c"));
        System.out.println(stack.search("b"));
        System.out.println(stack.search("a"));


//        String peek = stack.peek();
//        System.out.println(peek);


    }
}
