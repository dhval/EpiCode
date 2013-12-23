// Copyright (c) 2013 Elements of Programming Interviews. All rights reserved.
package com.epi;


// @include
class Array<ValueType> {
	private ValueType A_[];
	private int P_[], S_[], t_ = 0;

	public Array(Class<ValueType> clazz, int N) {
		A_ = (ValueType[]) java.lang.reflect.Array.newInstance(clazz, N);
		P_ = new int[N];
		S_ = new int[N];
	}

	public boolean read(int i, Wrapper<ValueType> v) {
		if (isValid(i)) {
			v.object = A_[i];
			return true;
		}
		return false;
	}

	public void write(int i, ValueType v) {
		if (!isValid(i)) {
			S_[t_] = i;
			P_[i] = t_++;
		}
		A_[i] = v;
	}

	private boolean isValid(int i) {
		return (0 <= P_[i] && P_[i] < t_ && S_[P_[i]] == i);
	}
}

class Wrapper<T> {
	T object;
	
	public Wrapper(T object) {
		this.object = object;
	}
}

// @exclude
	
public class LazyInit {

	public static void main(String[] args) {
		Array<Integer> A = new Array<Integer>(Integer.class, 11);
		Wrapper<Integer> x = new Wrapper<Integer>(0);

		assert (A.read(0, x) == false);
		assert (A.read(1, x) == false);

		A.write(1, 5);
		assert (A.read(1, x) == true && x.object == 5);
		assert (A.read(2, x) == false);

		A.write(2, 27);
		assert (A.read(2, x) == true && x.object == 27);
		assert (A.read(7, x) == false);

		A.write(7, -19);
		assert (A.read(7, x) == true && x.object == -19);
	}
}
