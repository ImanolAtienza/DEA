package coins2;

import java.util.ArrayList;


public class CoinsChange {
	static final int[] DENOMINATIONS = {7,4,1,2};
	static int numSol = 1;
	
	public static void change(int amount) {
		change(amount, new ArrayList<Integer>(), 0);	}

	private static void change(int remaining, ArrayList<Integer> coins, int pos) {
		if (remaining == 0) {
			System.out.println("end process. Number of solution: " + numSol);
			System.out.println(coins);
			numSol++;
		} else {
			if (remaining >= DENOMINATIONS[pos]) {
				coins.add(DENOMINATIONS[pos]);
				change(remaining - DENOMINATIONS[pos], coins, pos);
				coins.remove(coins.size() - 1);
			}
			if (pos + 1 < DENOMINATIONS.length) {
				change(remaining, coins, pos + 1);
			}	
		}
	}

	public static void main ( String[] args ){
		int change = 13;
		change(change);

	}
}