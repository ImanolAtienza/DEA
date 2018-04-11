
public class Sudoku {
	

	
	static int [][] sudoku =
		{
		{0, 0, 0, 0, 0, 0, 9, 6, 7},
		{0, 8, 0, 0, 0, 0, 0, 0, 5}, 
		{6, 0, 3, 0, 0, 2, 0, 0, 0},
		{0, 4, 0, 0, 0, 0, 0, 8, 0},
		{0, 0, 0, 2, 7, 4, 5, 0, 0},
		{0, 7, 0, 5, 0, 0, 0, 0, 0},
		{5, 6, 0, 0, 0, 3, 8, 7, 2},
		{0, 9, 0, 0, 2, 0, 3, 5, 4},
		{7, 3, 0, 8, 0, 0, 6, 0, 0}, 
		}; 
	
	static int numSol = 1;
	
	public static void main(String[] args) {
		backtrack(sudoku, 0, -1, 3);
	}

	private static void backtrack(int[][] a, int f, int c, int input) {
		int[] sol = new int[9];
		if (is_a_solution(a, f, c, input)) {
			process_solution(a, f, input);
		} else {
			c++;
			if (c == input) {
				c = 0;
				f++;
			}
			if (a[f][c] == 0) {
				int ncandidates = construct_candidates(a, f, c, input, sol);
				for (int i = 0; i < ncandidates; i++) {
					a[f][c] = sol[i];
					backtrack(a, f, c, input);
				}
			} else
				backtrack(a, f, c, input);
		}
	}

	private static int construct_candidates(int[][] a, int f, int c, int input, int[] sol) {
		int candidate = 0;
		int num;
		int i;
		boolean correct;
		
		for (num = 1; num <= 9; num++) {
			i = 0;
			correct = true;
			while (i < sudoku.length && correct) {
				if (sudoku[f][i] == num)
					correct = false;
				i++;
			}
		
			i = 0;
			while (i < sudoku.length && correct) {
				if (sudoku[i][c] == num)
					correct = false;
				i++;
			}
			
			int j = 0;
			int p = (((f/9)/3)*3*9)+(((f%9)/3)*3);     // p is the first position of the 3x3 sub-matrix
			int b = (((c/9)/3)*3*9)+(((c%9)/3)*3);
			while(correct && j<9){     // look if this number is already in the corresponding 3x3 sub-matrix
				if(i==sudoku[p/9][b%9]){
					correct=false;
				}
				if(p%3==2){
					p+=7;
				}else{
					p++;
				}
				j++;
			}
			
			if (correct) {
				sol[candidate] = num;
				candidate++;
			}
		}
			
		return candidate;
	}

		

	private static void process_solution(int[][] a, int k, int input) {
		System.out.println("Number of solution: " + numSol + "\n");
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++)
				System.out.print(a[i][j] + " ");
			
			System.out.println();
		}
		numSol++;
	}

	private static boolean is_a_solution(int[][] a, int f, int c, int input) {
		return (f == input - 1 && c == input - 1);
	}
	
	
}
