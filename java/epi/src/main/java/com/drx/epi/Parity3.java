package com.drx.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class Parity3 {
    private static short precomputed_parity[];

    public static void build_table() {
        if (precomputed_parity == null) {
            precomputed_parity = new short[1 << 16];
            for (int i = 0; i < (1 << 16); ++i) {
                precomputed_parity[i] = Parity1.parity1(i);
            }
        }
    }

    // @include
    public static short parity3(long x) {
        return (short) (precomputed_parity[(int)((x >> 48) & 0xFFFF)] ^
                precomputed_parity[(int)((x >> 32) & 0xFFFF)] ^
                precomputed_parity[(int)((x >> 16) & 0xFFFF)] ^
                precomputed_parity[(int)(x & 0xFFFF)]);
    }
    // @exclude
}
