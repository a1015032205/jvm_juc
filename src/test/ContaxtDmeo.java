package test;

import java.io.*;
import java.util.*;

public class ContaxtDmeo {
    private static final String PATH = "I:\\info.txt";

    public static void main(String[] agrs) throws Exception {

        ArrayList<Object> objects = new ArrayList<>();
        int size = objects.size();
        objects.add(1);
        TreeSet<User> info = new TreeSet<User>(new InfoComp());

        File file = new File(PATH);
        if (!file.exists()) {
            file.createNewFile();
        }
        if (!(file.length() == 0)) {
            ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(file));
            User temp;
            while ((temp = (User) objIn.readObject()) != null) {
                info.add(temp);
            }
        }
        FileWriter writer = new FileWriter(PATH);

        //  ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(file));

        boolean flag = true;
        while (flag) {
            Menu();
            int num = new Scanner(System.in).nextInt();

            switch (num) {
                case 1:
                    addContaxt(info);
                    break;
                case 2:
                    showAll(info);
                    break;
                case 3:
                    removeUser(info);
                    break;
                case 4:
                    exit(info, writer);
                    flag = false;
                    System.out.println("程序已退出！");
                    break;
            }
        }

    }

    public static void Menu() {
        System.out.println("\t");
        System.out.println("===欢迎进入通讯录系统===");
        System.out.println("\t");
        System.out.println("pleace choise");
        System.out.println("选择1：添加联系人");
        System.out.println("选择2：查看联系人");
        System.out.println("选择3：删除联系人");
        System.out.println("选择4：保存并退出");
    }

    public static void addContaxt(TreeSet<User> info) {

        System.out.println("input your name");
        String name = new Scanner(System.in).next();
        System.out.println("telNumber");
        String num = new Scanner(System.in).next();


        Iterator<User> it = info.iterator();
        while (it.hasNext()) {
            User u = it.next();
            if (u.getName().equals(name) && u.getNum().equalsIgnoreCase(num)) {
                System.out.println("联系人已存在");
                break;
            }
        }
        User user = new User(name, num);
        info.add(user);
    }

    public static void showAll(TreeSet<User> info) throws Exception {
        Iterator<User> it = info.iterator();
        while (it.hasNext()) {
            User u = it.next();
            System.out.println(u.toString());
        }
    }

    public static void removeUser(TreeSet<User> info) {
        System.out.println("输入姓名");
        String name = new Scanner(System.in).next();
        String num = "0";
        Iterator<User> it = info.iterator();
        while (it.hasNext()) {
            User u = it.next();
            if (u.getName().equals(name))
                num = u.getNum();
        }
        if ("0".equalsIgnoreCase(num))
            System.out.println("没有找到该联系人");
        else {
            info.remove(new User(name, num));
            System.out.println("删除成功");
        }
    }

    public static void exit(TreeSet<User> info, FileWriter writer) throws Exception {

        if (info.size() != 0) {

            info.forEach(x -> {
                try {
                    String str = x.toString();
                    System.out.println(str);
                    writer.write(str + "\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.close();
        }


    }
}

class User implements Serializable {
    private String name;
    private String num;

    User(String name, String num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    @Override
    public String toString() {
        return name + "......." + num;
    }

}

class InfoComp implements Comparator<User> {
    public int compare(User u1, User u2) {
        int temp = u1.getName().compareTo(u2.getName());
        if (temp == 0)
            return u1.getNum().compareTo(u2.getNum());
        return temp;
    }
}



