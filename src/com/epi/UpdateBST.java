package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class UpdateBST {
    // @include
    public static class BinarySearchTree<T extends Comparable<T>> {
        private static class TreeNode<T> {
            public T data;
            public TreeNode<T> left, right;

            public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
                this.data = data;
                this.left = left;
                this.right = right;
            }
        }

        private TreeNode<T> root_;

        public boolean empty() {
            return root_ == null;
        }

        public void clear() {
            root_ = null;
        }

        public boolean insert(T key) {
            if (empty()) {
                root_ = new TreeNode<T>(key, null, null);
            } else {
                TreeNode<T> curr = root_;
                TreeNode<T> par = curr;
                while (curr != null) {
                    par = curr;
                    if (key.compareTo(curr.data) == 0) {
                        return false;  // no insertion for duplicate key.
                    } else if (key.compareTo(curr.data) < 0) {
                        curr = curr.left;
                    } else {  // key.compareTo(curr.data) > 0.
                        curr = curr.right;
                    }
                }

                // Insert key according to key and par.
                if (key.compareTo(par.data) < 0) {
                    par.left = new TreeNode<T>(key, null, null);
                } else {
                    par.right = new TreeNode<T>(key, null, null);
                }
            }
            return true;
        }

        public boolean erase(T key) {
            // Find the node with key.
            TreeNode<T> curr = root_;
            TreeNode<T> par = null;
            while (curr != null && curr.data.compareTo(key) != 0) {
                par = curr;
                curr = key.compareTo(curr.data) < 0 ? curr.left : curr.right;
            }

            // No node with key in this binary tree.
            if (curr == null) {
                return false;
            }

            if (curr.right != null) {
                // Find the minimum of the right subtree.
                TreeNode<T> r_curr = curr.right;
                TreeNode<T> r_par = curr;
                while (r_curr.left != null) {
                    r_par = r_curr;
                    r_curr = r_curr.left;
                }
                // Move links to erase the node.
                r_curr.left = curr.left;
                curr.left = null;
                TreeNode<T> r_curr_right = r_curr.right;
                r_curr.right = null;
                if (curr.right != r_curr) {
                    r_curr.right = curr.right;
                    curr.right = null;
                }
                if (r_par.left == r_curr) {
                    r_curr = r_par.left;
                    r_par.left = null;
                    r_par.left = r_curr_right;
                } else {  // r_par.left != r_curr.
                    r_curr = r_par.right;
                    r_par.right = r_curr_right;
                }
                ReplaceParentChildLink(par, curr, r_curr);

                // Update root_ link if needed.
                if (root_ == curr) {
                    root_ = r_curr;
                }
            } else {
                // Update root_ link if needed.
                if (root_ == curr) {
                    root_ = curr.left;
                    curr.left = null;
                }
                ReplaceParentChildLink(par, curr, curr.left);
            }
            return true;
        }

        // Replace the link between par and child by new_link.
        private void ReplaceParentChildLink(TreeNode<T> par,
                                    TreeNode<T> child,
                                    TreeNode<T> new_link) {
            if (par == null) {
                return;
            }

            if (par.left == child) {
                par.left = new_link;
            } else {  // par->right.get() == child.
                par.right = new_link;
            }
        }

        // @exclude
        public T GetRootVal() {
            return root_.data;
        }
        // @include
    }
    // @exclude

    public static void main(String[] args) {
        BinarySearchTree<Integer> BST = new BinarySearchTree<Integer>();
        assert(BST.empty() == true);
        assert(BST.insert(4) == true);
        assert(BST.insert(5) == true);
        assert(BST.insert(2) == true);
        assert(BST.insert(3) == true);
        assert(BST.insert(1) == true);
        assert(BST.empty() == false);
        assert(BST.erase(0) == false);
        assert(BST.erase(2) == true);
        assert(BST.erase(2) == false);
        assert(BST.insert(4) == false);
        // should output 4
        assert(BST.GetRootVal() == 4);
        System.out.println(BST.GetRootVal());
        assert(BST.erase(4) == true);
        // should output 5
        assert(BST.GetRootVal() == 5);
        System.out.println(BST.GetRootVal());
        assert(BST.erase(5) == true);
        // should output 3
        assert(BST.GetRootVal() == 3);
        System.out.println(BST.GetRootVal());
        assert(BST.erase(3) == true);
        // should output 1
        assert(BST.GetRootVal() == 1);
        System.out.println(BST.GetRootVal());
        assert(BST.erase(1) == true);
        assert(BST.empty() == true);
    }
}
