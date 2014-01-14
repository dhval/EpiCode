package com.epi;

import java.util.ArrayList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class TreeDiameter {
    // @include
    public static class TreeNode {
        ArrayList<Pair<TreeNode, Double>> edges = new ArrayList<Pair<TreeNode, Double>>();
    }

    public static double compute_diameter(TreeNode T) {
        return T != null ? compute_height_and_diameter(T).getSecond() : 0.0;
    }

    // Return (height, diameter) pair.
    private static Pair<Double, Double> compute_height_and_diameter(TreeNode r) {
        double diameter = Double.MIN_VALUE;
        double height[] = {0.0, 0.0};  // store the max two heights.
        for (Pair<TreeNode, Double> e : r.edges) {
            Pair<Double, Double> h_d = compute_height_and_diameter(e.getFirst());
            if (h_d.getFirst() + e.getSecond() > height[0]) {
                height[1] = height[0];
                height[0] = h_d.getFirst() + e.getSecond();
            } else if (h_d.getFirst() + e.getSecond() > height[1]) {
                height[1] = h_d.getFirst() + e.getSecond();
            }
            diameter = Math.max(diameter, h_d.getSecond());
        }
        return new Pair<Double, Double>(height[0], Math.max(diameter, height[0] + height[1]));
    }
    // @exclude

    public static void main(String[] args) {
        TreeNode r = null;
        assert(0.0 == compute_diameter(r));
        r = new TreeNode();
        r.edges.add(new Pair<TreeNode, Double>(new TreeNode(), 10.0));
        r.edges.get(0)
                .getFirst().edges.add(new Pair<TreeNode, Double>(new TreeNode(), 50.0));
        r.edges.add(new Pair<TreeNode, Double>(new TreeNode(), 20.0));
        assert(80 == compute_diameter(r));
        System.out.println(compute_diameter(r));
        r.edges.get(0)
                .getFirst().edges.add(new Pair<TreeNode, Double>(new TreeNode(), 100.0));
        assert(150 == compute_diameter(r));
        System.out.println(compute_diameter(r));
    }
}
