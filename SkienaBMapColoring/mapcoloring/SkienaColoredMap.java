package mapcoloring;

public class SkienaColoredMap
{// Uses Backtracking to solve graph K-coloring problem
	// In this program code, K is denoted as MaxColor
	//	static int MaxColor = 3; // Solve 3-coloring problem
	static int MaxColor = 4; // Solve m-coloring problem
	static int n = 9; // number of vertices  //9 European countries
	//	static int[][] AM = new int[n+1][n+1]; // Adjacency matrix for undirected graph
	static int [][] AM = 
		{
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, // empty for indices clarity
		{0, 0, 1, 1, 1, 0, 0, 0, 0, 0 }, //Poland 1
		{0, 1, 0, 1, 0, 1, 1, 0, 0, 0 }, //Germany 2
		{0, 1, 1, 0, 1, 0, 1, 0, 0, 0 }, //Czech Republic 3
		{0, 1, 0, 1, 0, 0, 1, 1, 0, 0 }, //Slovakia	4 
		{0, 0, 1, 0, 0, 0, 1, 0, 1, 0 }, //Switzerland 5
		{0, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, //Austria 6
		{0, 0, 0, 0, 1, 0, 1, 0, 0, 1 }, //Hungary 7
		{0, 0, 0, 0, 0, 1, 1, 0, 0, 1 }, //Italy 8
		{0, 0, 0, 0, 0, 0, 1, 1, 1, 0 }, //Solvenia 9
		};

	static boolean sf_flag;
	static int[] C; // Solution vector

	public static void main(String[] args) { // Supply code to setup adjacency matrix AM and initialize n (number of vertices)
		C = new int[n+1];
		// sf_flag = false; solution-found flag
		// Call either GraphColor(1,n) or GraphColor_IT(n)
		// GraphColor_IT(C,n);
		backtrack(C,0,n); // Color vertices starting with vertex 1
		/*if (sf_flag == false) 
			System.out.println("no solution"); */
	}	
	
	private static void backtrack(int a[], int k, int input) {
		int c[] = new int[MaxColor];
		if (is_a_solution(a, k, input)) {
			process_solution(a, k, input);
		} else {
			k++; // In this case is the indice of the city in the graph
			int ncandidates = construct_candidates(a, k, input, c); // a array solutions; k indice; input total size
			for (int i = 0; i < ncandidates; i++) {
				a[k] = c[i];
				backtrack(a, k, input);
			}	
		}
	}
	
	private static int construct_candidates(int[] a, int k, int input, int c[]) {
		boolean flag = true;
		int j, candidate = 0;
		
		for (int i = 1; i <= MaxColor; i++) {
			flag = true;
			j = 1;
			while (j < a.length && flag) {
				if (a[j] == i) {
					if (AM[j][k] == 1) {
						flag = false;
					}
				}
				j++;		
			}
			
			if (flag) {
				c[candidate] = i;
				candidate++;
			}
		}
				
		return candidate;
	}

	private static void process_solution(int[] a, int k, int n) {
		// Print the solution
		System.out.println("This is a posible solution:\n");
		for (int i = 1; i <= n; i++) {
			System.out.print(a[i] + " ");
			/*switch (a[i]) {
			case 1:
				System.out.print("\nRed");
				break;
			case 2:
				System.out.print("\nGreen");
				break;
			case 3:
				System.out.print("\nBlue");
				break;
			case 4:
				System.out.print("\nYellow");
				break;
			default:
				break;
			}*/
		}
		System.out.println();
	}

	private static boolean is_a_solution(int[] a, int k, int input) {
		// Must check the array solution if have all countries with a color
		return (k == input);
	}
	
	static boolean ValidColor(int[] C, int v) { 
		// returns true if current partial coloring up to vertex v is OK
		for(int i=1; i < v; i++)
			if (C[i] == C[v]) // Vertices i and v have same color
				// Use Adjacency Matrix (AM) to check for presence of (i,v) edge
				if (AM[i][v]== 1) 
					return false;
		
		return true;
	}

	static void GraphColor(int[] C, int v, int n) { 
		 // Move across a level
		for(int color = 1; color <= MaxColor; color++) { // Uncomment next line if at most one solution is desired
//		if (sf_flag) return;
			C[v] = color; // Assign color to verex v
			// Check if partial solution C[1..v] is valid
			if (ValidColor(C,v)) { // check if solution is complete
				if (v == n) { 
					sf_flag = true; 
					//PrintSolution(C,n); 
				} else 
					GraphColor(C,v+1,n);// Move down
			}
		}
	// Backtrack here but it is taken care of by recursion
	}
}