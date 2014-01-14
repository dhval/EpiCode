package com.epi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class KthElementStreaming {
    // @include
    public static void find_kth_largest_stream(InputStream sin, int k) {
        PriorityQueue<Integer> min_heap = new PriorityQueue<Integer>();
        // The first k elements, output the minimum element.
        Scanner s = new Scanner(sin);
        for (int i = 0; i < k && s.hasNextInt(); ++i) {
            int x = s.nextInt();
            min_heap.add(x);
            System.out.println(min_heap.peek());
        }

        // After the first k elements, output the k-th largest one.
        while (s.hasNextInt()) {
            int x = s.nextInt();
            if (min_heap.peek() < x) {
                min_heap.remove();
                min_heap.add(x);
            }
            System.out.println(min_heap.peek());
        }
    }
    // @exclude

    public static void main(String[] args) {
        Random r = new Random();
        int num, k;
        if (args.length == 1) {
            num = Integer.parseInt(args[0]);
            k = r.nextInt(num) + 1;
        } else if (args.length == 2) {
            num = Integer.parseInt(args[0]);
            k = Integer.parseInt(args[1]);
        } else {
            num = r.nextInt(101);
            k = r.nextInt(num) + 1;
        }
        ArrayList<Integer> stream = new ArrayList<Integer>();
        for (int i = 0; i < num; ++i) {
            stream.add(r.nextInt(10000000));
        }
        Collections.sort(stream);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(baos);
        try {
            for (int i = 0; i < stream.size(); ++i) {
                osw.write(stream.get(i) + " ");
            }
            osw.close();
        } catch(IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        find_kth_largest_stream(bais, k);
        System.out.println("n = " + num + ", k = " + k);
    }
}
