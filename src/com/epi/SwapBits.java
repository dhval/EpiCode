package com.epi;

import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SwapBits {
    // @include
    public static long swap_bits(long x, int i, int j) {
        if (((x >> i) & 1) != ((x >> j) & 1)) {
            x ^= (1L << i) | (1L << j);
        }
        return x;
    }
    // @exclude

    private static void simple_test() {
        assert(swap_bits(47, 1, 4) == 61);
        assert(swap_bits(28, 0, 2) == 25);
    }

    public static void main(String[] args) {
        simple_test();
        Random r = new Random();
        long x;
        int i, j;
        if (args.length == 3) {
            x = Long.parseLong(args[0]);
            i = Integer.parseInt(args[1]);
            j = Integer.parseInt(args[2]);
        } else {
            x = r.nextLong();
            i = r.nextInt(64);
            j = r.nextInt(64);
        }
        System.out.println("x = " + x + ", i = " + i + ", j = " + j);
        System.out.println(swap_bits(x, i, j));
    }
}
