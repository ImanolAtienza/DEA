package dsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class People {
	
	@Override
	public String toString() {
		return "People [listPeople=" + listPeople + "]";
	}

	private ArrayList<Person> listPeople;
	private int numSol = 0;
	
	public void addPerson(String idperson, String name, String lastname, Date date,
			char gender, String birthplace, String home, String studiedat,
			String workplaces, String films, String groupcode) {
		Person p = new Person(idperson,name,lastname,date,gender,birthplace,home,studiedat,workplaces,films,groupcode);
		listPeople.add(p);
	}
	
	public People() {
		listPeople = new ArrayList<>();
	}
	
	public void printPeople() {
		for (Person p : listPeople) {
			System.out.println(p.toString());
		}
	}
	
	//public void printOutPeople(){ se llamará writePeople
	
	public void writePeople(){
		File wrp = new File("G6115.dsa");
		try {
			PrintWriter sc = new PrintWriter(wrp);
			for(Person p : listPeople) {
				sc.write(p.toString());
				sc.println();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void uploadPeople(String file) throws ParseException{
		File wrp = new File(file);
		Person p;
		try {
			Scanner sc = new Scanner(wrp);
			String line;
			String [] tokens;
			String delims = ",";
			sc.nextLine();
			while (sc.hasNext())
			{
				line = sc.nextLine();
				tokens = line.split(delims);
				Date d = new SimpleDateFormat("dd-MM-yy").parse(tokens[3]);
				p = new Person(tokens[0], tokens[1], tokens[2], d, tokens[4].charAt(0), 
						tokens[5],tokens[6],tokens[7],tokens[8],tokens[9],tokens[10]);
				listPeople.add(p);
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void uploadRelationship(String s) {
		File wrp = new File(s);
		Person p;
		try {
			Scanner sc = new Scanner(wrp);
			String line;
			String [] tokens;
			String delims = ",";
			sc.nextLine();
			while (sc.hasNext())
			{
				line = sc.nextLine();
				tokens = line.split(delims);
				Person per = seekPersonId(tokens[0]);
				if (per != null)
					per.getFriends().add(tokens[1]);
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Person seekPerson(String n) {
		for (Person p: listPeople) {
			if (n.compareTo(p.getName()) == 0) {
				return p;
			}
		}
		return null;
	}
	
	private Person seekPersonId(String n) {
		for (Person p: listPeople) {
			if (n.compareTo(p.getIdperson()) == 0) {
				return p;
			}
		}
		return null;
	}	
	
	public void searchPeopleCity(String city) {
		
		for(Person p : listPeople) {
			if (p.birthplace.equals(city)) {
				System.out.println(p.toString());
			}
		}
	}

	public void searchPeopleData(java.util.Date d1da, java.util.Date d2da) {
		for(Person p : listPeople) {
			if(p.date.after(d1da) && p.date.before(d2da)) {
				System.out.println(p.birthplace + " " + p.lastname + " " + p.name);
			}			
		}
	}
	
	public void birthplaceHometown() {
		ArrayList<String>home = uploadRess();
		for (int i = 0; i < home.size(); i++) {
			System.out.println("The following people were born in " + home.get(i));
			for(Person p : listPeople) {
				if (home.get(i).compareTo(p.birthplace) == 0)
					System.out.println(p.name + " " + p.lastname + " " + p.birthplace + " " + p.studiedat);
			}
		}
	}

	private ArrayList<String> uploadRess() {
		File wrp = new File("residential.txt");
		ArrayList<String> a = new ArrayList<>();
 		int cont = 0;
		try {
			Scanner sc = new Scanner(wrp);
			String line;
			sc.nextLine();
			while (sc.hasNext())
			{
				line = sc.nextLine();
				a.add(line);
				cont++;
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return a;
	}
	
	public void personFriends(String n) {
		Person p = seekPerson(n);
		for (String friends: p.getFriends()) 
			System.out.println(n + " is friend of " + friends);
	}

	public void cliquesOfFriends() {
		backtrack(new ArrayList<Person>(), 0, listPeople.size());
	}
	
	private void backtrack(ArrayList<Person> a, int k, int input) {
		int c[] = new int[listPeople.size()];
		if (is_a_solution(a, k, input)) {
			process_solution(a, k, input);
		} else {
			int ncandidate = construct_candidates(a, k, input, c);
			for (int i = 0; i < ncandidate; i++) {
				a.add(k, listPeople.get(c[i]));
				backtrack(a, k+1, input);
				a.remove(k);
			}
		}
	}

	private boolean is_a_solution(ArrayList<Person> a, int k, int input) {
		return k >= 4;
	}

	private void process_solution(ArrayList<Person> a, int k, int input) {
		numSol++;
		System.out.println("\nThe number of solution is " + numSol + " and the clique is: ");
		for (Person p : a) {
			System.out.print(p.getName() + " ");
		}
		System.out.println("\n");
	}

	private int construct_candidates(ArrayList<Person> a, int k, int input, int[] c) {
		int candidate = 0;
		Person p;
		boolean correct = true;
		for (int i = 0; i < listPeople.size(); i++) {
			p = listPeople.get(i);
			for(Person pEach : a) {
				if (!p.equals(pEach)) {
					if (!p.getFriends().contains(pEach.getIdperson())) 
						correct = false;
					//if (!pEach.getFriends().contains(p.getIdperson())) 
						//correct = false;
				} else
					correct = false;
			}
			
			if (correct) {
				c[candidate] = i;
				candidate++;
			}
			
			correct = true;
		}
		
		return candidate;
	}
}
