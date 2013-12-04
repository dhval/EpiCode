package com.drx.epi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class ShortestUniquePrefix {
    private static String rand_string(int len) {
        Random r = new Random();
        StringBuilder ret = new StringBuilder(len);
        while(len-- > 0) {
            ret.append((char)(r.nextInt(26) + 'a'));
        }
        return ret.toString();
    }

    // @include
    public static class Trie {
        private static class TrieNode {
            private boolean isString = false;
            private HashMap<Character, TrieNode> leaves = new HashMap<Character, TrieNode>();

            public boolean getIsString() {
                return isString;
            }

            public void setIsString(boolean string) {
                isString = string;
            }

            public HashMap<Character, TrieNode> getLeaves() {
                return leaves;
            }
        }

        private TrieNode root_ = new TrieNode();

        public boolean insert(String s) {
            TrieNode p = root_;
            for(char c : s.toCharArray()) {
                if(!p.getLeaves().containsKey(c)) {
                    p.getLeaves().put(c, new TrieNode());
                }
                p = p.getLeaves().get(c);
            }

            // s already existed in this trie.
            if(p.getIsString()) {
                return false;
            } else {                  // p.getIsString() == false
                p.setIsString(true);  // inserts s into this trie.
                return true;
            }
        }

        public String GetShortestUniquePrefix(String s) {
            TrieNode p = root_;
            StringBuilder prefix = new StringBuilder();
            for(char c : s.toCharArray()) {
                prefix.append(c);
                if(!p.getLeaves().containsKey(c)) {
                    return prefix.toString();
                }
                p = p.getLeaves().get(c);
            }
            return "";
        }
    }

    public static String find_shortest_prefix(String s, HashSet<String> D) {
        // Build a trie according to given dictionary D.
        Trie T = new Trie();
        for(String word : D) {
            T.insert(word);
        }
        return T.GetShortestUniquePrefix(s);
    }
    // @exclude

    private static String check_ans(String s, HashSet<String> D) {
        int len = 0;
        for(String word : D) {
            int i;
            for(i = 0; i < s.length() && i < word.length(); ++i) {
                if(s.charAt(i) != word.charAt(i)) {
                    break;
                }
            }
            if(i > len) {
                len = i;
            }
        }
        if(len == s.length()) {
            return "";
        } else {
            return s.substring(0, len + 1);
        }
    }

    public static void main(String[] args) {
        Random r = new Random();
        for (int times = 0; times < 100; ++times) {
            HashSet<String> D = new HashSet<String>();
            String s;
            if(args.length == 1) {
                s = args[0];
            } else {
                s = rand_string(r.nextInt(10) + 1);
            }
            int n = r.nextInt(10000) + 1;
            while(n-- > 0) {
                D.add(rand_string(r.nextInt(10) + 1));
            }
            System.out.println(s + " shortest prefix = " + find_shortest_prefix(s, D));
            assert(find_shortest_prefix(s, D).equals(check_ans(s, D)));
        }
    }
}
