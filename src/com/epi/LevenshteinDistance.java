package com.drx.epi;

import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class LevenshteinDistance {
    // @include
    public static int Levenshtein_distance(String A, String B) {
        // Try to reduce the space usage.
        if (A.length() < B.length()) {
            String temp = A;
            A = B;
            B = temp;
        }

        int D[] = new int[B.length() + 1];
        // Initialization.
        for(int i = 0; i < D.length; i++) {
            D[i] = i;
        }

        for (int i = 1; i <= A.length(); ++i) {
            int pre_i_1_j_1 = D[0];  // stores the value of D[i - 1][j - 1].
            D[0] = i;
            for (int j = 1; j <= B.length(); ++j) {
                int pre_i_1_j = D[j];  // stores the value of D[i -1][j].
                D[j] = A.charAt(i - 1) == B.charAt(j - 1) ? pre_i_1_j_1
                        : 1 + Math.min(pre_i_1_j_1, Math.min(D[j - 1], D[j]));
                // Previous D[i - 1][j] will become the next D[i - 1][j - 1].
                pre_i_1_j_1 = pre_i_1_j;
            }
        }
        return D[D.length - 1];
    }
    // @exclude

    private static String rand_string(int len) {
        Random r = new Random();
        StringBuilder ret = new StringBuilder(len);
        while(len-- > 0) {
            ret.append((char)(r.nextInt(26) + 'a'));
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        Random r = new Random();
        String A, B;
        // Wiki example (http://en.wikipedia.org/wiki/Levenshtein_distance)
        A = "Saturday";
        B = "Sunday";
        assert(3 == Levenshtein_distance(A, B));
        A = "kitten";
        B = "sitting";
        assert(3 == Levenshtein_distance(A, B));

        if (args.length== 2) {
            A = args[0];
            B = args[1];
        } else {
            A = rand_string(r.nextInt(100) + 1);
            B = rand_string(r.nextInt(100) + 1);
        }
        System.out.println(A + "\n" + B);
        System.out.println(Levenshtein_distance(A, B));
    }
}
