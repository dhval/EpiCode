package com.drx.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class DrawingSkylines {
    // @include
    public static class Skyline {
        public int left, right, height;

        public Skyline(int left, int right, int height) {
            this.left = left;
            this.right = right;
            this.height = height;
        }
    }

    public static List<Skyline> drawing_skylines(List<Skyline> skylines) {
        return drawing_skylines_helper(skylines, 0, skylines.size());
    }

    private static List<Skyline> drawing_skylines_helper(List<Skyline> skylines,
                                            int start,
                                            int end) {
        if (end - start <= 1) {  // 0 or 1 skyline, just copy it.
            return new ArrayList<Skyline>(skylines.subList(start, end));
        }
        int mid = start + ((end - start) >> 1);
        List<Skyline> L = drawing_skylines_helper(skylines, start, mid);
        List<Skyline> R = drawing_skylines_helper(skylines, mid, end);
        return merge_skylines(L, R);
    }

    private static List<Skyline> merge_skylines(List<Skyline> L, List<Skyline> R) {
        int i = 0, j = 0;
        ArrayList<Skyline> merged = new ArrayList<Skyline>();

        while (i < L.size() && j < R.size()) {
            if (L.get(i).right < R.get(j).left) {
                merged.add(L.get(i++));
            } else if (R.get(j).right < L.get(i).left) {
                merged.add(R.get(j++));
            } else if (L.get(i).left <= R.get(j).left) {
                ObjectWrapper<Integer> iWrapper = new ObjectWrapper<Integer>(i);
                ObjectWrapper<Integer> jWrapper = new ObjectWrapper<Integer>(j);
                merge_intersect_skylines(merged, L.get(i), iWrapper, R.get(j), jWrapper);
                i = iWrapper.get();
                j = jWrapper.get();
            } else {  // L.get(i).left > R.get(j).left.
                ObjectWrapper<Integer> iWrapper = new ObjectWrapper<Integer>(i);
                ObjectWrapper<Integer> jWrapper = new ObjectWrapper<Integer>(j);
                merge_intersect_skylines(merged, R.get(j), jWrapper, L.get(i), iWrapper);
                i = iWrapper.get();
                j = jWrapper.get();
            }
        }
        merged.addAll(L.subList(i, L.size()));
        merged.addAll(R.subList(j, R.size()));
        return merged;
    }

    private static void merge_intersect_skylines(List<Skyline> merged,
                                  Skyline a,
                                  ObjectWrapper<Integer> a_idx,
                                  Skyline b,
                                  ObjectWrapper<Integer> b_idx) {
        if (a.right <= b.right) {
            if (a.height > b.height) {
                if (b.right != a.right) {
                    merged.add(a);
                    a_idx.set(a_idx.get() + 1);
                    b.left = a.right;
                } else {
                    b_idx.set(b_idx.get() + 1);
                }
            } else if (a.height == b.height) {
                b.left = a.left;
                a_idx.set(a_idx.get() + 1);
            } else {  // a->height < b->height.
                if (a.left != b.left) {
                    merged.add(new Skyline(a.left, b.left, a.height));
                }
                a_idx.set(a_idx.get() + 1);
            }
        } else {  // a.right > b.right.
            if (a.height >= b.height) {
                b_idx.set(b_idx.get() + 1);
            } else {
                if (a.left != b.left) {
                    merged.add(new Skyline(a.left, b.left, a.height));
                }
                a.left = b.right;
                merged.add(b);
                b_idx.set(b_idx.get() + 1);
            }
        }
    }
    // @exclude

    public static void main(String[] args) {
        Random r = new Random();
        // Random test 2000 times.
        for (int times = 0; times < 2000; ++times) {
            int n;
            if (args.length == 1) {
                n = Integer.parseInt(args[0]);
            } else {
                n = r.nextInt(5000) + 1;
            }
            ArrayList<Skyline> A = new ArrayList<Skyline>();
            for (int i = 0; i < n; ++i) {
                int left = r.nextInt(1000);
                int right = r.nextInt(201) + left;
                int height = r.nextInt(100);
                A.add(new Skyline(left, right, height));
            }
            List<Skyline> ans = drawing_skylines(A);
            System.out.println("n = " + n);
            // just check there is no overlap.
            for (int i = 0; i < ans.size(); ++i) {
                assert(ans.get(i).left <= ans.get(i).right);
                if (i > 0) {
                    assert(ans.get(i - 1).right <= ans.get(i).left);
                    assert(ans.get(i - 1).right != ans.get(i).left ||
                            ans.get(i - 1).height != ans.get(i).height);
                }
            }
        }
    }
}
