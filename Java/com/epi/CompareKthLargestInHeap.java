package com.epi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class CompareKthLargestInHeap {
    // @include
    private static class Data {
        public int larger;
        public int equal;
    }

    private static void compare_kth_largest_heap_helper(List<Integer> max_heap,
                                         int k,
                                         int x,
                                         int idx,
                                         Data data) {
        if (data.larger >= k || idx >= max_heap.size() || max_heap.get(idx) < x) {
            return;
        } else if (max_heap.get(idx) == x) {
            if (++data.equal >= k) {
                return;
            }
        } else {  // max_heap[idx] > x.
            ++data.larger;
        }
        compare_kth_largest_heap_helper(
                max_heap, k, x, (idx << 1) + 1, data);
        compare_kth_largest_heap_helper(
                max_heap, k, x, (idx << 1) + 2, data);
    }

    // -1 means smaller, 0 means equal, and 1 means larger.
    public static int compare_kth_largest_heap(List<Integer> max_heap, int k, int x) {
        Data data = new Data();
        data.larger = 0;
        data.equal = 0;
        compare_kth_largest_heap_helper(max_heap, k, x, 0, data);
        return data.larger >= k ? 1 : (data.larger + data.equal >= k ? 0 : -1);
    }
    // @exclude

    public static void main(String[] args) {
        //      5
        //    4  5
        //  4  4 4 3
        // 4
        ArrayList<Integer> max_heap = new ArrayList<Integer>();
        max_heap.add(5);
        max_heap.add(4);
        max_heap.add(5);
        max_heap.add(4);
        max_heap.add(4);
        max_heap.add(4);
        max_heap.add(3);
        max_heap.add(4);
        int k, x;
        if (args.length == 2) {
            k = Integer.parseInt(args[0]);
            x = Integer.parseInt(args[1]);
            int res = compare_kth_largest_heap(max_heap, k, x);
            System.out.println((res == -1 ? "smaller" : (res == 0 ? "equal" : "larger")));
        } else {
            assert(-1 == compare_kth_largest_heap(max_heap, 1, 6));  // expect smaller
            assert(0 == compare_kth_largest_heap(max_heap, 1, 5));   // expect equal
            assert(0 == compare_kth_largest_heap(max_heap, 6, 4));   // expect equal
            assert(0 == compare_kth_largest_heap(max_heap, 3, 4));   // expect equal
            assert(-1 == compare_kth_largest_heap(max_heap, 8, 4));  // expect smaller
            assert(1 == compare_kth_largest_heap(max_heap, 2, 4));   // expect larger
            assert(1 == compare_kth_largest_heap(max_heap, 2, 3));   // expect larger
        }
    }
}
