package com.drx.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ClosestPairPoints {
    private static class Tuple<A, B, C> {
        public A a;
        public B b;
        public C c;

        public Tuple() {
        }

        public Tuple(A a, B b, C c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    // @include
    public static class Point {
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        // @exclude
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
        // @include
    }

    public static Pair<Point, Point> find_closest_pair_points(List<Point> P) {
        Collections.sort(P, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.valueOf(o1.x).compareTo(o2.x);
            }
        });
        Tuple<Point, Point, Double> ret = find_closest_pair_points_helper(P, 0, P.size());
        return new Pair<Point, Point>(ret.a, ret.b);
    }

    // Return the closest two points and its distance as a tuple.
    private static Tuple<Point, Point, Double> find_closest_pair_points_helper(List<Point> P, int s, int e) {
        if (e - s <= 3) {  // brute-force to find answer if there are <= 3 points.
            return brute_force(P, s, e);
        }

        int mid = (e + s) >> 1;
        Tuple<Point, Point, Double> l_ret = find_closest_pair_points_helper(P, s, mid);
        Tuple<Point, Point, Double> r_ret = find_closest_pair_points_helper(P, mid, e);
        Tuple<Point, Point, Double> min_l_r = l_ret.c < r_ret.c ? l_ret : r_ret;
        ArrayList<Point> remain = new ArrayList<Point>();  // stores the points whose x-dis < min_d.
        for (Point p : P) {
            if (Math.abs(p.x - P.get(mid).x) < min_l_r.c) {
                remain.add(p);
            }
        }

        Tuple<Point, Point, Double> mid_ret = find_closest_pair_in_remain(remain, min_l_r.c);
        return mid_ret.c < min_l_r.c ? mid_ret : min_l_r;
    }

    // Return the closest two points and the distance between them.
    private static Tuple<Point, Point, Double> brute_force(List<Point> P,
                                            int s, int e) {
        Tuple<Point, Point, Double> ret = new Tuple<Point, Point, Double>();
        ret.c = Double.MAX_VALUE;
        for (int i = s; i < e; ++i) {
            for (int j = i + 1; j < e; ++j) {
                double dis = distance(P.get(i), P.get(j));
                if (dis < ret.c) {
                    ret = new Tuple<Point, Point, Double>(P.get(i), P.get(j), dis);
                }
            }
        }
        return ret;
    }

    // Return the closest two points and its distance as a tuple.
    private static Tuple<Point, Point, Double> find_closest_pair_in_remain(List<Point> P,
                                                            double d) {
        Collections.sort(P, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.valueOf(o1.y).compareTo(o2.y);
            }
        });

        // At most six points in P.
        Tuple<Point, Point, Double> ret = new Tuple<Point, Point, Double>();
        ret.c = Double.MAX_VALUE;
        for (int i = 0; i < P.size(); ++i) {
            for (int j = i + 1; j < P.size() && P.get(j).y - P.get(i).y < d; ++j) {
                double dis = distance(P.get(i), P.get(j));
                if (dis < ret.c) {
                    ret = new Tuple<Point, Point, Double>(P.get(i), P.get(j), dis);
                }
            }
        }
        return ret;
    }

    private static double distance(Point a, Point b) {
        return Math.sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y));
    }
    // @exclude

    public static void main(String[] args) {
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            int n;
            if (args.length == 2) {
                n = Integer.parseInt(args[0]);
            } else {
                n = r.nextInt(5000) + 1;
            }
            System.out.println("num of points = " + n);
            ArrayList<Point> points = new ArrayList<Point>(n);
            for (int i = 0; i < n; ++i) {
                points.add(new Point(r.nextInt(10000), r.nextInt(10000)));
            }
            Pair<Point, Point> p = find_closest_pair_points(points);
            Tuple<Point, Point, Double> q = brute_force(points, 0, points.size());
            System.out.println("p = " + p + ", dis = " + distance(p.getFirst(), p.getSecond()));
            System.out.println("q = " + q.a + " " + q.b + ", dis = " + distance(q.a, q.b));
            assert(distance(p.getFirst(), p.getSecond()) == q.c);
        }
    }
}
