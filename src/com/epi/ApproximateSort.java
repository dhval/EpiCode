package com.drx.epi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ApproximateSort {
    // @include
    public static void approximate_sort(InputStream sin, int k) {
        PriorityQueue<Integer> min_heap = new PriorityQueue<Integer>();
        try {
            ObjectInputStream osin = new ObjectInputStream(sin);
            // Firstly push k elements into min_heap.
            for(int i = 0; i < k; ++i) {
                min_heap.add((Integer)osin.readObject());
            }

            // Extract the minimum one for every incoming element.
            while(true) {
                min_heap.add((Integer)osin.readObject());
                System.out.println(min_heap.remove());
            }
        } catch(IOException e) {
            // Do nothing, was read last element in stream
        } catch(ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
        }

        // Extract the remaining elements in min_heap.
        while(!min_heap.isEmpty()) {
            System.out.println(min_heap.remove());
        }
    }
    // @exclude

    // It should print 1, 2, 3, 4, 5, 6, 7, 8, 9.
    private static void simple_test() {
        List<Integer> A = Arrays.asList(2, 1, 5, 4, 3, 9, 8, 7, 6);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            for(Integer a : A) {
                oos.writeObject(a);
            }
            ByteArrayInputStream sin = new ByteArrayInputStream(baos.toByteArray());
            approximate_sort(sin, 3);
        } catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        simple_test();
        Random r = new Random();
        int n, k;
        if(args.length == 1) {
            n = Integer.parseInt(args[0]);
            k = r.nextInt(n) + 1;
        } else if(args.length == 2) {
            n = Integer.parseInt(args[0]);
            k = Integer.parseInt(args[1]);
        } else {
            n = r.nextInt(100000) + 1;
            k = r.nextInt(n) + 1;
        }
        System.out.println("n = " + n + " k = " + k);
        ArrayList<Integer> A = new ArrayList<Integer>();
        for(int i = 0; i < n; ++i) {
            A.add(r.nextInt(999999) + 1);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            for(Integer a : A) {
                oos.writeObject(a);
            }
            ByteArrayInputStream sin = new ByteArrayInputStream(baos.toByteArray());
            approximate_sort(sin, n - 1);
        } catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}