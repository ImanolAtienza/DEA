/**
 * Josephus
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/13/08
 */

import java.util.ArrayList;
import java.util.Scanner;

import dsa.LinearNode;
import dsa.LinkedList;
public class Josephus    
{  /* Continue around the circle eliminating every nth soldier 
    * until all of the soldiers have been eliminated. */
   public static void main ( String[] args)
   {  int numPeople, gap, newGap, counter;
      LinkedList<Integer> list = new LinkedList<Integer>();
      Scanner in = new Scanner(System.in);

      /* get the initial number of soldiers */
      System.out.println("Enter the number of soldiers: ");
      numPeople = in.nextInt();
      in.nextLine();

      /* get the gap between soldiers */
      System.out.println("Enter the gap between soldiers: ");
      gap = in.nextInt();

      /* load the initial list of soldiers */
      for (int count=1; count <= numPeople; count++)
      { list.add(new Integer(count)); }
      counter = gap; 
      newGap = gap;
      System.out.println("The order is: ");

      LinearNode<Integer> node = list.getHead();
		
      while (!list.isEmpty()) {
    	  for (int i = 1; i < gap; i++) {
    		  node = node.getNext();
    		  if (node == null) {
    			  node = list.getHead();
    		  }
    	  }
    	  System.out.println(list.remove(node.getElement()));
    	  node = node.getNext();
    	  if (node == null) {
    		  node = list.getHead();
    	  }
		}
   }
}
