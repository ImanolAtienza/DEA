package dsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class People {
	
	private ArrayList<Person> listPeople;
	private ArrayList<Person> shortFriends = new ArrayList<>();
	private ArrayList<Person> largeFriends = new ArrayList<>();
	private int numSol;
	private boolean finished = false;
	
	@Override
	public String toString() {
		return "People [listPeople=" + listPeople + "]";
	}
	
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
		for (String friends: p.getFriends()) {
			System.out.println(n + " is friend of " + friends);
		}
	}
	
	public void listProfiles() {
		ArrayList<ArrayList<Person>> lista = new ArrayList<ArrayList<Person>>();
		for (Person p : listPeople) {
			boolean found = false;
			for (int i=0; i<lista.size(); i++) {
				if (lista.get(i).get(0).getFilms() == p.getFilms()) {
					found = true;
					lista.get(i).add(p);
				}
			}
			if (!found) {
				ArrayList<Person> pe = new ArrayList<Person>();
				pe.add(p);
				lista.add(pe);
			}
		}
		for (int i=0; i<lista.size(); i++) {
			System.out.println(lista.get(i).get(0).getFilms() + ": ");
			for (int j=0; j<lista.get(i).size(); j++) {
				System.out.println(lista.get(i).get(j).idperson);
			}
			System.out.println();
		}
	}
	

	
	public void searchSortestChain(String p1, String p2) {
		Person pIni = seekPerson(p1);
		ArrayList<Person> friends = new ArrayList<Person>();
		
		friends.add(pIni);
		backtrackShort(friends, 0, listPeople.size(), p1, p2);
		System.out.println("This is the shortest list: \n");
		for (Person p: shortFriends) {
			System.out.print(p.getName() + " ");
		}
		System.out.println();
		shortFriends.clear();
	}
	
	private void backtrackShort(ArrayList<Person> a, int k, int input, String p1, String p2) {
		Person[] c = new Person[input];
		if (is_short_solution(a, k, input, p1, p2))
			process_the_short(a, k, input);
		else {
			int ncandidates = construct_short_candidate(a, k, input, c, p1, p2);
			k++;
			for (int i = 0; i < ncandidates; i++) {
				a.add(k, c[i]);
				backtrackShort(a, k, input, p1, p2);
			}
			//a.remove(k);
		}
		
	}

	private int construct_short_candidate(ArrayList<Person> a, int k, int input, Person[] c, String p1, String p2) {
		int candidate = 0;
		boolean found = true;
		Person p = a.get(k);
		Person otherP = new Person();
		ArrayList<String> f = p.getFriends();
		
		if (!f.isEmpty()) {
			for (int i = 0; i < f.size(); i++) {
				if (f.get(i).compareTo(p1) == 0)
					found = false;
				else {
					otherP = seekPersonId(f.get(i));
					boolean flag = true;
					int j = 0;
					while (flag && j < a.size()) {
						if (a.get(j).getName().compareTo(otherP.getName()) == 0)
							flag = found = false;
						
						j++;
					}
				}
			
				if (found) {
					c[candidate] = otherP;
					candidate++;
				}
				found = true;
			}
		}
		
		return candidate;
	}

	private void process_the_short(ArrayList<Person> a, int k, int input) {
		if (!shortFriends.isEmpty()) {
			if (a.size() < shortFriends.size()) {
				shortFriends.clear();
				for (int i = 0; i < a.size(); i++)
					shortFriends.add(i, a.get(i));
			}
		} else {
			for (int i = 0; i < a.size(); i++)
				shortFriends.add(i, a.get(i));
		}
	}

	private boolean is_short_solution(ArrayList<Person> a, int k, int input, String p1, String p2) {
		return a.get(k).getName().compareTo(p2) == 0;
	}
	
	public void cliquesOfFriends() {
		numSol=0;
		backtrackClique(new ArrayList<Person>(), 0, listPeople.size());
	}
	
	private void backtrackClique(ArrayList<Person> a, int k, int input) {
		int c[] = new int[listPeople.size()];
		if (is_a_solutionClique(a, k, input)) {
			process_solutionClique(a, k, input);
		} else {
			int ncandidate = construct_candidatesClique(a, k, input, c);
			for (int i = 0; i < ncandidate; i++) {
				a.add(k, listPeople.get(c[i]));
				backtrackClique(a, k+1, input);
				a.remove(k);
			}
		}
	}

	private boolean is_a_solutionClique(ArrayList<Person> a, int k, int input) {
		return k >= 4;
	}
	
	private void process_solutionClique(ArrayList<Person> a, int k, int input) {
		numSol++;
		System.out.println("\nThe number of solution is " + numSol + " and the clique is: ");
		for (Person p : a) {
			System.out.print(p.getName() + " ");
		}
		System.out.println("\n");
	}

	private int construct_candidatesClique(ArrayList<Person> a, int k, int input, int[] c) {
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

	public void searchLargestChain(String pl1, String pl2) {
		Person pIni = seekPerson(pl1);
		ArrayList<Person> friends = new ArrayList<Person>();
		
		friends.add(pIni);
		backtrackLarger(friends, 0, listPeople.size(), pl1, pl2);
		System.out.println("This is the larger list: \n");
		for (Person p: largeFriends) {
			System.out.print(p.getName() + " ");
		}
		System.out.println();
		largeFriends.clear();
	}

	private void backtrackLarger(ArrayList<Person> a, int k, int input, String p1, String p2) {
		Person[] c = new Person[input];
		if (is_large_solution(a, k, input, p1, p2))
			process_the_large(a, k, input);
		else {
			int ncandidates = construct_large_candidate(a, k, input, c, p1, p2);
			k++;
			for (int i = 0; i < ncandidates; i++) {
				a.add(k, c[i]);
				backtrackLarger(a, k, input, p1, p2);
			}
			//a.remove(k);
		}
	}

	private int construct_large_candidate(ArrayList<Person> a, int k, int input, Person[] c, String p1, String p2) {
		int candidate = 0;
		boolean found = true;
		Person p = a.get(k);
		Person otherP = new Person();
		ArrayList<String> f = p.getFriends();
		
		if (!f.isEmpty()) {
			for (int i = 0; i < f.size(); i++) {
				if (f.get(i).compareTo(p1) == 0)
					found = false;
				else {
					otherP = seekPersonId(f.get(i));
					boolean flag = true;
					int j = 0;
					while (flag && j < a.size()) {
						if (a.get(j).getName().compareTo(otherP.getName()) == 0)
							flag = found = false;
						
						j++;
					}
				}
			
				if (found) {
					c[candidate] = otherP;
					candidate++;
				}
				found = true;
			}
		}
		
		return candidate;
	}

	private void process_the_large(ArrayList<Person> a, int k, int input) {
		if (!largeFriends.isEmpty()) {
			if (a.size() > largeFriends.size()) {
				largeFriends.clear();
				for (int i = 0; i < a.size(); i++)
					largeFriends.add(i, a.get(i));
			}
		} else {
			for (int i = 0; i < a.size(); i++)
				largeFriends.add(i, a.get(i));
		}
	}

	private boolean is_large_solution(ArrayList<Person> a, int k, int input, String p1, String p2) {
		return a.get(k).getName().compareTo(p2) == 0;
	}
}
