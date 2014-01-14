package com.epi;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class QueueWithMax {
    // @include
    public static class Queue<T extends Comparable<T>> {
        private LinkedList<T> A_ = new LinkedList<T>();
        private LinkedList<T> B_ = new LinkedList<T>();

        public void enqueue(T x) {
            A_.push(x);
        }

        public T dequeue() {
            if(B_.isEmpty()) {
                while(!A_.isEmpty()) {
                    B_.push(A_.pop());
                }
            }
            if(!B_.isEmpty()) {
                return B_.pop();
            }
            throw new RuntimeException("empty queue");
        }

        public T max() {
            if (!A_.isEmpty()) {
                return B_.isEmpty() ? Collections.max(A_) :
                        Collections.max(Arrays.asList(Collections.max(A_), Collections.max(B_)));
            } else { // A_.empty() == true.
                if(!B_.isEmpty()) {
                    return Collections.max(B_);
                }
                throw new RuntimeException("empty queue");
            }
        }
    }
    // @exclude

    private static <T extends Comparable<T>> void assertDequeue(Queue<T> q, T t) {
        T dequeue = q.dequeue();
        assert(t.equals(dequeue));
    }

    public static void main(String[] args) {
        Queue<Integer> Q = new Queue<Integer>();
        Q.enqueue(1);
        Q.enqueue(2);
        assert(2 == Q.max());
        assertDequeue(Q, 1);
        assert(2 == Q.max());
        assertDequeue(Q, 2);
        Q.enqueue(3);
        assert(3 == Q.max());
        assertDequeue(Q, 3);
        try {
            Q.max();
        } catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }
        try {
            Q.dequeue();
        } catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
