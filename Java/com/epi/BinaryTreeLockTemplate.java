package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class BinaryTreeLockTemplate {
    // @include
    public static class BinaryTree<T> {
        private BinaryTree<T> left_, right_, parent_;
        private boolean locked_;
        private int numChildreLocks_;

        public boolean isLock() {
            return locked_;
        }

        public void lock() {
            if(numChildreLocks_ == 0 && !locked_) {
                // Make sure all parents do not lock.
                BinaryTree<T> n = parent_;
                while(n != null) {
                    if(n.locked_) {
                        return;
                    }
                    n = n.parent_;
                }

                // Lock itself and update its parents.
                locked_ = true;
                n = parent_;
                while(n != null) {
                    ++n.numChildreLocks_;
                    n = n.parent_;
                }
            }
        }

        public void unlock() {
            if(locked_) {
                // Unlock itself and update its parents.
                locked_ = false;
                BinaryTree<T> n = parent_;
                while(n != null) {
                    --n.numChildreLocks_;
                    n = n.parent_;
                }
            }
        }
        // @exclude

        public BinaryTree<T> getLeft() {
            return left_;
        }

        public void setLeft(BinaryTree<T> left) {
            left_ = left;
        }

        public BinaryTree<T> getRight() {
            return right_;
        }

        public void setRight(BinaryTree<T> right) {
            right_ = right;
        }

        public void setParent(BinaryTree<T> parent) {
            parent_ = parent;
        }
        // @include
    }
    // @exclude

    public static void main(String[] args) {
        BinaryTree<Integer> root = new BinaryTree<Integer>();
        root.setLeft(new BinaryTree<Integer>());
        root.getLeft().setParent(root);
        root.setRight(new BinaryTree<Integer>());
        root.getRight().setParent(root);
        root.getLeft().setLeft(new BinaryTree<Integer>());
        root.getLeft().getLeft().setParent(root.getLeft());
        root.getLeft().setRight(new BinaryTree<Integer>());
        root.getLeft().getRight().setParent(root.getLeft());
        // Should output false.
        assert(!root.isLock());
        System.out.println(root.isLock());
        root.lock();
        // Should output true.
        assert(root.isLock());
        System.out.println(root.isLock());
        root.unlock();
        root.getLeft().lock();
        root.lock();
        // Should output false.
        assert(!root.isLock());
        System.out.println(root.isLock());
        root.getRight().lock();
        // Should output true.
        assert(root.getRight().isLock());
        System.out.println(root.isLock());
    }
}
