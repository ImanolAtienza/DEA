package dsa;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
import java.io.PrintWriter;

// ReadIntsFindZeros first reads a file of integers and then finds those integers that are equal to 0 in the file 
public class Sum2 {
	
	public static int count(int[] a, int Nread)
	{ // Count elements equal to 0.
		System.out.println(Nread);
		int N = Nread; 
		int cnt = 0;
		for (int i = 0; i < N; i++) 
			for (int j = i+1; j < N; j++)
				if ((a[i] + a[j]) == 0){
//						System.out.println(i);
						cnt++;};
		return cnt;
	}
	
	public static void main(String[] args) 
	{
		// first read the integers into an array
		// then call the method count
		// finally print out the results (to console or to a file)
		
		int Nints = 16001; //need to state the size of the array. Set a maximum but use only the actual elements filled in
		int[] theints = new int[Nints]; // Space allocation for the integers read 
		int i = 0; // counter for the number of integers in the file
		String mypath = "16KintsV1.txt";
		try{
			File myfilename = new File( mypath );
			Scanner input2program = new Scanner (myfilename);
			String stringint = "";
			while ( input2program.hasNext() ){		
				stringint = input2program.nextLine();
				// process the string
				theints[i] = Integer.parseInt (stringint);
				i = i+1;
			}
			System.out.println(i);
			input2program.close();
		}
		catch ( FileNotFoundException e ) {
				 e.printStackTrace();
		}
		
		//measure time to process the file 
		Stopwatch mytimer = new Stopwatch();
		//process 
		int totalcount = 0;	
		totalcount = count(theints, i-1); // i is the actual number of integers read.
		double totaltime = mytimer.elapsedTime();
		System.out.println(totalcount);
		System.out.println(totaltime);
		
		// write out to a textfile
		String write2file = "count2Kints-2sum1.txt";
		File wrname = new File (write2file);
		try {
			PrintWriter wrfile = new PrintWriter (wrname);
			wrfile.println(mypath);
			wrfile.println(totalcount);
			wrfile.println(totaltime);
			wrfile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	}
}
