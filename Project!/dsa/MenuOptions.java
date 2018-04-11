package dsa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.xml.crypto.Data;

public class MenuOptions {
	
//	cliqueG6115 c = new cliqueG6115();
//	c.writeFileFriends();
	static boolean flag = false;
	static People p = new People();
	
	public static void firstMenu() {
	Scanner in;
	String file;
		
	System.out.println("Hi! Welcome to our social network!");
	System.out.println("Menu");
		while (!flag) {
			System.out.print("1- Upload people.\n2- Upload relationship.\n3-Print out the Social Network.\n4-Other operations.\n5-Exit!");
			System.out.print("\nInsert your choice: \n");
			switch (new Scanner(System.in).nextInt()) {
			case 1:
				System.out.println("Please, insert the name of the file to upload.");
				in = new Scanner(System.in);
				file = in.next();
				try {
					p.uploadPeople(file);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Please, insert the name of the file to upload.");
				in = new Scanner(System.in);
				file = in.next();
				p.uploadRelationship(file);
				break;
			case 3:
				p.printPeople();
				break;
			case 4:
				newMenu();
			case 5:
				flag = true;
				break;
			default:
				break;
			}
		}
	}
	
	public static void newMenu() {
		System.out.println("Menu");
		while (!flag) {
			System.out.print("1- Insert a person to know his/her friends.\n2- Given a city retrieve all the people who were born there.\n3-retrieve the people who were born between dates D1 and D2.\n4- recover the values of the attributes name, surname, birthplace and studiedat of the people on the network whose birthplace matches the hometown of the people who are described in residential.txt.\n5-See the friends of a person.\n6-Clique of friends. \n7-Go back.");
			System.out.print("\nInsert your choice: \n");
			Scanner in = new Scanner(System.in);
			switch (new Scanner(System.in).nextInt()) {
			
			case 1:
				System.out.println("Please, insert the name of the file to upload.");
				String file = in.next();
				try {
					p.uploadPeople(file);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Please, insert the city.");
				String city = in.next();
				p.searchPeopleCity(city);
				System.out.println();
				break;
			case 3:
				System.out.println("Please, insert the first date.");
				String d1st = in.nextLine();
				System.out.println("Please, insert the second date.");
				String d2st = in.nextLine();
				Date d1da, d2da;
				try {
					d1da = new SimpleDateFormat("dd-MM-yy").parse(d1st);
					d2da = new SimpleDateFormat("dd-MM-yy").parse(d2st);
					p.searchPeopleData(d1da,d2da);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				p.birthplaceHometown();
				break;
			case 5:
				System.out.println("Please, guive the name of a person.");
				String name = in.nextLine();
				p.personFriends(name);
				break;
			case 6:
				System.out.println("The diferent clique of friends...");
				p.cliquesOfFriends();
				break;
			case 7:
				firstMenu();
			default:
				break;
			}
		}
	}
}
