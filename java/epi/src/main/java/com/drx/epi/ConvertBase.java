package com.drx.epi;

import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ConvertBase {
    // @include
    public static String convert_base(String s, int b1, int b2) {
        boolean neg = s.startsWith("-");
        int x = 0;
        for (int i = (neg ? 1 : 0); i < s.length(); ++i) {
            x *= b1;
            x += Character.isDigit(s.charAt(i)) ? s.charAt(i) - '0' : s.charAt(i) - 'A' + 10;
        }

        StringBuilder ans = new StringBuilder();
        while (x != 0) {
            int r = x % b2;
            ans.append((char)(r >= 10 ? 'A' + r - 10 : '0' + r));
            x /= b2;
        }

        if (ans.length() == 0) {
            ans.append('0');
        }
        if (neg) {
            ans.append('-');
        }
        ans.reverse();
        return ans.toString();
    }
    // @exclude

    public static String rand_int_string(int len) {
        Random r = new Random();
        StringBuilder ret = new StringBuilder();
        if (len == 0) {
            return "0";
        }
        if (r.nextBoolean()) {
            ret.append('-');
        }
        ret.append((char)('1' + r.nextInt(9)));
        while (--len != 0) {
            ret.append((char)('0' + r.nextInt(10)));
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        if (args.length == 3) {
            String input = args[0];
            int b1 = Integer.parseInt(args[1]);
            int b2 = Integer.parseInt(args[2]);
            System.out.println(convert_base(input, b1, b2));
            assert(input.equals(convert_base(convert_base(input, b1, b2),
                    b2,
                    b1)));
        } else {
            Random r = new Random();
            for (int times = 0; times < 100000; ++times) {
                String input = rand_int_string(r.nextInt(9) + 1);
                int base = r.nextInt(15) + 2;
                System.out.println("input is " + input + ", base1 = 10, base2 = " + base
                        + ", ans = " + convert_base(input, 10, base));
                assert(input.equals(convert_base(convert_base(input, 10, base), base, 10)));
            }
        }

    }
}
