package test;

import java.io.*;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-16 21:57
 * @Description:
 */

public class ContaxtDmeo {
    public static void main(String[] agrs) throws Exception {

        TreeSet<User> info = new TreeSet<>(new InfoComp());

        File file = new File("I:\\UserInfo.txt");

        if (!(file.length() == 0)) {
            ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(file));
            User temp;
            while ((temp = (User) objIn.readObject()) != null) {
                info.add(temp);
            }
        }

        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(file));

        boolean flag = true;
        while (flag) {
            menu();
            int num = new Scanner(System.in).nextInt();
            switch (num) {
                case 1:
                    addContext(info, obj);
                    break;
                case 2:
                    showAll(info);
                    break;
                case 3:
                    removeUser(info);
                    break;
                case 4:
                    exit(info, obj);
                    flag = false;
                    System.out.println("程序已退出！");
                    break;
                default:
                    System.out.println("不能没有default！");
                    break;
            }
        }

    }

    public static void menu() {
        System.out.println("\t");
        System.out.println("===欢迎进入通讯录系统===");
        System.out.println("\t");
        System.out.println("pleace choise");
        System.out.println("选择1：添加联系人");
        System.out.println("选择2：查看联系人");
        System.out.println("选择3：删除联系人");
        System.out.println("选择4：保存并退出");
    }

    public static void addContext(TreeSet<User> info, ObjectOutputStream obj) throws Exception {

        System.out.println("input your name");
        String name = new Scanner(System.in).next();
        System.out.println("telNumber");
        int num = new Scanner(System.in).nextInt();
        for (User u : info) {
            if (u.getName().equals(name) && u.getNum() == num) {
                System.out.println("联系人已存在");
                break;
            }
        }
        User user = new User(name, num);
        info.add(user);
    }

    public static void showAll(TreeSet<User> info) throws Exception {
        for (User u : info) {
            System.out.println(u.toString());
        }
    }

    public static void removeUser(TreeSet<User> info) {
        System.out.println("输入姓名");
        String name = new Scanner(System.in).next();
        int num = 0;
        for (User u : info) {
            if (u.getName().equals(name)) {
                num = u.getNum();
            }
        }
        if (num == 0) {
            System.out.println("没有找到该联系人");
        } else {
            info.remove(new User(name, num));
            System.out.println("删除成功");
        }
    }

    public static void exit(TreeSet<User> info, ObjectOutputStream obj) throws Exception {
        for (User user : info) {
            obj.writeObject(user);
        }
        obj.writeObject(null);
        obj.close();
    }
}

class User implements Serializable {
    private String name;
    private int num;

    User(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return name + "......." + num;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

class InfoComp implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        int temp = u1.getName().compareTo(u2.getName());
        if (temp == 0) {
            return Integer.compare(u1.getNum(), u2.getNum());
        }
        return temp;
    }
}


