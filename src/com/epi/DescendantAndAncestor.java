package com.drx.epi;

import static com.drx.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class DescendantAndAncestor {
    // @include
    public static <T extends Comparable<T>> boolean is_r_s_descendant_ancestor_of_m(
            BinaryTree<T> r,
            BinaryTree<T> s,
            BinaryTree<T> m) {
        BinaryTree<T> cur_r = r;
        BinaryTree<T> cur_s = s;

        // Interleaving searches from r and s.
        while (cur_r != null && cur_r != s && cur_s != null && cur_s != r) {
            if (cur_r == m || cur_s == m) {
                return true;
            }
            cur_r = cur_r.getData().compareTo(s.getData()) > 0 ? cur_r.getLeft() : cur_r.getRight();
            cur_s = cur_s.getData().compareTo(r.getData()) > 0 ? cur_s.getLeft() : cur_s.getRight();
        }

        // Reach the other before reaching m.
        if (cur_r == s || cur_s == r) {
            return false;
        }
        // Try to search m before reaching the other.
        return search_m_before_t(cur_r, s, m) || search_m_before_t(cur_s, r, m);
    }

    private static <T extends Comparable<T>> boolean search_m_before_t(BinaryTree<T> p,
                           BinaryTree<T> t,
                           BinaryTree<T> m) {
        while (p != null && p != t && p != m) {
            p = p.getData().compareTo(t.getData()) > 0 ? p.getLeft() : p.getRight();
        }
        return p == m;
    }
    // @exclude

    private static void small_test() {
        BinaryTree<Integer> root = new BinaryTree<Integer>(5);
        root.setLeft(new BinaryTree<Integer>(2));
        root.getLeft().setRight(new BinaryTree<Integer>(4));
        assert(!is_r_s_descendant_ancestor_of_m(root, root.getLeft(), root.getLeft().getRight()));
    }

    public static void main(String[] args) {
        small_test();
        //      3
        //    2   5
        //  1    4 6
        BinaryTree<Integer> root = new BinaryTree<Integer>(3);
        root.setLeft(new BinaryTree<Integer>(2));
        root.getLeft().setLeft(new BinaryTree<Integer>(1));
        root.setRight(new BinaryTree<Integer>(5));
        root.getRight().setLeft(new BinaryTree<Integer>(4));
        root.getRight().setRight(new BinaryTree<Integer>(6));
        assert(is_r_s_descendant_ancestor_of_m(root, root.getRight().getRight(), root.getRight()));
        assert(is_r_s_descendant_ancestor_of_m(root.getRight().getRight(), root, root.getRight()));
        assert(!is_r_s_descendant_ancestor_of_m(root, root.getRight(), root.getRight().getRight()));
        assert(!is_r_s_descendant_ancestor_of_m(root.getRight(), root, root.getRight().getRight()));
        assert(!is_r_s_descendant_ancestor_of_m(
                root.getRight().getLeft(), root.getRight().getRight(), root.getRight()));
        assert(!is_r_s_descendant_ancestor_of_m(
                root.getRight().getLeft(), root.getLeft().getLeft(), root.getRight()));
    }
}
