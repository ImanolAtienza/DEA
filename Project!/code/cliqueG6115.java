package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class cliqueG6115 {

	String [] friends = {"Paco11", "Suarez32", "Rodolfo17", "Manuela99"};
	String filef = "friendsG6115.dsa";
	
	public void writeFileFriends(){
		
		File wrf = new File(filef);
		try {
			PrintWriter sc = new PrintWriter(wrf);
			sc.write("friend1, friend2");
			sc.println();
			for(int i=0; i<=friends.length -1; i++) {
				for(int j=i+1; j<=friends.length -1; j++){
					sc.write(friends[i] + ", " + friends[j]);
					sc.println();
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}	
}
