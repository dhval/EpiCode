package com.drx.epi.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Utils {
	
	private static Random random;
	
	public static int[][] copy(int[][] m) {
		int[][] copy = new int[m.length][];
		
		for (int i = 0; i < m.length; i++) {
			copy[i] = Arrays.copyOf(m[i], m[i].length);
		}
		
		return copy;
	}
	public static void copy(int[] a1, int from, int to, int[] a2, int start) {
		for (int i = 0; i < to - from; i++) {
			if (start + i < a2.length) {
				a2[start + i] = a1[from + i];
			}
		}
	}
	
	public static void shuffle(int[] array) {
		if (array == null || array.length == 0) {
			return;
		}

		Random rnd = random;
		if (rnd == null) {
			random = rnd = new Random();
		}

		for (int i = array.length; i > 1; i--) {
			int newIndex = rnd.nextInt(i);
			int tmp = array[i - 1];
			array[i - 1] = array[newIndex];
			array[newIndex] = tmp;
		}

	}
	
	public static void fill(int[] array, int val) {
		if (array == null || array.length == 0) {
			return;
		}
		
		for (int i = 0; i < array.length; i++) {
			array[i] = val;
		}
	}
	public static void reverse(char[] array, int start, int stopIndex) {
		if (array == null || array.length == 0 || start >= stopIndex) {
			return;
		}
		
		int last = stopIndex - 1;
		for (int i = start; i <= start + (last - start) / 2; i++) {
			char tmp = array[i];
			array[i] = array[last - i + start];
			array[last - i + start] = tmp;
		}
	}
	
	public static void reverse(int[] array, int start, int stopIndex) {
		if (array == null || array.length == 0 || start >= stopIndex) {
			return;
		}
		
		int last = stopIndex - 1;
		for (int i = start; i <= start + (last - start) / 2; i++) {
			int tmp = array[i];
			array[i] = array[last - i + start];
			array[last - i + start] = tmp;
		}
	}

	public static int find(char[] array, char c, int start) {
		for (int i = start; i < array.length; i++) {
			if (array[i] == c) {
				return i;
			}
		}
 
		return -1;
	}
	
	public static void swap(int[] array, int index1, int index2) {
		int element1_copy = array[index1];
		array[index1] = array[index2];
		array[index2] = element1_copy;
	}
	
	public static void swap(List<Integer> list, Integer index1, Integer index2) {
		int element1_copy = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, element1_copy);
	}
	
	/**
	 * Adds <code>num_of_elements</code> of elements to the list
	 * <code>list</code>, each being a copy of <code>value</code>.
	 * 
	 * @param list
	 * @param num_of_elements
	 * @param value
	 */
	public static void fill(List<Integer> list, int num_of_elements, int value) {
		for (int i = 1; i <= num_of_elements; ++i) {
			list.add(value);
		}
	}

	/**
	 * Fills the range [from, to) with sequentially increasing values, starting
	 * with value and repetitively evaluating ++value.
	 * 
	 * @param array
	 * @param from
	 * @param to
	 * @param value
	 */
	public static void iota(int[] array, int from, int to, int value) {
		for (int i = from; i < to; ++i) {
			array[i] = value++;
		}
	}
	
	/**
	 * Fills the list with sequentially increasing values, starting with value
	 * and repetitively evaluating ++value.
	 * 
	 * @param list
	 * @param num_of_elements
	 * @param value
	 */
	public static void iota(List<Integer> list, int num_of_elements, int value) {
		for (int i = 1; i <= num_of_elements; ++i) {
			list.add(value++);
		}
	}
	
	public static void simplePrint(boolean[] array) {
		if (array == null || array.length == 0) {
			return;
		}
		
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i < array.length - 1) {
				System.out.print(" ");
			}
		}
	}
	
	public static void simplePrint(int[] array) {
		if (array == null || array.length == 0) {
			return;
		}
		
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i < array.length - 1) {
				System.out.print(" ");
			}
		}
	}
	
	public static <T> void simplePrint(Collection<T> collection) {
		if (collection == null || collection.isEmpty()) {
			return;
		}
		
		for (Iterator<T> iterator = collection.iterator(); iterator.hasNext();) {
			T t = (T) iterator.next();
			System.out.print(t);
			if (iterator.hasNext()) {
				System.out.print(" ");
			}
			
		}
	}
	
	public static <T> boolean equal(List<T> list1, List<T> list2) {
		if (list1.size() != list2.size()) {
			return false;
		}
		
		for (int i = 0; i < list1.size(); i++) {
			if (!list1.get(i).equals(list2.get(i))) {
				return false;
			}
		}
		
		return true;
	}
	
}
