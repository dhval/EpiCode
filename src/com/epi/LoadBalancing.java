package com.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class LoadBalancing {
    // @include
    public static List<Integer> decide_load_balancing(List<Integer> user_file_size,
                                      int server_num) {
        // Uses binary search to find the assignment with minimized maximum load.
        int l = 0;
        int r = 0;
        for(int i : user_file_size) {
            r += i;
        }
        ArrayList<Integer> feasible_assignment = new ArrayList<Integer>();
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            ArrayList<Integer> assign_res = new ArrayList<Integer>(server_num);
            for(int i = 0; i < server_num; i++) {
                assign_res.add(0);
            }
            boolean is_feasible = greedy_assignment(user_file_size, server_num, m, assign_res);
            if (is_feasible) {
                feasible_assignment = assign_res;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return feasible_assignment;
    }

    private static boolean greedy_assignment(List<Integer> user_file_size,
                           int server_num,
                           int limit,
                           List<Integer> assign_res) {
        int server_idx = 0;
        for (int file : user_file_size) {
            while (server_idx < server_num &&
                    file + assign_res.get(server_idx) > limit) {
                ++server_idx;
            }

            if (server_idx >= server_num) {
                return false;
            } else {
                assign_res.set(server_idx, assign_res.get(server_idx) + file);
            }
        }
        return true;
    }
    // @exclude

    public static void main(String[] args) {
        Random r = new Random();
        int n, m;
        if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
        } else {
            n = r.nextInt(50000) + 1;
            m = r.nextInt(n) + 1;
        }
        System.out.println(n + " " + m);
        ArrayList<Integer> users = new ArrayList<Integer>();  // stores user i's data size.
        for (int i = 0; i < n; ++i) {
            users.add(r.nextInt(1000) + 1);
        }
        for (int u : users) {
            System.out.print(u + " ");
        }
        System.out.println();
        List<Integer> res = decide_load_balancing(users, m);
        for (int file : res) {
            System.out.print(file + " ");
        }
        System.out.println();
    }
}
