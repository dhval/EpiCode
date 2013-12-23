package com.epi;
import static com.epi.utils.Utils.find;
import static com.epi.utils.Utils.shuffle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class StableAssignment {
	// @include
	static Pair<Integer, Integer>[] find_stable_assignment(
			int[][] professor_preference, int[][] student_preference) {

		// stores currently free students.
		Queue<Integer> free_student = new LinkedList<Integer>();
		for (int i = 0; i < student_preference.length; ++i) {
			free_student.add(i);
		}

		// Records the professors that each student have asked.
		int[] student_pref_idx = new int[student_preference.length];
		Arrays.fill(student_pref_idx, 0);
		
		// Records the current student choice for each professor.
		int[] professor_choice = new int[professor_preference.length];
		Arrays.fill(professor_choice, -1);

		while (!free_student.isEmpty()) {
			int i = free_student.element(); // free student.
			int j = student_preference[i][student_pref_idx[i]]; // target professor.
			if (professor_choice[j] == -1) { // this professor is free.
				professor_choice[j] = i;
				free_student.remove();
			} else { // this professor has student now.
				int original_pref = find(professor_preference[j], professor_choice[j]);
				int new_pref = find(professor_preference[j], i);
				if (new_pref < original_pref) { // this professor prefers the new one.
					free_student.add(professor_choice[j]);
					professor_choice[j] = i;
					free_student.remove();
				}
			}
			++student_pref_idx[i];
		}

		Pair<Integer, Integer>[] match_result = new Pair[professor_choice.length];
		for (int j = 0; j < professor_choice.length; ++j) {
			match_result[j] = new Pair<Integer, Integer>(professor_choice[j], j);
		}
		return match_result;
	}
	// @exclude

	static void check_ans(int[][] professor_preference,
			int[][] student_preference, Pair<Integer, Integer>[] match_result) {
	  
		assert match_result.length == professor_preference.length;
		
		boolean[] professor = new boolean[professor_preference.length], 
				student = new boolean[student_preference.length];
		
		for (Pair<Integer, Integer> p : match_result) {
			student[p.getFirst()] = true;
			professor[p.getSecond()] = true;
		}
		for (boolean p : professor) {
			assert p;
		}
		for (boolean s : student) {
			assert s;
		}

		for (int i = 0; i < match_result.length; ++i) {
			for (int j = i + 1; j < match_result.length; ++j) {
				int s0 = match_result[i].getFirst(), a0 = match_result[i].getSecond();
				int s1 = match_result[j].getFirst(), a1 = match_result[j].getSecond();
				int a0_in_s0_order = find(student_preference[s0], a0);
				int a1_in_s0_order = find(student_preference[s0], a1);
				int s0_in_a1_order = find(professor_preference[a1], s0);
				int s1_in_a1_order = find(professor_preference[a1], s1);
				assert a0_in_s0_order < a1_in_s0_order
						|| s1_in_a1_order < s0_in_a1_order;
	    }
	  }
	}

	public static void main(String[] args) {
		Random gen = new Random();
		for (int times = 0; times < 1000; ++times) {
			int n;
			if (args.length == 1) {
				n = Integer.valueOf(args[0]);
			} else {
				n = gen.nextInt(300) + 1;
			}
			int[][] professor_preference = new int[n][n], student_preference = new int[n][n];
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					professor_preference[i][j] = j;
					student_preference[i][j] = j;
				}
				shuffle(professor_preference[i]);
				shuffle(student_preference[i]);
			}

		    /*
			for (int i = 0; i < n; ++i) {
				System.out.println("professor " + i);
				for (int j = 0; j < n; ++j) {
					System.out.println(professor_preference[i][j] + " ");
				}
				System.out.println();
			}
			for (int i = 0; i < n; ++i) {
				System.out.println("student " + i);
				for (int j = 0; j < n; ++j) {
					System.out.println(student_preference[i][j] + " ");
				}
				System.out.println();
			}
		    */
		    Pair<Integer,Integer>[] res =
		        find_stable_assignment(professor_preference, student_preference);
		    
		    /*
		    for (int i = 0; i < res.size(); ++i) {
		    	System.out.println(res.get(i).getFirst() + ", " + res.get(i).getSecond());
		    }
		    */
		    check_ans(professor_preference, student_preference, res);
		  }
	}
}
