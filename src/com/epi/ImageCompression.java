package com.epi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ImageCompression {
    // @include
    public static class Point {
        public int i, j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public boolean isGreater(Point that) {
            return i > that.i || j > that.j;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if(i != point.i) return false;
            if(j != point.j) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31*result + j;
            return result;
        }

        public String toString() {
            return "(" + i + " " + j + ")";
        }
    }

    public static class TreeNode {
        public int node_num;  // stores the number of node in its subtree.

        public Point lowerLeft, upperRight;

        // Store the SW, NW, NE, and SE rectangles if color is mixed.
        public List<TreeNode> children = new ArrayList<TreeNode>();

        public TreeNode(int node_num, Point lowerLeft, Point upperRight) {
            this.node_num = node_num;
            this.lowerLeft = lowerLeft;
            this.upperRight = upperRight;
        }
    }

    public static boolean is_monochromatic(int image_sum[][],
                          Point lower_left,
                          Point upper_right) {
        int pixel_sum = image_sum[upper_right.i][upper_right.j];
        if (lower_left.i >= 1) {
            pixel_sum -= image_sum[lower_left.i - 1][upper_right.j];
        }
        if (lower_left.j >= 1) {
            pixel_sum -= image_sum[upper_right.i][lower_left.j - 1];
        }
        if (lower_left.i >= 1 && lower_left.j >= 1) {
            pixel_sum += image_sum[lower_left.i - 1][lower_left.j - 1];
        }
        return pixel_sum == 0 ||                                  // totally white.
                pixel_sum == (upper_right.i - lower_left.i + 1) *  // totally black.
                        (upper_right.j - lower_left.j + 1);
    }

    public static TreeNode calculate_optimal_2D_tree_helper(
            int image[][],
            int image_sum[][],
            Point lower_left,
            Point upper_right,
            HashMap<Point,HashMap<Point, TreeNode>> table) {
        // Illegal rectangle region, returns empty node.
        if (lower_left.isGreater(upper_right)) {
            return new TreeNode(0, lower_left, upper_right);
        }
        if(!table.containsKey(lower_left)) {
            table.put(lower_left, new HashMap<Point, TreeNode>());
        }
        if (!table.get(lower_left).containsKey(upper_right)) {
            if (is_monochromatic(image_sum, lower_left, upper_right)) {
                table.get(lower_left).put(upper_right, new TreeNode(1, lower_left, upper_right));
            } else {
                TreeNode p = new TreeNode(Integer.MAX_VALUE, lower_left, upper_right);
                for (int s = lower_left.i; s <= upper_right.i + 1; ++s) {
                    for (int t = lower_left.j; t <= upper_right.j + 1; ++t) {
                        if ((s != lower_left.i && s != upper_right.i + 1) ||
                                (t != lower_left.j && t != upper_right.j + 1)) {
                            ArrayList<TreeNode> children = new ArrayList<TreeNode>();
                                    // SW rectangle.
                            children.add(calculate_optimal_2D_tree_helper(
                                            image, image_sum, lower_left, new Point(s - 1, t - 1), table));
                                    // NW rectangle.
                            children.add(calculate_optimal_2D_tree_helper(image,
                                            image_sum,
                                            new Point(lower_left.i, t),
                                            new Point(s - 1, upper_right.j),
                                            table));
                                    // NE rectangle.
                            children.add(calculate_optimal_2D_tree_helper(
                                    image, image_sum, new Point(s, t), upper_right, table));
                                    // SE rectangle.
                            children.add(calculate_optimal_2D_tree_helper(image,
                                    image_sum,
                                    new Point(s, lower_left.j),
                                    new Point(upper_right.i, t - 1),
                                    table));

                            int node_num = 1;  // itself.
                            ArrayList<TreeNode> toRemove = new ArrayList<TreeNode>();
                            for (TreeNode child : children) {
                                node_num += child.node_num;
                                // Remove the child contains no node.
                                if (child.node_num == 0) {
                                    toRemove.add(child);
                                }
                            }
                            children.removeAll(toRemove);
                            if (node_num < p.node_num) {
                                p.node_num = node_num;
                                p.children = children;
                            }
                        }
                    }
                }
                table.get(lower_left).put(upper_right, p);
            }
        }
        return table.get(lower_left).get(upper_right);
    }

    public static TreeNode calculate_optimal_2D_tree(
            int image[][]) {
        int image_sum[][] = new int[image.length][image[0].length];
        for (int i = 0; i < image.length; ++i) {
            int summ = 0;
            for(int j = 0; j < image[i].length; ++j) {
                summ += image[i][j];
                image_sum[i][j] = summ;
            }
            for (int j = 0; i > 0 && j < image[i].length; ++j) {
                image_sum[i][j] += image_sum[i - 1][j];
            }
        }

        HashMap<Point, HashMap<Point, TreeNode>> table = new HashMap<Point, HashMap<Point, TreeNode>>();
        return calculate_optimal_2D_tree_helper(
                image,
                image_sum,
                new Point(0, 0),
                new Point(image.length - 1, image[0].length - 1),
                table);
    }
    // @exclude

    private static void recursive_print_tree(TreeNode r) {
        System.out.println(r.lowerLeft + "-" + r.upperRight);
        for (TreeNode ptr : r.children) {
            if(ptr != null) {
                recursive_print_tree(ptr);
            }
        }
    }

    public static void main(String[] args) {
        Random r = new Random();
        int m, n;
        if (args.length == 2) {
            m = Integer.parseInt(args[0]);
            n = Integer.parseInt(args[1]);
        } else {
            m = r.nextInt(20) + 1;
            n = r.nextInt(20) + 1;
        }
        int image[][] = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                image[i][j] = r.nextInt(2);
            }
        }
        System.out.println(m + " " + n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(image[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        TreeNode res = calculate_optimal_2D_tree(image);
        recursive_print_tree(res);
    }
}
