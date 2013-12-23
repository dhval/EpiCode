package com.epi;

import static com.epi.utils.Utils.nullEqual;

/**
 * @author translated from c++ by Blazheev Alexander
 */
public class Pair<A, B> {
    public A first;
    public B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
    	if (!(obj instanceof Pair)) {
    		return false;
    	}
    	
    	final Pair<A, B> that = (Pair<A, B>) obj;
    	return nullEqual(first, that.getFirst()) && nullEqual(second, that.getSecond());
    }
    
	public String toString() {
        return first + " " + second;
    }
    
    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }
    
}
