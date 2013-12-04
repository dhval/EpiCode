package com.drx.epi;

import java.util.ArrayList;
import java.util.Random;

import static com.drx.epi.BinaryTreePrototypeTemplate.BinaryTree;
import static com.drx.epi.BinaryTreeUtils.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ReconstructBinaryTreePostInOrdersTemplate {
    // @include
    private static <T> BinaryTree<T> reconstruct_post_in_orders_helper(ArrayList<T> post, int post_s, int post_e,
                                                                       ArrayList<T> in, int in_s, int in_e) {
        if(post_e > post_s && in_e > in_s) {
            int it = in.subList(in_s, in_e).indexOf(post.get(post_e - 1));
            it = it < 0 ? in_e : (it + in_s);
            int left_tree_size = it - in_s;
            return new BinaryTree<T>(post.get(post_e - 1),
                    // Recursively build the left subtree.
                    reconstruct_post_in_orders_helper(
                            post, post_s, post_s + left_tree_size,
                            in, in_s, it),
                    // Recursively build the right subtree.
                    reconstruct_post_in_orders_helper(
                            post, post_s + left_tree_size, post_e - 1,
                            in, it + 1, in_e));
        }
        return null;
    }

    public static <T> BinaryTree<T> reconstruct_post_in_orders(ArrayList<T> post,
                                                               ArrayList<T> in) {
        return reconstruct_post_in_orders_helper(post, 0, post.size(),
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
            ArrayList<Integer> post = generate_postorder(root);
            ArrayList<Integer> in = generate_inorder(root);
            BinaryTree<Integer> res = reconstruct_post_in_orders(post, in);
            assert(root.equals(res));
        }
    }
}
