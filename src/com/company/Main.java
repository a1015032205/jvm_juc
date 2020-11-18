package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Info> result = new ArrayList<>();
        Map<String, List<Info>> collect = getList().stream().collect(Collectors.groupingBy(Info::getLeave, Collectors.toList()));
        for (Map.Entry<String, List<Info>> entry : collect.entrySet()) {

            List<Info> count = entry.getValue();
            Info end = new Info();
            end = count
                    .stream()
                    .reduce(end, Info::sum, (u, t) -> u);
            result.add(end);
        }
        result.forEach(System.out::println);
    }

    static class Info {
        //等级
        String leave;
        //计数
        int count;

        public Info sum(Info info) {
            if (leave == null) {
                this.leave = info.getLeave();
            }
            this.count += info.getCount();
            return this;
        }

        public Info(String leave, int count) {
            this.leave = leave;
            this.count = count;
        }

        public Info() {
        }

        @Override
        public String toString() {
            return "{" +
                    "等级='" + leave + '\'' +
                    ", 总数=" + count +
                    '}';
        }

        public String getLeave() {
            return leave;
        }

        public void setLeave(String leave) {
            this.leave = leave;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }


    static List<Info> getList() {
        List<Info> list = new ArrayList<>(10);
        Info info = new Info("1", 1);
        Info info1 = new Info("150", 10);
        Info info2 = new Info("150", 10);
        Info info3 = new Info("150", 1);
        Info info4 = new Info("180", 10);
        Info info5 = new Info("180", 1);
        list.add(info);
        list.add(info1);
        list.add(info3);
        list.add(info2);
        list.add(info4);
        list.add(info5);
        return list;
    }

}
