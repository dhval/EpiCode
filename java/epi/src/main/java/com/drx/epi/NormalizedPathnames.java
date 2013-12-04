package com.drx.epi;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class NormalizedPathnames {
    // @include
    public static String normalized_path_names(String path) {
        LinkedList<String> s = new LinkedList<String>(); // Use LinkedList as a stack.
        // Special case: starts with "/", which is an absolute path.
        if (path.startsWith("/")) {
            s.push("/");
        }
        for(String token : path.split("/")) {
            if(token.equals("..")) {
                if(s.isEmpty() || s.peek().equals("..")) {
                    s.push(token);
                } else {
                    if(s.peek().equals("/")) {
                        throw new RuntimeException("Path error");
                    }
                    s.pop();
                }
            } else if(!token.equals(".") && !token.isEmpty()) { // name.
                for(char c : token.toCharArray()) {
                    if(c != '.' && !Character.isDigit(c) && !Character.isLetter(c)) {
                        throw new RuntimeException("Invalid directory name");
                    }
                }
                s.push(token);
            }
        }
        StringBuilder normalized_path = new StringBuilder();
        if(!s.isEmpty()) {
            Iterator<String> it = s.descendingIterator();
            String prev = it.next();
            normalized_path.append(prev);
            while(it.hasNext()) {
                if(!prev.equals("/")) {
                    normalized_path.append("/");
                }
                prev = it.next();
                normalized_path.append(prev);
            }
        }
        return normalized_path.toString();
    }
    // @exclude

    public static void main(String[] args) {
        assert(normalized_path_names("123/456").equals("123/456"));
        assert(normalized_path_names("/123/456").equals("/123/456"));
        assert(normalized_path_names("usr/lib/../bin/gcc").equals("usr/bin/gcc"));
        assert(normalized_path_names("./../").equals(".."));
        assert(normalized_path_names("../../local").equals("../../local"));
        assert(normalized_path_names("./.././../local").equals("../../local"));
        try {
            normalized_path_names("/..");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            normalized_path_names("/cpp_name/bin/");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assert(normalized_path_names("scripts//./../scripts/awkscripts/././")
                .equals("scripts/awkscripts"));
        if (args.length == 1) {
            System.out.println(normalized_path_names(args[0]));
        }
    }
}
