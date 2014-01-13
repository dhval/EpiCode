package com.drx.epi;

import java.util.HashSet;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class CollatzConjecture {
    // @include
    public static boolean test_Collatz_conjecture(int n) {
        // Stores the odd number that converges to 1.
        HashSet<Long> table = new HashSet<Long>();

        // Start from 2 since we don't need to test 1.
        for (int i = 2; i <= n; ++i) {
            HashSet<Long> sequence = new HashSet<Long>();
            long test_i = i;
            while (test_i >= i) {
                // Emplace failed, it mean we met some number encountered before.
                if (!sequence.add(test_i)) {
                    return false;
                }

                if ((test_i & 1) != 0) {  // odd number
                    if (!table.add(test_i)) {
                        break;  // this number have already be proven to converge to 1.
                    }
                    long next_test_i = 3 * test_i + 1;  // 3n + 1.
                    if (next_test_i <= test_i) {
                        throw new RuntimeException("test process overflow");
                    }
                    test_i = next_test_i;
                } else {         // even number.
                    test_i >>= 1;  // n / 2.
                }
            }
            table.remove((long) i);  // removes i from table.
        }
        return true;
    }
    // @exclude

    // Slow check without any pruning.
    public static boolean check(int n) {
        for (int i = 2; i <= n; ++i) {
            int test_i = i;
            while (test_i != 1 && test_i >= i) {
                if ((test_i & 1) != 0) {
                    test_i = test_i * 3 + 1;
                } else {
                    test_i >>= 1;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            int n;
            if (args.length == 1) {
                n = Integer.parseInt(args[0]);
            } else {
                n = r.nextInt(100000) + 1;
            }
            System.out.println("n = " + n);
            boolean res = test_Collatz_conjecture(n);
            System.out.println(res);
            assert(res == check(n));
        }
    }
}
