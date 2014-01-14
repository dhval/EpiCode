import java.util.Random;
import java.util.ArrayList;

public class Intersect_sorted_arrays {

  public static void check(ArrayList<Integer> R1, 
                               ArrayList<Integer> R2, 
                               ArrayList<Integer> R3) {
    assert( R1.size() == R2.size() );
    assert( R2.size() == R3.size() );
    for ( int i = 0 ; i < R1.size(); i++ ) {
       int r1 = R1.get(i);
       int r2= R1.get(i);
       int r3 = R1.get(i);
       assert( r1 == r2 );
       assert( r2 == r3 );
    }
  }

  public static void main(String[] args) {
    int N = 100;
    int L = 1000;
    if ( args.length > 0 ) {
      N = new Integer(args[0]);
    } 
    if ( args.length > 1 ) {
      L = new Integer(args[1]);
    } 
    ArrayList<Integer> A = null;
    ArrayList<Integer> B = null;
    Random rnd = new Random(0);
    for ( int i = 0 ; i < N; i++ ) {
      A = new ArrayList<Integer>(L);
      B = new ArrayList<Integer>(L);
      for ( int j = 0 ; j < L; j++ ) {
        A.add( rnd.nextInt() );
        B.add( rnd.nextInt() );
      }
      ArrayList<Integer> R1 = Intersect_sorted_arrays1.intersect_arrs1(A, B); 
      ArrayList<Integer> R2 = Intersect_sorted_arrays2.intersect_arrs2(A, B); 
      ArrayList<Integer> R3 = Intersect_sorted_arrays3.intersect_arrs3(A, B); 
      check(R1, R2, R3);
    }
  }
}
