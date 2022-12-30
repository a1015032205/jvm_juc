package jvm;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-17 23:40
 * @Description:
 */
class CodeZy extends CodeBlock {
    public CodeZy() {
        System.out.println("===========code 构造方法===========");
    }

    static {
        System.out.println("===========code 静态块===========");
    }

    {
        System.out.println("===========code  代码块===========");
    }
}

public class CodeBlock {
    static {
        System.out.println("===========CodeBlock 静态代码块===========");
    }

    {
        System.out.println("===========CodeBlock  代码块===========");
    }

    public CodeBlock() {
        System.out.println("===========CodeBlock  构造方法===========");
    }

    public static void main(String[] args) {
        System.out.println("========MAIN=======777777777777=======");
        new CodeZy();
        System.out.println("-------------------------------");
        new CodeBlock();
        System.out.println("-------------------------------");
    }
}
