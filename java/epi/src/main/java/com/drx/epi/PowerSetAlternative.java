package com.drx.epi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class PowerSetAlternative {
    // @include
    private static void generate_power_set_helper(ArrayList<Integer> S, int idx,
                                   LinkedList<Integer> res) {
        if (!res.isEmpty()) {
            // Print the subset.
            Iterator<Integer> iterator = res.iterator();
            while(iterator.hasNext()) {
                System.out.print(iterator.next());
                if(iterator.hasNext())
                    System.out.print(",");
            }
        }
        System.out.println();

        for (int i = idx; i < S.size(); ++i) {
            res.offerLast(S.get(i));
            generate_power_set_helper(S, i + 1, res);
            res.pollLast();
        }
    }

    public static void generate_power_set(ArrayList<Integer> S) {
        LinkedList<Integer> res = new LinkedList<Integer>();
        generate_power_set_helper(S, 0, res);
    }
    // @exclude

    public static void main(String[] args) {
        ArrayList<Integer> S = new ArrayList<Integer>();
        if (args.length >= 1) {
            for (int i = 0; i < args.length; ++i) {
                S.add(Integer.parseInt(args[i]));
            }
        } else {
            Random r = new Random();
            int count = r.nextInt(10) + 1;
            for (int i = 0; i < count; ++i) {
                S.add(i);
            }
        }
        generate_power_set(S);
    }
}
