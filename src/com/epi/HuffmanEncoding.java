package com.drx.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class HuffmanEncoding {
    private static final double EnglishFreq[] = {8.167, 1.492, 2.782, 4.253, 12.702, 2.228, 2.015,
            6.094, 6.966, 0.153, 0.772, 4.025,  2.406, 6.749,
            7.507, 1.929, 0.095, 5.987, 6.327,  9.056, 2.758,
            0.978, 2.360, 0.150, 1.974, 0.074};

    // @include
    public static class Symbol {
        public char c;
        public double prob;
        public String code;

        public String toString() {
            return c + " " + prob + " " + code;
        }
    }

    public static class BinaryTree implements Comparable<BinaryTree> {
        public double prob;
        public ObjectWrapper<Symbol> s;
        public BinaryTree left, right;

        public BinaryTree(double prob, ObjectWrapper<Symbol> s, BinaryTree left, BinaryTree right) {
            this.prob = prob;
            this.s = s;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(BinaryTree o) {
            return Double.compare(o.prob, prob);
        }
    }

    public static void Huffman_encoding(List<ObjectWrapper<Symbol>> symbols) {
        // Initially assign each symbol into min->heap.
        PriorityQueue<BinaryTree> min_heap = new PriorityQueue<BinaryTree>();
        for (ObjectWrapper<Symbol> s : symbols) {
            min_heap.add(new BinaryTree(s.get().prob, s, null, null));
        }

        // Keep combining two nodes until there is one node left.
        while (min_heap.size() > 1) {
            BinaryTree l = min_heap.remove();
            BinaryTree r = min_heap.remove();
            min_heap.add(new BinaryTree(l.prob + r.prob, null, l, r));
        }

        // Traverse the binary tree and assign code.
        assign_huffman_code(min_heap.peek(), "");
    }

    // Traverse tree and assign code.
    private static void assign_huffman_code(BinaryTree r, String s) {
        if (r != null) {
            // This node (i.e.,leaf) contains symbol.
            if (r.s != null) {
                r.s.get().code = s;
            } else {  // non-leaf node.
                assign_huffman_code(r.left, s + "0");
                assign_huffman_code(r.right, s + "1");
            }
        }
    }
    // @exclude

    public static void main(String[] args) {
        int n;
        Random r = new Random();
        if (args.length == 1) {
            if (!"huffman".equals(args[0])) {
                n = Integer.parseInt(args[0]);
            } else {
                n = 26;
            }
        } else {
            n = r.nextInt(255) + 1;
        }
        ArrayList<ObjectWrapper<Symbol>> symbols = new ArrayList<ObjectWrapper<Symbol>>();
        int sum = 0;
        if (args.length == 0 || (!"huffman".equals(args[0]))) {
            for (int i = 0; i < n; ++i) {
                Symbol t = new Symbol();
                t.c = (char)i;
                t.prob = r.nextInt(100001);
                sum += t.prob;
                symbols.add(new ObjectWrapper<Symbol>(t));
            }
            for (int i = 0; i < n; ++i) {
                symbols.get(i).get().prob /= sum;
            }
        } else {
            for (int i = 0; i < n; ++i) {
                Symbol t = new Symbol();
                t.c = (char)('a' + i);
                t.prob = EnglishFreq[i];
                symbols.add(new ObjectWrapper<Symbol>(t));
            }
        }
        Huffman_encoding(symbols);
        double avg = 0.0;
        for (int i = 0; i < symbols.size(); ++i) {
            System.out.println(symbols.get(i).get().c);
            avg += symbols.get(i).get().prob / 100 * symbols.get(i).get().code.length();
        }
        System.out.println("average huffman code length = " + avg);
    }
}
