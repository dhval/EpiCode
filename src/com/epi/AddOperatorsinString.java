package com.drx.epi;

import java.util.*;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class AddOperatorsinString {
    private static String buildStringByList(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for(int e : list) {
            sb.append((char)e);
        }
        return sb.toString();
    }

    // @include
    public static void exp_synthesis(List<Integer> A, int k) {
        LinkedList<Character> oper_list = new LinkedList<Character>();
        LinkedList<Integer> operand_list = new LinkedList<Integer>();
        if (!exp_synthesis_helper(A, k, operand_list, oper_list, 0, 0)) {
            System.out.println("no answer");
        }
    }

    private static boolean exp_synthesis_helper(List<Integer> A,
                              int k,
                              LinkedList<Integer> operand_list,
                              LinkedList<Character> oper_list,
                              int cur,
                              int level) {
        cur = cur * 10 + A.get(level) - '0';
        if (level == A.size() - 1) {
            operand_list.addLast(cur);
            if (evaluate(operand_list, oper_list) == k) {
                Iterator<Integer> operand_it = operand_list.iterator();
                System.out.print(operand_it.next());
                for (char oper : oper_list) {
                    System.out.print(" " + oper + operand_it.next());
                }
                System.out.println(" = " + k);
                return true;
            }
            operand_list.removeLast();
        } else {
            // No operator.
            if (exp_synthesis_helper(A, k, operand_list, oper_list, cur, level + 1)) {
                return true;
            }

            // Add operator '+'.
            operand_list.addLast(cur);
            if (k - evaluate(operand_list, oper_list) <=
            Integer.parseInt(buildStringByList(A.subList(level + 1, A.size())))) {  // pruning.
                oper_list.add('+');
                if (exp_synthesis_helper(A, k, operand_list, oper_list, 0, level + 1)) {
                    return true;
                }
                oper_list.removeLast();  // revert.
            }
            operand_list.removeLast();  // revert.

            // Add operator '*'.
            operand_list.addLast(cur);
            oper_list.addLast('*');
            if (exp_synthesis_helper(A, k, operand_list, oper_list, 0, level + 1)) {
                return true;
            }
            operand_list.removeLast();
            oper_list.removeLast();  // revert.
        }
        return false;
    }

    private static int evaluate(LinkedList<Integer> operand_list, LinkedList<Character> oper_list) {
        operand_list = (LinkedList<Integer>) operand_list.clone();
        // Evaluate '*' first.
        ListIterator<Integer> operand_it = operand_list.listIterator();
        int cur = operand_it.next();
        for(char oper : oper_list) {
            if (oper == '*') {
                int product = cur;
                operand_it.remove();
                cur = operand_it.next();
                product *= cur;
                operand_it.set(product);
            } else {
                cur = operand_it.next();
            }
        }

        // Evaluate '+' second.
        int accumulate = 0;
        for(int o : operand_list) {
            accumulate += o;
        }
        return accumulate;
    }
    // @exclude

    private static String rand_string(int len) {
        Random r = new Random();
        StringBuilder ret = new StringBuilder();
        ret.append(r.nextInt(9) + 1);
        while (--len != 0) {
            ret.append(r.nextInt(9) + 1);
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        String s;
        int k;
        Random r = new Random();
        if (args.length == 2) {
            s = args[0];
            k = Integer.parseInt(args[1]);
        } else {
            s = rand_string(8);
            k = r.nextInt(1000);
        }
        ArrayList<Integer> A = new ArrayList<Integer>();
        for (char c : s.toCharArray()) {
            A.add((int)c);
        }
        System.out.println("s = " + s + ", k = " + k);
        exp_synthesis(A, k);
    }
}
