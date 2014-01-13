package com.drx.epi;

import java.util.ArrayList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
// @include
public class TournamentTree {
    private static class TreeNode {
        public double cap;         // leaf: remaining capacity in the box.
        // non-leaf: max remaining capacity in the subtree.
        public ArrayList<Integer> items = new ArrayList<Integer>();  // stores the items in the leaf node.

        public TreeNode(double cap) {
            this.cap = cap;
        }
    }

    // Stores the complete binary tree. For tree_[i],
    // left subtree is tree_[2i + 1], and right subtree is tree_[2i + 2].
    private ArrayList<TreeNode> tree_;

    // Recursively inserts item in tournament tree.
    private void insertHelper(int idx, int item, double cap) {
        int left = (idx << 1) + 1, right = (idx << 1) + 2;
        if (left < tree_.size()) {  // tree_[idx] is an internal node.
            insertHelper(tree_.get(left).cap >= cap ? left : right, item, cap);
            tree_.get(idx).cap = Math.max(tree_.get(left).cap, tree_.get(right).cap);
        } else {  // tree_[idx] is a leaf node.
            tree_.get(idx).cap -= cap;
            tree_.get(idx).items.add(item);
        }
    }

    // n items, and each box has unit_cap.
    public TournamentTree(int n, double unit_cap) {
        // Complete binary tree with n leafs has 2n - 1 nodes.
        int count = (n << 1) - 1;
        tree_ = new ArrayList<TreeNode>(count);
        for(int i = 0; i < count; i++) {
            tree_.add(new TreeNode(unit_cap));
        }
    }

    public void insert(int item, double item_cap) {
        insertHelper(0, item, item_cap);
    }
    // @exclude
    private void printLeaf() {
        for (int i = 0; i < tree_.size(); ++i) {
            System.out.println("i = " + i + ", cap = " + tree_.get(i).cap);
            for (int item : tree_.get(i).items) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // following is the example in the book.
        TournamentTree t = new TournamentTree(6, 1.0);
        t.insert(0, 0.60);
        t.insert(1, 0.60);
        t.insert(2, 0.55);
        t.insert(3, 0.80);
        t.insert(4, 0.50);
        t.insert(5, 0.45);
        // Due to the precision error of floating point number, Item 5 will be
        // inserted into 5-th box. However, if we are not using floating point
        // number, everything is fine.
        t.printLeaf();
    }
    // @include
}
// @exclude