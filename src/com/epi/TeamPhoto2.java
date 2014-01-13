package com.drx.epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class TeamPhoto2 {
    private static class Player<HeightType extends Comparable<HeightType>> implements Comparable<Player<HeightType>>{
        public HeightType height;

        public Player(HeightType height) {
            this.height = height;
        }

        @Override
        public int compareTo(Player<HeightType> o) {
            return height.compareTo(o.height);
        }
    }

    private static class Team<HeightType extends Comparable<HeightType>>{
        private ArrayList<Player<HeightType>> members_;

        public Team(List<HeightType> height) {
            members_ = new ArrayList<Player<HeightType>>();
            for(HeightType h : height) {
                members_.add(new Player<HeightType>(h));
            }
        }

        public boolean lessThen(Team<HeightType> that) {
            List<Player<HeightType>> this_sorted = sortHeightMembers();
            List<Player<HeightType>> that_sorted = that.sortHeightMembers();
            for (int i = 0; i < this_sorted.size() && i < that_sorted.size(); ++i) {
                if (!(this_sorted.get(i).compareTo(that_sorted.get(i)) < 0)) {
                    return false;
                }
            }
            return true;
        }

        private List<Player<HeightType>> sortHeightMembers() {
            List<Player<HeightType>> sorted_members = (List<Player<HeightType>>) members_.clone();
            Collections.sort(sorted_members);
            return sorted_members;
        }
    }

    // @include
    public static class GraphVertex {
        public ArrayList<GraphVertex> edges = new ArrayList<GraphVertex>();
        public int max_distance = 1;
        public boolean visited = false;
    }

    public static int find_largest_number_teams(List<GraphVertex> G) {
        LinkedList<GraphVertex> vertex_order = build_topological_ordering(G);
        return find_longest_path(vertex_order);
    }

    private static LinkedList<GraphVertex> build_topological_ordering(List<GraphVertex> G) {
        LinkedList<GraphVertex> vertex_order = new LinkedList<GraphVertex>();
        for (GraphVertex g : G) {
            if (!g.visited) {
                DFS(g, vertex_order);
            }
        }
        return vertex_order;
    }

    private static int find_longest_path(LinkedList<GraphVertex> vertex_order) {
        int max_distance = 0;
        while (!vertex_order.isEmpty()) {
            GraphVertex u = vertex_order.peek();
            max_distance = Math.max(max_distance, u.max_distance);
            for (GraphVertex v : u.edges) {
                v.max_distance = Math.max(v.max_distance, u.max_distance + 1);
            }
            vertex_order.pop();
        }
        return max_distance;
    }

    private static void DFS(GraphVertex cur, LinkedList<GraphVertex> vertex_order) {
        cur.visited = true;
        for (GraphVertex next : cur.edges) {
            if (!next.visited) {
                DFS(next, vertex_order);
            }
        }
        vertex_order.push(cur);
    }
    // @exclude

    public static void main(String[] args) {
        ArrayList<GraphVertex> G = new ArrayList<GraphVertex>(3);
        for(int i = 0; i < 3; i++) {
            G.add(new GraphVertex());
        }
        G.get(0).edges.add(G.get(2));
        G.get(1).edges.add(G.get(2));
        assert(2 == find_largest_number_teams(G));
    }
}
