package com.drx.epi;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ReverseBits {
    private static ArrayList<Long> precomputed_reverse = new ArrayList<Long>();

    private static long reverse_x(long x, int n) {
        for (int i = 0, j = n; i < j; ++i, --j){
            x = SwapBits.swap_bits(x, i, j);
        }
        return x;
    }

    private static void create_precomputed_table() {
        for (int i = 0; i < (1 << 16); ++i) {
            precomputed_reverse.add(reverse_x(i, 15));
        }
    }

    // @include
    public static long reverse_bits(long x) {
        return precomputed_reverse.get((int) ((x >> 48) & 0xFFFF)) |
                precomputed_reverse.get((int) ((x >> 32) & 0xFFFF)) << 16 |
                precomputed_reverse.get((int) ((x >> 16) & 0xFFFF)) << 32 |
                precomputed_reverse.get((int) (x & 0xFFFF)) << 48;
    }
    // @exclude

    public static void main(String[] args) {
        create_precomputed_table();
        if(args.length == 2) {
            long x = Long.parseLong(args[1]);
            System.out.println("sizeof(x) = 8"); //In java long always 8 bytes
            System.out.println("x = " + x + ", reverse_x = " + reverse_bits(x));
            System.out.println(reverse_x(x, 63));
            assert reverse_bits(x) == reverse_x(x, 63);
        } else {
            Random r = new Random();
            for (int times = 0; times < 1000; ++times) {
                long x = r.nextLong();
                System.out.println("x = " + x + ", reverse_x = " + reverse_bits(x));
                assert reverse_bits(x) == reverse_x(x, 63);
            }
        }
    }
}
