package com.drx.epi;

import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class InterconvertingStringInteger {
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

    // @include
    public static String intToString(int x) {
        boolean is_negative = false;
        if (x < 0) {
            x = -x;
            is_negative = true;
        }

        StringBuilder s = new StringBuilder();
        while (x != 0) {
            s.append((char)('0' + x % 10));
            x /= 10;
        }
        if (s.length() == 0) {
            return "0";  // x is 0.
        }

        if (is_negative) {
            s.append('-');
        }
        s.reverse();
        return s.toString();
    }

    // We define the valid strings for this function as those matching regexp
    // -?[0-9]+.
    public static int stringToInt(String s) {
        // "-" starts as a valid integer, but has no digits.
        if (s.equals("-")) {
            throw new RuntimeException("illegal input");
        }

        boolean is_negative = s.charAt(0) == '-';
        int x = 0;
        for (int i = is_negative ? 1 : 0; i < s.length(); ++i) {
            if (Character.isDigit(s.charAt(i))) {
                x = x * 10 + s.charAt(i) - '0';
            } else {
                throw new RuntimeException("illegal input");
            }
        }
        return is_negative ? -x : x;
    }
    // @exclude

    public static void main(String[] args) {
        Random r = new Random();
        if (args.length == 1) {
            try {
                System.out.println(stringToInt(args[0]));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            for (int times = 0; times < 10000; ++times) {
                int x = r.nextInt();
                String str = intToString(x);
                System.out.println(x + " " + str);
                assert(x == Integer.parseInt(str));
                str = rand_int_string(r.nextInt(10));
                x = stringToInt(str);
                System.out.println(str + " " + x);
                assert(x == Integer.parseInt(str));
            }
            try {
                stringToInt("123abc");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
