package com.epi;

import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class RNP {
    // @include
    public static int eval(String s) {
        LinkedList<Integer> eval_stack = new LinkedList<Integer>();
        String[] symbols = s.split(",");
        for(String symbol : symbols) {
            if (symbol.length() == 1 && "+-*/".contains(symbol)) {
                int y = eval_stack.pop();
                int x = eval_stack.pop();
                switch (symbol.charAt(0)) {
                    case '+':
                        eval_stack.push(x + y);
                        break;
                    case '-':
                        eval_stack.push(x - y);
                        break;
                    case '*':
                        eval_stack.push(x * y);
                        break;
                    case '/':
                        eval_stack.push(x / y);
                        break;
                }
            } else {  // number.
                eval_stack.push(Integer.parseInt(symbol));
            }
        }
        return eval_stack.pop();
    }
    // @exclude

    public static void main(String[] args) {
        assert(0 == eval("2,-10,/"));
        assert(-5 == eval("-10,2,/"));
        assert(5 == eval("-10,-2,/"));
        assert(-5 == eval("5,10,-"));
        assert(6 == eval("-10,-16,-"));
        assert(12 == eval("10,2,+"));
        assert(15 == eval("1,2,+,3,4,*,+"));
        assert(42 == eval("1,2,3,4,5,+,*,+,+,3,4,*,+"));
    }
}
