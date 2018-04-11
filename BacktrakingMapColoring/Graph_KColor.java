package mapcoloring;

public class Graph_KColor
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

	static void PrintSolution(int[] C, int n)
	{ System.out.println("solution found:");
	for(int i = 1; i <= n; i++)
		System.out.print(" " + C[i]);
	System.out.println();
	}
	static boolean ValidColor(int[] C, int v)
	{ // returns true if current partial coloring up to vertex v is OK
		for(int i=1; i < v; i++)
			if (C[i] == C[v]) // Vertices i and v have same color
				// Use Adjacency Matrix (AM) to check for presence of (i,v) edge
				if (AM[i][v]== 1) return false;
		return true;
	}

	static void GraphColor(int[] C, int v, int n)
	{ for(int color = 1; color <= MaxColor; color++) // Move across a level
	{ // Uncomment next line if at most one solution is desired
//		if (sf_flag) return;
		C[v] = color; // Assign color to verex v
		if (ValidColor(C,v)) // Check if partial solution C[1..v] is valid
		{ // check if solution is complete
			if (v == n) { sf_flag = true; PrintSolution(C,n); }
			else GraphColor(C,v+1,n);// Move down
		}
	}
	// Backtrack here but it is taken care of by recursion
	}

	static void GraphColor_IT(int[] C, int n)
	{ int v = 1;
	C[v] = 1;
	while (v >= 1)
	{ while (C[v] < MaxColor)
	{ // Uncomment next line if at most one solution is desired
//		if (sf_flag) return;
		C[v] = C[v] + 1; // Move across a level (left-to-right)
		if (ValidColor(C, v)) // Check if partial solution is valid
		{ // Check if solution is complete
			if (v == n) { sf_flag = true; PrintSolution(C,n);}
			else { v = v+1; C[v] = 0; } // Advance to next slot and initialize
		}
	}
	// Backtrack: move up the search tree
	v = v-1;
	}
	}

	public static void main(String[] args)
	{ // Supply code to setup adjacency matrix AM and initialize n (number of vertices)

		C = new int[n+1];
		sf_flag = false; // solution-found flag
		// Call either GraphColor(1,n) or GraphColor_IT(n)
		// GraphColor_IT(C,n);
		GraphColor(C,1,n); // Color vertices starting with vertex 1
		if (sf_flag == false) System.out.println("no solution");
	}
}