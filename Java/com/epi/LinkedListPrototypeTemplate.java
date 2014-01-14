// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
// @author Andrey Pavlov

package com.epi;

// @include
class node_t<T> {
  public T data;
  public node_t<T> next;

  node_t(T dt, node_t<T> n) {
    data = dt;
    next = n;
  }
};
// @exclude
