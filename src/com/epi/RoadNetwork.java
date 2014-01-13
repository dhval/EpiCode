package com.drx.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class RoadNetwork {
    // @include
    public static class HighwaySection {
        public int x, y;
        public double distance;

        public HighwaySection(int x, int y, double distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return x + " " + y + " " + distance;
        }
    }

    public static HighwaySection find_best_proposals(List<HighwaySection> H,
                                       List<HighwaySection> P,
                                       int a,
                                       int b,
                                       int n) {
        // G stores the shortest path distances between all pairs of vertices.
        double G[][] = new double[n][n];
        for(double g[] : G) {
            Arrays.fill(g, Double.MAX_VALUE);
        }
        for (int i = 0; i < n; ++i) {
            G[i][i] = 0;
        }

        // Build a undirected graph G based on existing highway sections H.
        for (HighwaySection h : H) {
            G[h.x][h.y] = G[h.y][h.x] = h.distance;
        }
        // Perform Floyd Warshall to build the shortest path between vertices.
        Floyd_Warshall(G);

        // Examine each proposal for shorter distance between a and b.
        double min_dis_a_b = G[a][b];
        HighwaySection best_proposal = new HighwaySection(-1, -1, 0.0);  // default
        for (HighwaySection p : P) {
            // Check the path of a => p.x => p.y => b.
            if (G[a][p.x] != Double.MAX_VALUE &&
                    G[p.y][b] != Double.MAX_VALUE &&
                    min_dis_a_b > G[a][p.x] + p.distance + G[p.y][b]) {
                min_dis_a_b = G[a][p.x] + p.distance + G[p.y][b];
                best_proposal = p;
            }
            // Check the path of a => p.y => p.x => b.
            if (G[a][p.y] != Double.MAX_VALUE &&
                    G[p.x][b] != Double.MAX_VALUE &&
                    min_dis_a_b > G[a][p.y] + p.distance + G[p.x][b]) {
                min_dis_a_b = G[a][p.y] + p.distance + G[p.x][b];
                best_proposal = p;
            }
        }
        return best_proposal;
    }

    private static void Floyd_Warshall(double G[][]) {
        for (int k = 0; k < G.length; ++k) {
            for (int i = 0; i < G.length; ++i) {
                for (int j = 0; j < G.length; ++j) {
                    if (G[i][k] != Double.MAX_VALUE &&
                            G[k][j] != Double.MAX_VALUE &&
                            G[i][j] > G[i][k] + G[k][j]) {
                        G[i][j] = G[i][k] + G[k][j];
                    }
                }
            }
        }
    }
    // @exclude

    // Try to add each proposal and use Floyd Warshall to solve, O(n^4) algorithm.
    private static HighwaySection check_ans(List<HighwaySection> H,
                             List<HighwaySection> P,
                             int a,
                             int b,
                             int n) {
        // G stores the shortest path distances between all pairs of vertices.
        double G[][] = new double[n][n];
        for(double g[] : G) {
            Arrays.fill(g, Double.MAX_VALUE);
        }
        for (int i = 0; i < n; ++i) {
            G[i][i] = 0;
        }

        // Build a undirected graph G based on existing highway sections H.
        for (HighwaySection h : H) {
            G[h.x][h.y] = G[h.y][h.x] = h.distance;
        }
        // Perform Floyd Warshall to build the shortest path between vertices.
        Floyd_Warshall(G);

        double best_cost = G[a][b];
        HighwaySection best_proposal = new HighwaySection(-1, -1, 0.0);  // default
        for (HighwaySection p : P) {
            // Create new G_test for Floyd Warshall.
            double G_test[][] = new double[G.length][];
            for(int i = 0; i < G.length; i++) {
                G_test[i] = Arrays.copyOf(G[i], G[i].length);
            }
            G_test[p.x][p.y] = G_test[p.y][p.x] = p.distance;
            Floyd_Warshall(G_test);
            if (best_cost > G_test[a][b]) {
                best_cost = G_test[a][b];
                best_proposal = p;
            }
        }
        return best_proposal;
    }

    public static void main(String[] args) {
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            int n, m, k;
            if (args.length == 1) {
                n = Integer.parseInt(args[0]);
                m = r.nextInt(n * ((n - 1) >> 1) - 1) + 1;
                k = r.nextInt(n * ((n - 1) >> 1) - m) + 1;
            } else if (args.length == 2) {
                n = Integer.parseInt(args[0]);
                m = Integer.parseInt(args[1]);
                k = r.nextInt(n * ((n - 1) >> 1) - m) + 1;
            } else {
                n = r.nextInt(96) + 5;
                m = r.nextInt(n * ((n - 1) >> 1) - 1) + 1;
                k = r.nextInt(n * ((n - 1) >> 1) - m) + 1;
            }
            System.out.println("n = " +n + ", m = " + m + ", k = " + k);
            boolean have_edges[][] = new boolean[n][n];
            ArrayList<HighwaySection> H = new ArrayList<HighwaySection>();  // existing highway sections
            for (int i = 0; i < m; ++i) {
                int a, b;
                do {
                    a = r.nextInt(n);
                    b = r.nextInt(n);
                } while (a == b || have_edges[a][b] == true);
                have_edges[a][b] = have_edges[b][a] = true;
                H.add(new HighwaySection(a, b, r.nextDouble() * 9999 + 1));
            }
            //*
            for (int i = 0; i < m; ++i) {
                System.out.println("H[i] = " + H.get(i));
            }
            //*/
            ArrayList<HighwaySection> P = new ArrayList<HighwaySection>();  // proposals
            for (int i = 0; i < k; ++i) {
                int a, b;
                do {
                    a = r.nextInt(n);
                    b = r.nextInt(n);
                } while (a == b || have_edges[a][b] == true);
                have_edges[a][b] = have_edges[b][a] = true;
                P.add(new HighwaySection(a, b, r.nextDouble() * 49 + 1));
            }
            //*
            for (int i = 0; i < k; ++i) {
                System.out.println("P[i] = " + P.get(i));
            }
            //*/

            int a, b;
            do {
                a = r.nextInt(n);
                b = r.nextInt(n);
            } while (a == b);
            System.out.println("a = " + a + ", b = " + b);
            HighwaySection t = find_best_proposals(H, P, a, b, n);
            System.out.println(t);
            HighwaySection ans = check_ans(H, P, a, b, n);
            System.out.println(ans);
            // TODO(THL): follow assert may fail sometime due to epsilon problem.
            //assert(t.x == ans.x && t.y == ans.y && t.distance == ans.distance);
        }

    }
}
