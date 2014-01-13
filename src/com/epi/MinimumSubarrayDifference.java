package com.drx.epi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MinimumSubarrayDifference {
    // @include
    public static int minimize_difference(List<Integer> A) {
        int sum = 0;
        for(int a : A) {
            sum += a;
        }

        HashSet<Integer> is_Ok = new HashSet<Integer>();
        is_Ok.add(0);
        for (int item : A) {
            for (int v = sum >> 1; v >= item; --v) {
                if (is_Ok.contains(v - item)) {
                    is_Ok.add(v);
                }
            }
        }

        // Find the first i from middle where is_Ok[i] == true.
        for (int i = sum >> 1; i > 0; --i) {
            if (is_Ok.contains(i)) {
                return (sum - i) - i;
            }
        }
        return sum;  // one thief takes all.
    }
    // @exclude

    public static void main(String[] args) {
        Random r = new Random();
        int n;
        ArrayList<Integer> A = new ArrayList<Integer>();
        if (args.length == 1) {
            n = Integer.parseInt(args[0]);
        } else if (args.length == 0) {
            n = r.nextInt(50);
        } else {
            for (String arg : args) {
                A.add(Integer.parseInt(arg));
            }
            n = 0;
        }
        for (int i = 0; i < n; ++i) {
            A.add(r.nextInt(100));
        }
        System.out.println(A);
        int sum = 0;
        for(int a : A) {
            sum += a;
        }
        System.out.println(sum);
        System.out.println("minimum difference = " + minimize_difference(A));
    }
}
