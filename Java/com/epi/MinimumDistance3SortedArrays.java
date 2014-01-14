package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MinimumDistance3SortedArrays {
    private static int distance(List<? extends List<Integer>> arrs, List<Integer> idx) {
        int max_val = Integer.MIN_VALUE;
        int min_val = Integer.MAX_VALUE;
        for (int i = 0; i < idx.size(); ++i) {
            max_val = Math.max(max_val, arrs.get(i).get(idx.get(i)));
            min_val = Math.min(min_val, arrs.get(i).get(idx.get(i)));
        }
        return max_val - min_val;
    }

    // @include
    public static class ArrData implements Comparable<ArrData>{
        public int idx;
        public int val;

        public ArrData(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }

        @Override
        public int compareTo(ArrData o) {
            int result = Integer.valueOf(val).compareTo(o.val);
            if(result == 0)
                result = Integer.valueOf(idx).compareTo(o.idx);
            return result;
        }
    }

    public static int find_min_distance_sorted_arrays(List<? extends List<Integer>> arrs) {
        // Pointers for each of arrs.
        ArrayList<Integer> idx = new ArrayList<Integer>(arrs.size());
        for(int i = 0; i < arrs.size(); i++) {
            idx.add(0);
        }
        int min_dis = Integer.MAX_VALUE;
        TreeSet<ArrData> current_heads = new TreeSet<ArrData>();

        // Each of arrs puts its minimum element into current_heads.
        for (int i = 0; i < arrs.size(); ++i) {
            if (idx.get(i) >= arrs.get(i).size()) {
                return min_dis;
            }
            current_heads.add(new ArrData(i, arrs.get(i).get(idx.get(i))));
        }

        while (true) {
            min_dis = Math.min(min_dis,
                    current_heads.last().val - current_heads.first().val);
            int tar = current_heads.first().idx;
            // Return if there is no remaining element in one array.
            idx.set(tar, idx.get(tar) + 1);
            if (idx.get(tar) >= arrs.get(tar).size()) {
                return min_dis;
            }
            current_heads.pollFirst();
            current_heads.add(new ArrData(tar, arrs.get(tar).get(idx.get(tar))));
        }
    }
    // @exclude

    private static void rec_gen_answer(List<? extends List<Integer>> arrs,
                        List<Integer> idx,
                        int level,
                        ObjectWrapper<Integer> ans) {
        if (level == arrs.size()) {
            ans.set(Math.min(distance(arrs, idx), ans.get()));
            return;
        }
        for (int i = 0; i < arrs.get(level).size(); ++i) {
            idx.set(level, i);
            rec_gen_answer(arrs, idx, level + 1, ans);
        }
    }

    private static int brute_force_gen_answer(List<? extends List<Integer>> arrs) {
        ObjectWrapper<Integer> ans = new ObjectWrapper<Integer>(Integer.MAX_VALUE);
        List<Integer> idx = new ArrayList<Integer>(arrs.size());
        for(int i = 0; i < arrs.size(); i++) {
            idx.add(0);
        }
        rec_gen_answer(arrs, idx, 0, ans);
        System.out.println(ans.get());
        return ans.get();
    }

    public static void main(String[] args) {
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            int n;
            ArrayList<ArrayList<Integer>> arrs = new ArrayList<ArrayList<Integer>>();
            if (args.length == 1) {
                n = Integer.parseInt(args[0]);
            } else {
                n = r.nextInt(5) + 1;
            }
            for (int i = 0; i < n; ++i) {
                int size = r.nextInt(40) + 1;
                arrs.add(new ArrayList<Integer>(size));
                for (int j = 0; j < size; ++j) {
                    arrs.get(i).add(r.nextInt(10000));
                }
                Collections.sort(arrs.get(i));
            }
            int ans = find_min_distance_sorted_arrays(arrs);
            System.out.println(ans);
            assert(brute_force_gen_answer(arrs) == ans);
        }
    }
}
