package restaurant;

import java.util.ArrayList;
import java.util.Scanner;

import sun.misc.Cleaner;

public class ManageRestaurantQueue {

	public static void main(String[] args) {
		CustomerQueue  openbuffet = new CustomerQueue();
		CustomerQueue group9 = new CustomerQueue();
		
		Client client1 = new Client(11,"BF",10);
		Client client2 = new Client(5,"AF",20);
		Client client3 = new Client(5,"AD",30);
		Client client4 = new Client(7,"XF",40);
		Client client7 = new Client(9,"MB",70);
		Client client9 = new Client(9,"RB",90);
		Client client10 = new Client(11,"BB",100);
		
		group9.addClient2rear(client7);
		group9.addClient2rear(client9);
		
		openbuffet.addClient2rear(client1);	
		
		System.out.println("Queue of group9");
		group9.print();
		
		System.out.println("\n\nQueue of openBuffet");
		openbuffet.print();

		openbuffet.insertGroup(group9);
		
		System.out.println("\n\nGroup9 in openBuffet");
		openbuffet.print();
		
		openbuffet.insertClient(client10);
		openbuffet.insertClient(client2);
		openbuffet.insertClient(client3);
		openbuffet.addClient2front(client4);
		System.out.println("\n\nQueue of openBuffet");
		openbuffet.print();
		
		System.out.println("\n\nQueue of openBuffet");
		openbuffet.print();

		Client cC = openbuffet.removeFirstinLine();
		CustomerQueue n = openbuffet.numberTshirt(cC);
		
}

