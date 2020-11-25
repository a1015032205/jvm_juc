package com.company.demo1;

import java.util.ArrayList;
import java.util.List;


public class MtStringAll {
    static List<List<Integer>> res = new ArrayList<>();


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        long start = System.currentTimeMillis();
        List<List<Integer>> permute = permute(nums);
        long end = System.currentTimeMillis();
        long l = end - start;
        System.out.println("时间：" + l);
        System.out.println("长度：" + permute.size());
        // permute.forEach(System.out::println);
    }

    static List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        DFS(nums, list, used);
        return res;
    }

    static void DFS(int[] nums, List<Integer> list, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int c = nums[i];
            if (!used[i]) {
                list.add(c);
                used[i] = true;
                DFS(nums, list, used);
                list.remove(list.size() - 1);
                used[i] = false;
            }
        }
    }
}
