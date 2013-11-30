package com.drx.epi;

import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class Division {
    public static long divide_x_y_bsearch(long x, long y) {
        if (x < y) {
            return 0;
        }

        int power_left = 0;
        int power_right = 8 << 3;
        int power_mid = -1;
        while (power_left < power_right) {
            int tmp = power_mid;
            power_mid = power_left + ((power_right - power_left) >> 1);
            if (tmp == power_mid) {
                break;
            }
            long yshift = y << power_mid;
            if ((yshift >> power_mid) != y) {
                // yshift overflowed, use a smaller shift.
                power_right = power_mid;
                continue;
            }
            if ((y << power_mid) > x) {
                power_right = power_mid;
            } else if ((y << power_mid) < x) {
                power_left = power_mid;
            } else {
                return (1L << power_mid);
            }
        }
        long part = 1L << power_left;
        return part | divide_x_y_bsearch(x - (y << power_left), y);
    }

    // @include
    public static long divide_x_y(long x, long y) {
        long res = 0;
        while (x >= y) {
            int power = 1;
            // Checks (y << power) >= (y << (power - 1)) to prevent potential
            // overflow of unsigned.
            while ((y << power) >= (y << (power - 1)) && (y << power) <= x) {
                ++power;
            }

            res += 1L << (power - 1);
            x -= y << (power - 1);
        }
        return res;
    }
    // @exclude

    private static void simple_test() {
        assert(divide_x_y(64, 1) == 64);
        assert(divide_x_y(64, 2) == 32);
        assert(divide_x_y(64, 3) == 21);
        assert(divide_x_y(64, 4) == 16);
        assert(divide_x_y(64, 5) == 12);
        assert(divide_x_y(65, 2) == 32);
        assert(divide_x_y(2600540749L, 2590366779L) == 1);
        assert(divide_x_y_bsearch(4, 2) == 2);
        assert(divide_x_y_bsearch(64, 1) == 64);
        assert(divide_x_y_bsearch(64, 2) == 32);
        assert(divide_x_y_bsearch(64, 3) == 21);
        assert(divide_x_y_bsearch(64, 4) == 16);
        assert(divide_x_y_bsearch(64, 5) == 12);
        assert(divide_x_y_bsearch(65, 2) == 32);
        assert(divide_x_y_bsearch(9444, 4714) == 2);
        assert(divide_x_y_bsearch(8186, 19) == 430);
        assert(divide_x_y_bsearch(8186, 19) == 430);
    }

    public static void main(String[] args) {
        simple_test();
        if (args.length == 2) {
            long x = Long.parseLong(args[0]);
            long y = Long.parseLong(args[1]);
            assert(x / y == divide_x_y(x, y));
            assert(x / y == divide_x_y_bsearch(x, y));
        } else {
            Random r = new Random();
            for (int times = 0; times < 100000; ++times) {
                long x = r.nextInt(Integer.MAX_VALUE), y = r.nextInt(Integer.MAX_VALUE);
                y = (y == 0) ? 1 : y;  // ensure no divide by 0.
                System.out.println("times = " + times + ", x = " + x + ", y = " + y);
                System.out.println("first = " + x / y + ", second = " + divide_x_y(x, y));
                assert(x / y == divide_x_y(x, y));
                assert(x / y == divide_x_y_bsearch(x, y));
            }
        }

    }
}
