package com.epi;

import java.util.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class JobAssignment {
    private static <T> void nth_element(List<T> A, int n, Comparator<T> c) {
        Collections.sort(A, c);
    }

    // @include
    public static boolean[][] find_feasible_job_assignment(List<Integer> T,
                                                                              List<Integer> S) {
        int T_total = 0;
        for(Integer t : T) {
            T_total += t;
        }
        int S_total = 0;
        for(Integer s : S) {
            S_total += Math.min(s, T.size());
        } // tighter bound of server capacity.

        if (T_total > S_total || Collections.max(T) > S.size()) {
            return new boolean[0][0];  // too many jobs or one task needs too many servers.
        }

        ArrayList<Pair<Integer, Integer>> T_idx_data = new ArrayList<Pair<Integer, Integer>>();
        ArrayList<Pair<Integer, Integer>> S_idx_data = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < T.size(); ++i) {
            T_idx_data.add(new Pair<Integer, Integer>(i, T.get(i)));
        }
        for (int j = 0; j < S.size(); ++j) {
            S_idx_data.add(new Pair<Integer, Integer>(j, S.get(j)));
        }

        Collections.sort(S_idx_data, new Comp());
        boolean X[][] = new boolean[T.size()][S.size()];
        for (int j = 0; j < S_idx_data.size(); ++j) {
            if (S_idx_data.get(j).getSecond() < T_idx_data.size()) {
                nth_element(T_idx_data,
                        S_idx_data.get(j).getSecond(),
                        new Comp());
            }

            // Greedily assign jobs.
            int size = Math.min(T_idx_data.size(), S_idx_data.get(j).getSecond());
            for (int i = 0; i < size; ++i) {
                if (T_idx_data.get(i).getSecond() != 0) {
                    X[T_idx_data.get(i).getFirst()][S_idx_data.get(j).getFirst()] = true;
                    T_idx_data.get(i).setSecond(T_idx_data.get(i).getSecond() - 1);
                    --T_total;
                }
            }
        }
        if (T_total != 0) {
            return new boolean[0][0];  // still some jobs remain, no feasible assignment.
        }
        return X;
    }

    private static class Comp implements Comparator<Pair<Integer, Integer>> {
        @Override
        public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
            return o1.getSecond().compareTo(o2.getSecond());
        }
    }
    // @exclude

    private static void check_answer(List<Integer> T,
                      List<Integer> S,
                      boolean res[][]) {
        // Check row constraints.
        for (int i = 0; i < T.size(); ++i) {
            int sum = 0;
            for (int j = 0; j < S.size(); ++j) {
                sum += res[i][j] ? 1 : 0;
            }
            assert(sum == T.get(i));
        }

        // Check column constraints.
        for (int j = 0; j < S.size(); ++j) {
            int sum = 0;
            for (int i = 0; i < T.size(); ++i) {
                sum += res[i][j] ? 1 : 0;
            }
            assert(sum <= S.get(j));
        }
    }

    public static void main(String[] args) {
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            int n, m;
            ArrayList<Integer> T = new ArrayList<Integer>();
            ArrayList<Integer> S = new ArrayList<Integer>();
            if (args.length == 2) {
                n = Integer.parseInt(args[0]);
                m = Integer.parseInt(args[1]);
            } else {
                n = r.nextInt(100) + 1;
                m = r.nextInt(100) + 1;
            }
            for (int i = 0; i < n; ++i) {
                T.add(r.nextInt(5) + 1);
            }
            for (int i = 0; i < m; ++i) {
                S.add(r.nextInt(10) + 1);
            }
            System.out.println("T = " + T);
            System.out.println("S = " + S);
            boolean res[][] = find_feasible_job_assignment(T, S);
            if (res.length != 0) {  // there is a feasible answer.
                System.out.println("found feasible assignment!");
                for (int i = 0; i < res.length; ++i) {
                    System.out.println(Arrays.toString(res[i]));
                }
                check_answer(T, S, res);
            } else {
                // TODO(THL): find a way to verify there is no assignment.
                System.out.println("no feasible assignment");
            }
        }
    }
}
