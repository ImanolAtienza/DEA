package dsa;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Postfix    
{
  /**
   * Reads and evaluates multiple postfix expressions.
   */
  public static void main (String[] args)
  {
    String expression;
    int result;
    
    // input file
    String path = "postfixexpressions.txt";
	File fname = new File( path );
	
	//output file
	String write2file = "postfixResults.txt";
	File wrname = new File (write2file);
	
	try{

		Scanner input2program = new Scanner (fname);
		PrintWriter wrfile = new PrintWriter (wrname);
        PostfixEvaluator evaluator = new PostfixEvaluator();
		
		while ( input2program.hasNext() ){		
			expression = input2program.nextLine();
			
	        result = evaluator.evaluate (expression);
	        
			wrfile.println(expression);
			wrfile.println("----------> "+ result);
		}
		
		input2program.close();
		wrfile.close();
	}
	catch ( FileNotFoundException e ) {
			 e.printStackTrace();
	}
    
    
    
    
    
    
    
  }
}