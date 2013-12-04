package com.drx.epi;

import static com.drx.epi.BinaryTreePrototypeTemplate.BinaryTree;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class SymmetricBinaryTreeTemplate {
    // @include
    private static <T> boolean is_symmetric_helper(BinaryTree<T> l, BinaryTree<T> r) {
        if(l == null && r == null) {
            return true;
        } else if(l != null && r != null) {
            return equals(l.getData(), r.getData()) && is_symmetric_helper(l.getLeft(), l.getRight()) &&
                    is_symmetric_helper(l.getRight(), l.getLeft());
        } else { // (l != null && r == null) || (l == null && r != null)
            return false;
        }
    }

    public static <T> boolean is_symmetric(BinaryTree<T> n) {
        return n == null || is_symmetric_helper(n.getLeft(), n.getRight());
    }
    // @exclude

    private static <T> boolean equals(T t1, T t2) {
        return t1 == null && t2 == null || t1 != null && t1.equals(t2);
    }

    public static void main(String[] args) {
        // non symmetric tree test
        //      3
        //    2   5
        //  1    4 6
        BinaryTree<Integer> non_symm_root = new BinaryTree<Integer>();
        non_symm_root.setLeft(new BinaryTree<Integer>());
        non_symm_root.getLeft().setLeft(new BinaryTree<Integer>());
        non_symm_root.setRight(new BinaryTree<Integer>());
        non_symm_root.getRight().setLeft(new BinaryTree<Integer>());
        non_symm_root.getRight().setRight(new BinaryTree<Integer>());
        assert(!is_symmetric(non_symm_root));
        System.out.println(is_symmetric(non_symm_root));
        // symmetric tree test
        BinaryTree<Integer> symm_root = new BinaryTree<Integer>();
        symm_root.setLeft(new BinaryTree<Integer>());
        symm_root.setRight(new BinaryTree<Integer>());
        assert(is_symmetric(symm_root));
        System.out.println(is_symmetric(symm_root));
    }
}
