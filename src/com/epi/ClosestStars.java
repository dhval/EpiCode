package com.drx.epi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ClosestStars {
    // @include
    public static class Star implements Comparable<Star>, Serializable {
        private int ID_;
        private double x_, y_, z_;

        public Star() {
        }

        public Star(int ID, double x, double y, double z) {
            ID_ = ID;
            x_ = x;
            y_ = y;
            z_ = z;
        }

        public int getID() {
            return ID_;
        }

        public void setID(int ID) {
            ID_ = ID;
        }

        public double getX() {
            return x_;
        }

        public void setX(double x) {
            x_ = x;
        }

        public double getY() {
            return y_;
        }

        public void setY(double y) {
            y_ = y;
        }

        public double getZ() {
            return z_;
        }

        public void setZ(double z) {
            z_ = z;
        }

        public double distance() {
            return Math.sqrt(x_ * x_ + y_ * y_ + z_ * z_);
        }

        @Override
        public int compareTo(Star s) {
            return Double.valueOf(distance()).compareTo(s.distance());
        }
    }

    public static ArrayList<Star> find_closest_k_stars(InputStream sin, int k) {
        // Use max_heap to find the closest k stars.
        PriorityQueue<Star> max_heap = new PriorityQueue<Star>();
        try {
            ObjectInputStream osin = new ObjectInputStream(sin);

            // Record the first k stars.
            while (true) {
                Star s = (Star) osin.readObject();

                if (max_heap.size() == k) {
                    // Compare the top of heap with the incoming star.
                    Star far_star = max_heap.peek();
                    if (s.compareTo(far_star) < 0) {
                        max_heap.remove();
                        max_heap.add(s);
                    }
                } else {
                    max_heap.add(s);
                }
            }
        } catch(IOException e) {
            // Do nothing, was read last element in stream
        } catch(ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
        }

        // Store the closest k stars.
        ArrayList<Star> closest_stars = new ArrayList<Star>();
        while (!max_heap.isEmpty()) {
            closest_stars.add(max_heap.remove());
        }
        return closest_stars;
    }
    // @exclude

    private static int partition(ArrayList<Star> stars, int left, int right, int pivot_index) {
        double pivot_value = stars.get(pivot_index).distance();
        Collections.swap(stars, pivot_index, right);
        int less_index = left;
        for (int i = left; i < right; ++i) {
            if (stars.get(i).distance() < pivot_value) {
                Collections.swap(stars, i, less_index++);
            }
        }
        Collections.swap(stars, right, less_index);
        return less_index;
    }

    private static ArrayList<Star> select_k(ArrayList<Star> stars, int k) {
        if (stars.size() <= k) {
            return stars;
        }

        Random r = new Random();
        int left = 0, right = stars.size() - 1, pivot_index = -1;
        while (left <= right) {
            pivot_index = partition(stars, left, right, r.nextInt(right - left + 1) + left);
            if (k - 1 < pivot_index) {
                right = pivot_index - 1;
            } else if (k - 1 > pivot_index) {
                left = pivot_index + 1;
            } else {  // k - 1 == pivot_index
                break;
            }
        }

        ArrayList<Star> closest_stars = new ArrayList<Star>();
        double dist = stars.get(pivot_index).distance();
        for (int i = 0; i < stars.size(); ++i) {
            if (dist >= stars.get(i).distance()) {
                closest_stars.add(stars.get(i));
            }
        }
        return closest_stars;
    }

    public static void main(String[] args) {
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            int num, k;
            if (args.length == 1) {
                num = Integer.parseInt(args[0]);
                k = r.nextInt(num) + 1;
            } else if (args.length == 2) {
                num = Integer.parseInt(args[0]);
                k = Integer.parseInt(args[1]);
            } else {
                num = r.nextInt(10000) + 1;
                k = r.nextInt(num) + 1;
            }
            ArrayList<Star> stars = new ArrayList<Star>();
            // randomly generate num of stars
            for (int i = 0; i < num; ++i) {
                stars.add(new Star(i, r.nextInt(100001), r.nextInt(100001), r.nextInt(100001)));
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ByteArrayInputStream sin = null;
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                for(Star s : stars) {
                    oos.writeObject(s);
                    //System.out.println(s.distance());
                }
                oos.close();
                sin = new ByteArrayInputStream(baos.toByteArray());
            } catch(IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
            ArrayList<Star> closest_stars = find_closest_k_stars(sin, k);
            ArrayList<Star> selected_stars = select_k(stars, k);
            Collections.sort(selected_stars);
            Collections.sort(stars);
            System.out.println(k);
            // assert(stars.get(0).getID() == closest_stars.get(0).getID());
            assert(stars.get(k - 1).distance() == selected_stars.get(selected_stars.size() - 1).distance());
        }
    }

}
