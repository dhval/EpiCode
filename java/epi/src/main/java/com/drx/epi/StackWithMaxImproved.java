package com.drx.epi;

import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class StackWithMaxImproved {
    // @include
    public static class Stack<T extends Comparable<T>> {
        private LinkedList<T> s_ = new LinkedList<T>();
        private LinkedList<Pair<T, Integer>> aux_ = new LinkedList<Pair<T, Integer>>();

        public boolean empty() {
            return s_.isEmpty();
        }

        public T max() {
            if(!empty()) {
                return aux_.peek().getFirst();
            }
            throw new RuntimeException("empty_stack");
        }

        public T pop() {
            if(empty()) {
                throw new RuntimeException("empty_stack");
            }
            T ret = s_.pop();
            if(ret.equals(aux_.peek().getFirst())) {
                aux_.peek().setSecond(aux_.peek().getSecond() - 1);
                if(aux_.peek().getSecond().equals(0)) {
                    aux_.pop();
                }
            }
            return ret;
        }

        public void push(T x) {
            s_.push(x);
            if(!aux_.isEmpty()) {
                if(x.compareTo(aux_.peek().getFirst()) == 0) {
                    aux_.peek().setSecond(aux_.peek().getSecond() + 1);
                } else if(x.compareTo(aux_.peek().getFirst()) > 0) {
                    aux_.push(new Pair<T, Integer>(x, 1));
                }
            } else {
                aux_.push(new Pair<T, Integer>(x, 1));
            }
        }
    }
    // @exclude

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        s.push(1);
        s.push(2);
        assert(s.max() == 2);
        System.out.println(s.max()); // 2
        System.out.println(s.pop()); // 2
        assert(s.max() == 1);
        System.out.println(s.max()); // 1
        s.push(3);
        s.push(2);
        assert(s.max() == 3);
        System.out.println(s.max()); // 3
        s.pop();
        assert(s.max() == 3);
        System.out.println(s.max()); // 3
        s.pop();
        assert(s.max() == 1);
        System.out.println(s.max()); // 1
        s.pop();
        try {
            s.max();
            s.pop();
            s.pop();
            s.pop();
            s.pop();
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
