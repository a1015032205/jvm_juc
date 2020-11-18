package com.company.classload;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-16 21:22
 * @Description:
 */

public class MyObj {
    public static void main(String[] args) {
        Object obj = new Object();
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("key", "value");
        map.get("key");
        System.out.println(obj.getClass().getClassLoader().getParent().getParent());//NullPointerException
        System.out.println(obj.getClass().getClassLoader().getParent());//NullPointerException
        System.out.println(obj.getClass().getClassLoader());//null

        System.out.println();
        System.out.println();
        System.out.println();


        MyObj myObj = new MyObj();
        System.out.println(myObj.getClass().getClassLoader().getParent().getParent());//null
        System.out.println(myObj.getClass().getClassLoader().getParent());//sun.misc.Launcher$ExtClassLoader@14ae5a5
        System.out.println(myObj.getClass().getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2
    }
}
