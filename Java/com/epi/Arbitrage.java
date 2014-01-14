package com.epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class Arbitrage {
    // @include
    public static boolean is_Arbitrage_exist(List<? extends List<Double>> G) {
        // Transform each edge in G.
        for (List<Double> edge_list : G) {
            for(int i = 0; i < edge_list.size(); i++) {
                edge_list.set(i, -Math.log10(edge_list.get(i)));
            }
        }

        // Use Bellman-Ford to find negative weight cycle.
        return Bellman_Ford(G, 0);
    }

    private static boolean Bellman_Ford(List<? extends List<Double>> G, int source) {
        double dis_to_source[] = new double[G.size()];
        Arrays.fill(dis_to_source, Double.MAX_VALUE);
        dis_to_source[source] = 0;

        for (int times = 1; times < G.size(); ++times) {
            boolean have_update = false;
            for (int i = 0; i < G.size(); ++i) {
                for (int j = 0; j < G.get(i).size(); ++j) {
                    if (dis_to_source[i] != Double.MAX_VALUE &&
                            dis_to_source[j] > dis_to_source[i] + G.get(i).get(j)) {
                        have_update = true;
                        dis_to_source[j] = dis_to_source[i] + G.get(i).get(j);
                    }
                }
            }

            // No update in this iteration means no negative cycle.
            if (have_update == false) {
                return false;
            }
        }

        // Detect cycle if there is any further update.
        for (int i = 0; i < G.size(); ++i) {
            for (int j = 0; j < G.get(i).size(); ++j) {
                if (dis_to_source[i] != Double.MAX_VALUE &&
                        dis_to_source[j] > dis_to_source[i] + G.get(i).get(j)) {
                    return true;
                }
            }
        }
        return false;
    }
    // @exclude

    public static void main(String[] args) {
        Random r = new Random();
        int n, m;
        if (args.length == 1) {
            n = Integer.parseInt(args[0]);
            m = r.nextInt(n * (n - 1) / 2) + 1;
        } else if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
        } else {
            n = r.nextInt(100) + 1;
            m = r.nextInt(n * (n - 1) / 2) + 1;
        }
        ArrayList<ArrayList<Double>> G = new ArrayList<ArrayList<Double>>();
        for(int i = 0; i < n; i++) {
            ArrayList<Double> newList = new ArrayList<Double>();
            for(int j = 0; j < n; j++) {
                newList.add(0.0);
            }
            G.add(newList);
        }
        // Assume the input is a complete graph.
        for (int i = 0; i < G.size(); ++i) {
            G.get(i).set(i, 1.0);
            for (int j = i + 1; j < G.size(); ++j) {
                G.get(i).set(j, r.nextDouble());
                G.get(j).set(i, 1.0 / G.get(i).get(j));
            }
        }
        boolean res = is_Arbitrage_exist(G);
        System.out.println(res);

        ArrayList<ArrayList<Double>> g = new ArrayList<ArrayList<Double>>();
        for(int i = 0; i < 3; i++) {
            ArrayList<Double> newList = new ArrayList<Double>();
            for(int j = 0; j < 3; j++) {
                newList.add(0.0);
            }
            g.add(newList);
        }
        g.get(0).set(1, 2.0);
        g.get(1).set(0, 0.5);
        g.get(0).set(2, 1.0);
        g.get(2).set(0, 1.0);
        g.get(1).set(2, 4.0);
        g.get(2).set(1, 0.25);
        res = is_Arbitrage_exist(g);
        assert(res == true);
        System.out.println(res);
    }
}
