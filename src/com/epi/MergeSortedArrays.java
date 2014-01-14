package com.drx.epi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MergeSortedArrays {
    // @include
    public static ArrayList<Integer> merge_arrays(List<List<Integer>> S) {
        PriorityQueue<Pair<Integer, Integer>> min_heap = new PriorityQueue<Pair<Integer, Integer>>(11, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.getFirst().compareTo(o2.getFirst());
            }
        });
        ArrayList<Integer> S_idx = new ArrayList<Integer>(S.size());

        // Every array in S puts its smallest element in heap.
        for (int i = 0; i < S.size(); ++i) {
            if (S.get(i).size() > 0) {
                min_heap.add(new Pair<Integer, Integer>(S.get(i).get(0), i));
                S_idx.add(1);
            } else {
                S_idx.add(0);
            }
        }

        ArrayList<Integer> ret = new ArrayList<Integer>();
        while (!min_heap.isEmpty()) {
            Pair<Integer, Integer> p = min_heap.remove();
            ret.add(p.getFirst());
            // Add the smallest element into heap if possible.
            if (S_idx.get(p.getSecond())< S.get(p.getSecond()).size()) {
                min_heap.add(new Pair<Integer, Integer>(S.get(p.getSecond()).get(S_idx.get(p.getSecond())), p.getSecond()));
                S_idx.set(p.getSecond(), S_idx.get(p.getSecond()) + 1);
            }
        }
        return ret;
    }
    // @exclude
}
