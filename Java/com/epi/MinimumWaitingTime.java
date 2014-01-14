package com.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class MinimumWaitingTime {
    // @include
    public static int minimum_waiting_time(List<Integer> service_time) {
        // Sort the query time in increasing order.
        Collections.sort(service_time);

        int waiting = 0;
        for (int i = 0; i < service_time.size(); ++i) {
            waiting += service_time.get(i) * (service_time.size() - (i + 1));
        }
        return waiting;
    }
    // @exclude

    public static void main(String[] args) {
        Random r = new Random();
        int n;
        if (args.length == 1) {
            n = Integer.parseInt(args[0]);
        } else {
            n = r.nextInt(100) + 1;
        }
        ArrayList<Integer> waiting_time = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            waiting_time.add(r.nextInt(1000));
        }
        System.out.println(waiting_time);
        System.out.println(minimum_waiting_time(waiting_time));
    }
}
