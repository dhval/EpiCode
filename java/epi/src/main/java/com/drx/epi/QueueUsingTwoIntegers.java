package com.drx.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class QueueUsingTwoIntegers {
    // @include
    public static class Queue {
        private int val_ = 0;
        private int size_ = 0, max_size_ = (int) Math.floor(Math.log10(Integer.MAX_VALUE));

        public void enqueue(int x) {
            if(size_ >= max_size_) {
                throw new RuntimeException("queue overflow");
            }
            val_ = val_ * 10 + x;
            ++size_;
        }

        public int dequeue() {
            if(size_ != 0) {
                int ret = 0, d = (int) Math.floor(Math.log10(val_));
                if(d + 1 == size_) {
                    int pow_val = (int) Math.pow(10, d);
                    ret = val_ / pow_val;
                    val_ -= pow_val * ret;
                }
                --size_;
                return ret;
            }
            throw new RuntimeException("empty queue");
        }
    }
    // @exclude

    private static void assertDequeue(Queue q, int t) {
        int dequeue = q.dequeue();
        assert(t == dequeue);
    }

    public static void main(String[] args) {
        Queue q = new Queue();
        q.enqueue(0);
        q.enqueue(5);
        q.enqueue(0);
        q.enqueue(2);
        assertDequeue(q, 0);
        assertDequeue(q, 5);
        q.enqueue(3);
        assertDequeue(q, 0);
        assertDequeue(q, 2);
        assertDequeue(q, 3);
        q.enqueue(0);
        q.enqueue(0);
        assertDequeue(q, 0);
        assertDequeue(q, 0);
        // Empty queue, it should throw
        try {
            q.dequeue();
        } catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }
        q.enqueue(0);
        q.enqueue(0);
        q.enqueue(0);
        q.enqueue(0);
        q.enqueue(5);
        q.enqueue(0);
        q.enqueue(2);
        q.enqueue(5);
        q.enqueue(0);
        // Queue overflow, it should throw.
        try {
            q.enqueue(2);
        } catch(RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
