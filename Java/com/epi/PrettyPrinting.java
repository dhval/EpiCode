package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class PrettyPrinting {
    private static String rand_string(int len) {
        Random r = new Random();
        StringBuilder ret = new StringBuilder(len);
        while(len-- > 0) {
            ret.append((char)(r.nextInt(26) + 'a'));
        }
        return ret.toString();
    }

    // @include
    public static int find_pretty_printing(List<String> W, int L) {
        // Calculate M(i).
        long M[] = new long[W.size()];
        Arrays.fill(M, Long.MAX_VALUE);
        for (int i = 0; i < W.size(); ++i) {
            int b_len = L - W.get(i).length();
            M[i] = Math.min((i - 1 < 0 ? 0 : M[i - 1]) + (1 << b_len), M[i]);
            for (int j = i - 1; j >= 0; --j) {
                b_len -= (W.get(j).length() + 1);
                if (b_len < 0) {
                    break;
                }
                M[i] = Math.min((j - 1 < 0 ? 0 : M[j - 1]) + (1 << b_len), M[i]);
            }
        }

        // Find the minimum cost without considering the last line.
        long min_mess = (W.size() >= 2 ? M[W.size() - 2] : 0);
        int b_len = L - W.get(W.size() - 1).length();
        for (int i = W.size() - 2; i >= 0; --i) {
            b_len -= (W.get(i).length() + 1);
            if (b_len < 0) {
                return (int) min_mess;
            }
            min_mess = Math.min(min_mess, (i - 1 < 0 ? 0 : M[i - 1]));
        }
        return (int) min_mess;
    }
    // @exclude

    public static void main(String[] args) {
        Random r = new Random();
        int n, L;
        if (args.length == 1) {
            n = Integer.parseInt(args[0]);
            L = r.nextInt(10) + 10;
        } else if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            L = Integer.parseInt(args[1]);
        } else {
            n = r.nextInt(30) + 1;
            L = r.nextInt(10) + 11;
        }
        ArrayList<String> W = new ArrayList<String>(n);
        for (int i = 0; i < n; ++i) {
            W.add(rand_string(r.nextInt(10) + 1));
        }
        System.out.println(W);
        System.out.println(L);
        System.out.println(find_pretty_printing(W, L));
    }
}
