package com.epi;

import java.util.ArrayList;
import java.util.Random;

import static com.epi.BinaryTreePrototypeTemplate.BinaryTree;
import static com.epi.BinaryTreeUtils.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ReconstructBinaryTreePreInOrdersTemplate {
    // @include
    private static <T> BinaryTree<T> reconstruct_pre_in_orders_helper(
            ArrayList<T> pre, int pre_s, int pre_e,
            ArrayList<T> in, int in_s, int in_e) {
        if(pre_e > pre_s && in_e > in_s) {
            int it = in.subList(in_s, in_e).indexOf(pre.get(pre_s));
            it = it < 0 ? in_e : (it + in_s);
            int left_tree_size = it - in_s;

            return new BinaryTree<T>(pre.get(pre_s),
                    reconstruct_pre_in_orders_helper(
                            pre, pre_s + 1, pre_s + 1 + left_tree_size,
                            in, in_s, it),
                    reconstruct_pre_in_orders_helper(
                            pre, pre_s + 1 + left_tree_size, pre_e,
                            in, it + 1, in_e));
        }
        return null;
    }

    public static <T> BinaryTree<T> reconstruct_pre_in_orders(ArrayList<T> pre,
                                                              ArrayList<T> in) {
        return reconstruct_pre_in_orders_helper(pre, 0, pre.size(),
                                                in, 0, in.size());
    }
    // @exclude

    public static void main(String[] args) {
        Random r = new Random();
        for(int times = 0; times < 1000; ++times) {
            System.out.println(times);
            int n;
            if(args.length == 1) {
                n = Integer.parseInt(args[0]);
            } else {
                n = r.nextInt(10000) + 1;
            }
            BinaryTree<Integer> root = generate_rand_binary_tree(n, true);
            ArrayList<Integer> pre = generate_preorder(root);
            ArrayList<Integer> in = generate_inorder(root);
            BinaryTree<Integer> res = reconstruct_pre_in_orders(pre, in);
            assert(root.equals(res));
        }
    }
}
