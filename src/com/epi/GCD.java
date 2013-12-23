package com.epi;

import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class GCD {
    // @include
    public static long GCD(long x, long y) {
        if (x == 0) {
            return y;
        } else if (y == 0) {
            return x;
        } else if ((x & 1) == 0 && (y & 1) == 0) {  // x and y are even.
            return GCD(x >> 1, y >> 1) << 1;
        } else if ((x & 1) == 0 && (y & 1) != 0) {  // x is even, and y is odd.
            return GCD(x >> 1, y);
        } else if ((x & 1) != 0 && (y & 1) == 0) {  // x is odd, and y is even.
            return GCD(x, y >> 1);
        } else if (x > y) {  // both x and y are odd, and x > y.
            return GCD(x - y, y);
        }
        return GCD(x, y - x);  // both x and y are odd, and x <= y.
    }
    // @exclude

    private static long another_GCD(long a, long b) {
        if (b != 0) {
            while ((a = a % b) != 0 && (b = b % a) != 0);
        }
        return a + b;
    }

    public static void main(String[] args) {
        long x = 18, y = 12;
        assert(GCD(x, y) == 6);
        if (args.length == 2) {
            x = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[1]);
            System.out.println(GCD(x, y));
            assert(GCD(x, y) == another_GCD(x, y));
        } else {
            Random r = new Random();
            for (int times = 0; times < 1000; ++times) {
                x = r.nextInt(Integer.MAX_VALUE);
                y = r.nextInt(Integer.MAX_VALUE);
                assert(GCD(x, y) == another_GCD(x, y));
            }
        }
    }
}
