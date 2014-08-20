package com.epi;

import java.util.ArrayList;

public class Linked_list<T>{
  
  public class Node {
      public  T   item;
      private Node next;
      private Node prev;
  }
 
  private int N;        // count
  private Node pre;     // sentinel before first item
  private Node post;    // sentinel after last item

  public Linked_list() {
      pre  = new Node();
      post = new Node();
      pre.next = post;
      post.prev = pre;
  }
  
  // add back
  public Node push_back(T item) {
      Node last = post.prev;
      Node x = new Node();
      x.item = item;
      x.next = post;
      x.prev = last;
      post.prev = x;
      last.next = x;
      N++;
      return x;
  }
  
  // add front
  public Node push_front(T item) {
    Node first = pre.next;
    Node x = new Node();
    x.item = item;
    x.next = first;
    x.prev = pre;
    pre.next = x;
    first.prev = x;
    N++;
    return x;
  }
  
  // remove specified node
  public void erase(Node n){
    Node prev = n.prev;
    Node post = n.next;
    prev.next = post;
    post.prev = prev;    
    --N;
  }
  
  public ArrayList<T> serialize(){
    ArrayList<T> res = new ArrayList<T>();
    Node current = pre.next;
    while(current != post){
      res.add(current.item);
      current = current.next;
    }
    return res;
  }
  
  public String toString() {
    StringBuilder s = new StringBuilder();
    Node current = pre.next;
    while(current != post){
      s.append(current.item + " ");
      current = current.next;
    }        
    return s.toString();
  }
  
  public boolean isEmpty()    { return N == 0; }
  public int size()           { return N;      }
  public Node front()         { return pre.next;}
  public Node back()          { return post.prev;}
  
  public static void main(String[] args){
    Linked_list<Integer> ll = new Linked_list<Integer>();
    Linked_list<Integer>.Node n1 = ll.push_back(1);
    Linked_list<Integer>.Node n2 = ll.push_back(2);
    Linked_list<Integer>.Node n0 = ll.push_front(0);
    assert(ll.size() == 3);
    assert(n1.item == 1);
    assert(n2.item == 2);
    assert(n0.item == 0);    
    assert(ll.toString().compareTo("0 1 2 ") == 0);
    ll.push_front(4);
    ll.push_back(5);
    assert(ll.toString().compareTo("4 0 1 2 5 ") == 0);
    assert(ll.back().item == 5);
    assert(ll.front().item == 4);
    ll.erase(n1);
    assert(ll.size() == 4);
    assert(ll.toString().compareTo("4 0 2 5 ") == 0);
    ll.erase(n2);
    assert(ll.size() == 3);
    assert(ll.toString().compareTo("4 0 5 ") == 0);
    ll.erase(n0);
    assert(ll.size() == 2);
    assert(ll.toString().compareTo("4 5 ") == 0);
    ll.erase(ll.front());
    assert(ll.size() == 1);
    assert(ll.toString().compareTo("5 ") == 0);
    ll.erase(ll.front());
    assert(ll.size() == 0);    
    assert(ll.toString().compareTo("") == 0);
    ll.push_front(100);
    assert(ll.size() == 1);    
    assert(ll.toString().compareTo("100 ") == 0);    
  }
}
