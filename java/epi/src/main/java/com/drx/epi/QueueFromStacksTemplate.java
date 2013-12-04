package com.drx.epi;

import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class QueueFromStacksTemplate {
    // @include
    public static class Queue<T> {
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
    }
    // @exclude

    private static <T> void assertDequeue(Queue<T> q, T t) {
        T dequeue = q.dequeue();
        assert(t.equals(dequeue));
    }

    public static void main(String[] args) {
        Queue<Integer> Q = new Queue<Integer>();
        Q.enqueue(1);
        Q.enqueue(2);
        assertDequeue(Q, 1);
        assertDequeue(Q, 2);
        Q.enqueue(3);
        assertDequeue(Q, 3);
        try {
            Q.dequeue();
        } catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
