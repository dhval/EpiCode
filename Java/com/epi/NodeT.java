package com.epi;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class NodeT {
    private int order;
    private NodeT next, jump;

    public NodeT(int order, NodeT next, NodeT jump) {
        this.order = order;
        this.next = next;
        this.jump = jump;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public NodeT getNext() {
        return next;
    }

    public void setNext(NodeT next) {
        this.next = next;
    }

    public NodeT getJump() {
        return jump;
    }

    public void setJump(NodeT jump) {
        this.jump = jump;
    }
}
