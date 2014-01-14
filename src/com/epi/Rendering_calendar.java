// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.

import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

// @include
class TInterval {
  int start, finish;
};

class TEndpoint implements Comparable<TEndpoint> {
  public int compareTo(TEndpoint e){
    if (time < e.time) return -1;
    if (time > e.time) return 1;
    if (isStart && !e.isStart) return -1;
    if (!isStart && e.isStart) return 1;
    return 0;
    //return time != e.time ? time < e.time : (isStart && !e.isStart);
  }
  
  TEndpoint(int t, boolean is){
    time = t;
    isStart = is;
  }
  
  public int time;
  public boolean isStart;
}

class Rendering_calendar{
  public static int find_max_concurrent_events(ArrayList<TInterval> A) {
    // Build the TEndpoint array.
    ArrayList<TEndpoint> E = new ArrayList<TEndpoint>();
    for (TInterval i : A) {
      E.add(new TEndpoint(i.start, true));
      E.add(new TEndpoint(i.finish, false));
    }
    // Sort the TEndpoint array according to the time.
    Collections.sort(E);    
  
    // Find the maximum number of events overlapped.
    int max_count = 0, count = 0;
    for (TEndpoint e : E) {
      if (e.isStart) {
        max_count = Math.max(++count, max_count);
      } else {
        --count;
      }
    }
    return max_count;
  }
  // @exclude
  
  public static void main(String[] argv) {
    Random gen = new Random();
    int n;
    if (argv.length == 1) {
      n = Integer.parseInt(argv[0]);
    } else {      
      n = gen.nextInt(100000) + 1;
    }
    ArrayList<TInterval> A = new ArrayList<TInterval>();
    for (int i = 0; i < n; ++i) {
      TInterval temp = new TInterval();      
      temp.start = gen.nextInt(99999);
      temp.finish = gen.nextInt(temp.start + 10000) + temp.start + 1;
      A.add(temp);
    }
    int ans = find_max_concurrent_events(A);
    System.out.println(ans);
  }
}