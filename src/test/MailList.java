package test;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-16 22:05
 * @Description: 通讯录
 */


/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-16 22:05
 * @Description: 通讯录
 */
public class MailList {

    private static Scanner sc = new Scanner(System.in);
    private static Integer index = 1;
    private static TreeSet<UserInfo> infoList = new TreeSet<>(Comparator.comparingInt(UserInfo::getId));


    public static void main(String[] args) throws Exception {

        readFileContent("I:\\UserInfo.txt");

        if (infoList.size() != 0) {
            index = infoList.last().getId();
        }
        extracted();
    }

    private static void extracted() throws IOException {
        menu();
        int num = sc.nextInt();
        switch (num) {
            case 1:
                addContext();
                break;
            case 2:
                show();
                break;
            case 3:
                removeUser();
                break;
            case 4:
                exit();
                break;
            default:
                System.out.println("错误输入，程序退出！");
                System.exit(0);
                break;
        }
    }


    public static void menu() {
        System.out.println();
        System.out.println("===欢迎进入通讯录系统===");
        System.out.println();
        System.out.println("pleace choise");
        System.out.println("选择1：添加联系人");
        System.out.println("选择2：查看联系人");
        System.out.println("选择3：删除联系人");
        System.out.println("选择4：保存并退出");
        System.out.print("选择输入:");
    }

    public static void addContext() throws IOException {
        System.out.print("输入姓名:");
        String name = sc.next();
        if (infoList.stream().anyMatch(x -> x.getName().equalsIgnoreCase(name))) {
            System.out.println("联系人已存在，查找到如下信息：");
            infoList.stream().filter(x -> x.getName().equalsIgnoreCase(name)).forEach(System.out::println);
            System.out.print("请重新输入:");
            addContext();
        }
        System.out.print("输入电话:");
        String tel = sc.next();
        UserInfo userInfo = new UserInfo(++index, name, tel);
        infoList.add(userInfo);
        System.out.println("信息已加入");
        extracted();
    }

    public static void show() throws IOException {
        if (infoList.size() == 0) {
            System.out.println("暂无信息！");
            return;
        }
        System.out.println("==============信息如下：=====================");
        infoList.forEach(System.out::println);
        System.out.println("==============信息如下：=====================");
        extracted();
    }

    public static void removeUser() throws IOException {
        if (infoList.size() == 0) {
            System.out.println("暂无联系人可删除！");
            return;
        }
        System.out.println("====================现有联系人如下：===========================");
        System.out.print("输入要删除序号:");
        int i = sc.nextInt();
        UserInfo user = infoList.stream().filter(x -> x.getId() == i).findFirst().orElseThrow(() -> new RuntimeException("输入不存在"));
        infoList.remove(user);
        System.out.println("====================现有联系人如下：===========================");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("====================删除后联系人如下：===========================");
        show();
        extracted();
    }

    public static void exit() throws IOException {

        if (infoList.size() != 0) {
            FileWriter writer = new FileWriter("I:\\UserInfo.txt");
            infoList.forEach(x -> {
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

    public static void readFileContent(String path) throws Exception {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        while (line != null && !"".equalsIgnoreCase(line)) {
            String[] split = line.split(",");
            UserInfo userInfo = new UserInfo();
            Arrays.stream(split).forEach(x -> {
                String[] arr = x.split(":");
                String key = arr[0];
                String value = arr[1];
                if ("id".equalsIgnoreCase(key)) {
                    userInfo.setId(Integer.parseInt(value));
                } else if ("name".equalsIgnoreCase(key)) {
                    userInfo.setName(value);
                } else {
                    userInfo.setTel(value);
                }

            });
            System.out.println("已从文件读取：");
            System.out.println(userInfo);
            infoList.add(userInfo);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        fileReader.close();
    }
}

class UserInfo implements Serializable {

    private Integer id;

    private String name;

    private String tel;


    @Override
    public String toString() {
        return "id:" + id + ",name:" + name + ",tel:" + tel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public UserInfo(Integer id, String name, String tel) {
        this.id = id;
        this.name = name;
        this.tel = tel;
    }

    public UserInfo() {
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