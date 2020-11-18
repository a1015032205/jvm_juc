package com.company.demo1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Demo {

    private static final Scanner scanner = new Scanner(System.in);
    private static Set<Person> set = new HashSet<>();

    public static void main(String[] args) {
        task();
        System.out.println("==============LIST=================");
        set.forEach(x -> System.out.println("NAME:" + x.getName() + ",TEL:" + x.getPhone()));
    }

    public static void task() {
        System.out.print("INPUT NAME:");
        String name = scanner.next();
        System.out.print("INPUT TEL:");
        long tel = scanner.nextLong();
        set.add(new Person(name, tel));
        System.out.println("ADD OK!!! NAME:" + name + ",TEL:" + tel);
        System.out.print(" IF INPUT 1 ADD,ELSE EXIT:");
        int nextInt = scanner.nextInt();
        if (nextInt == 1) {
            task();
        }
    }


    static class Person {

        private String name;

        private Long phone;

        public Person() {
        }

        public Person(String name, Long phone) {
            this.name = name;
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getPhone() {
            return phone;
        }

        public void setPhone(Long phone) {
            this.phone = phone;
        }
    }
}
