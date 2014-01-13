package com.drx.epi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class TwoExists {
    // @include
    public static class GraphVertex {
        public enum Color {
            WHITE,
            GRAY,
            BLACK
        }
        public Color color;
        public ArrayList<GraphVertex> edges;
        // @exclude
        public GraphVertex() {
            color = Color.WHITE;
            edges = new ArrayList<GraphVertex>();
        }

        @Override
        public String toString() {
            return edges.toString();
        }
        // @include
    }

    public static boolean is_graph_2_exist(List<GraphVertex> G) {
        if (!G.isEmpty()) {
            return DFS(G.get(0), null);
        }
        return false;
    }

    private static boolean DFS(GraphVertex cur, GraphVertex pre) {
        // Visiting a gray vertex means a cycle.
        if (cur.color == GraphVertex.Color.GRAY) {
            return true;
        }

        cur.color = GraphVertex.Color.GRAY;  // marks current vertex as a gray one.
        // Traverse the neighbor vertices.
        for (GraphVertex next : cur.edges) {
            if (next != pre && next.color != GraphVertex.Color.BLACK) {
                if (DFS(next, cur)) {
                    return true;
                }
            }
        }
        cur.color = GraphVertex.Color.BLACK;  // marks current vertex as black.
        return false;
    }
    // @exclude

    private static void DFS_exclusion(GraphVertex cur, GraphVertex a, GraphVertex b) {
        cur.color = GraphVertex.Color.BLACK;
        for (GraphVertex next : cur.edges) {
            if (next.color == GraphVertex.Color.WHITE &&
                    ((cur != a && cur != b) || (next != a && next != b))) {
                DFS_exclusion(next, a, b);
            }
        }
    }

    // O(n^2) check answer.
    private static boolean check_answer(List<GraphVertex> G) {
        // marks all vertices as white.
        for (GraphVertex n : G) {
            n.color = GraphVertex.Color.WHITE;
        }

        for (GraphVertex g : G) {
            for (GraphVertex t : g.edges) {
                DFS_exclusion(g, g, t);
                int count = 0;
                for (GraphVertex n : G) {
                    if (n.color == GraphVertex.Color.BLACK) {
                        ++count;
                        n.color = GraphVertex.Color.WHITE;
                    }
                }
                if (count == G.size()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Random r = new Random();
        for (int times = 0; times < 1000; ++times) {
            int n;
            if (args.length == 1) {
                n = Integer.parseInt(args[0]);
            } else {
                n = r.nextInt(1999) + 2;
            }
            ArrayList<GraphVertex> G = new ArrayList<GraphVertex>(n);
            for(int i = 0; i < n; i++) {
                G.add(new GraphVertex());
            }
            int m = r.nextInt(n * (n - 1) / 2) + 1;
            boolean is_edge_exist[][] = new boolean[n][n];
            // Make the graph become connected.
            for (int i = 1; i < n; ++i) {
                G.get(i - 1).edges.add(G.get(i));
                G.get(i).edges.add(G.get(i - 1));
                is_edge_exist[i - 1][i] = is_edge_exist[i][i - 1] = true;
            }
            // Generate edges randomly.
            m -= (n - 1);
            while (m-- > 0) {
                int a, b;
                do {
                    a = r.nextInt(n);
                    b = r.nextInt(n);
                } while (a == b || is_edge_exist[a][b] == true);
                is_edge_exist[a][b] = is_edge_exist[b][a] = true;
                G.get(a).edges.add(G.get(b));
                G.get(b).edges.add(G.get(a));
            }
            //System.out.println(G);
            boolean res = is_graph_2_exist(G);
            System.out.println(res);
            assert(check_answer(G) == res);
        }
    }
}
