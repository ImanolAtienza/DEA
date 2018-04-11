package coins2;

import java.util.ArrayList;


public class SkienaCoinsChange {
	static final int[] DENOMINATIONS = {7,4,1,2};
	static int numSol = 1;
	static boolean flag = false;
	
	public static void main ( String[] args ){
		int change = 13;
		backtrack(new ArrayList<Integer>(), 0, change);

	}
	
	private static void backtrack (ArrayList<Integer> a, int k, int input) {
		int c[] = new int[8];
		if (is_a_solution(a, k, input)) {
			process_solution(a, k, input);
		} else {
			int ncandidate = construct_candidate(a, k, input, c);
			int i = 0;
		
				if (input >= DENOMINATIONS[k]) {
					a.add(DENOMINATIONS[k]);
					backtrack(a, k, input - DENOMINATIONS[k]);
					a.remove(a.size() - 1);
				}
				if (k + 1 < DENOMINATIONS.length)
					backtrack(a, k+1, input);
		
		}
	}
	
	private static int construct_candidate(ArrayList<Integer> a, int k, int input, int[] c) {
		int candidate = 0;

		for (int i = k; i < DENOMINATIONS.length; i++) {
			c[candidate] = DENOMINATIONS[i];
			candidate++;
		}
		
		return candidate;
	}
	
	private static boolean is_a_solution(ArrayList<Integer> a, int k, int input) {
		return (input == 0);
	}
	
	private static void process_solution(ArrayList<Integer> a, int k, int input) {
		System.out.println("end process. Number of solution: " + numSol);
		System.out.println(a);
		numSol++;
	}
}