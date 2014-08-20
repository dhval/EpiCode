package com.epi;

import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class Utils {
    public static <T extends Comparable<T>> int upper_bound(List<T> arr, T key) {
        int len = arr.size();
        int lo = 0;
        int hi = len - 1;
        int mid = (lo + hi)/2;
        while(true) {
            int cmp = arr.get(mid).compareTo(key);
            if(cmp <= 0) {
                lo = mid + 1;
                if(hi < lo)
                    return mid < len - 1 ? mid + 1 : len;
            } else {
                hi = mid - 1;
                if(hi < lo)
                    return mid;
            }
            mid = (lo + hi)/2;
        }
    }
}
