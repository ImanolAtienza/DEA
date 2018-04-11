/**
 * ArrayList represents an array implementation of a list. The front of
 * the list is kept at array index 0. This class will be extended
 * to create a specific kind of list.
 *
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/13/08
 */

package dsa;
import exceptions.*;

import java.util.Iterator;

public class LinkedList<T> implements ListADT<T>, Iterable<T>
{
   /*protected final int DEFAULT_CAPACITY = 100;
   
   protected int rear;
   protected T[] list;*/
	private final int NOT_FOUND = -1;
	protected int count;
	protected LinearNode<T> head, tail;

   /**
    * Creates an empty list using the specified capacity.
    *
    * @param initialCapacity  the integer value of the size of the array list
    */
   public LinkedList ()
   {  count = 0;
      head = tail = null;
   }

   public LinearNode<T> getHead() {
	   return head;
   }

   public void add(T data) {
	   LinearNode<T> aux = new LinearNode<T>(data);
	   
	   if (isEmpty()) {
		   head = tail = aux;
	   } else {
		   tail.setNext(aux);
		   tail = aux;
	   }
	   
	   count++;
   }
   
   /**
    * Removes and returns the last element in this list.
    *
    * @return                           the last element in the list
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T removeLast () throws EmptyCollectionException
   {   
	  if (isEmpty())
		  throw new EmptyCollectionException ("list");
	  
	  LinearNode<T> aux = head;
      T result = tail.getElement();
      
      count--;
      /** shift the elements */
      for (int scan = 1; scan < count; scan++)
         aux = aux.getNext();
      
      tail = aux;
      tail.setNext(null);
      
      return result;
   }

   /**
    * Removes and returns the first element in this list.
    *
    * @return                           the first element in the list
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T removeFirst() throws EmptyCollectionException
   {  
	  if (isEmpty())
		  throw new EmptyCollectionException ("list");
	      
	  LinearNode<T> aux;
      T result;
      
      count--;
      result = head.getElement();
      aux = head.getNext();
      head = aux;
      
      return result;
   }

   /**
    * Removes and returns the specified element.
    *
    * @param element                    the element to be removed and returned 
    *                                   from the list
    * @return                           the removed elememt
    * @throws ElementNotFoundException  if an element not found exception occurs
    */
   public T remove (T element)
   {  T result;
      LinearNode<T> index = find (element);
      LinearNode<T> aux = head;
      LinearNode<T> previous = null;
      boolean flag = false;
      if (index == null)
          throw new ElementNotFoundException ("list");

      result = index.getElement();

      /** shift the appropriate elements */
      while (aux != null && !flag) {
    	  if (index.equals(aux)) {
    		  flag = true;
    	  } else {
    		  previous = aux;
    		  aux = aux.getNext();
    	  }
      }
 
      if (size() == 1)
    	  head = tail = null;
      else if (aux.equals(head)) 
    	  head = aux.getNext();
      else if (aux.equals(tail)) {
    	  tail = previous;
    	  tail.setNext(null);
      } else
    	  previous.setNext(index.getNext());
      
      count--;
      return result;
   }
   
   /**
    * Returns a reference to the element at the front of this list.
    * The element is not removed from the list.  Throws an
    * EmptyCollectionException if the list is empty.  
    *
    * @return                           a reference to the first element in the 
    *                                   list
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T first() throws EmptyCollectionException
   {  if (isEmpty())
         throw new EmptyCollectionException ("list"); 

      return head.getElement();
   }

   /**
    * Returns a reference to the element at the rear of this list.
    * The element is not removed from the list.  Throws an
    * EmptyCollectionException if the list is empty.  
    *
    * @return  a reference to the last element of this list
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T last() throws EmptyCollectionException
   {  if (isEmpty())
         throw new EmptyCollectionException ("list"); 

      return tail.getElement();
   }

   /**
    * Returns true if this list contains the specified element.
    *
    * @param target  the element that the list is searched for
    * @return        true if the target is in the list, false if otherwise 
    */
   public boolean contains (T target)
   {
      return (find(target) != null);
   }

   /**
    * Returns the array index of the specified element, or the
    * constant NOT_FOUND if it is not found.
    *
    * @param target  the element that the list will be searched for
    * @return        the integer index into the array containing the target
    *                element, or the NOT_FOUND constant
    */
   private LinearNode<T> find (T target)
   {  if (isEmpty())
	   	throw new EmptyCollectionException ("list"); 
	   
	  LinearNode<T> result = null;
      boolean found = false;
      LinearNode<T> aux = head;

      
      while (!found && aux != null)
    	  if (target.equals(aux.getElement()))
    		  found = true;
          else
        	  aux = aux.getNext();
      
      if (found)
         result = aux;
      
      return result;
   }

   /**
    * Returns true if this list is empty and false otherwise. 
    *
    * @return  true if the list is empty and false if otherwise
    */
   public boolean isEmpty(){return (count == 0);  }
 
   /**
    * Returns the number of elements currently in this list.
    *
    * @return  the integer representation of the number of elements in the list
    */
   public int size() { return count;  }

   /**
    * Returns an iterator for the elements currently in this list.
    * 
    * @return  an iterator for the elements in this list
    */

public Iterator<T> iterator()
   {    return new ArrayIterator<T> (head, count); }

   /**
    * Returns a string representation of this list. 
    * 
    * @return  the string representation of this list
    */
   public String toString()
   {  String result = "";
   	  LinearNode<T> aux = head;
   
      for (int scan=0; scan < count; scan++) {
         result = result + aux.getElement().toString() + "\n";
         aux = aux.getNext(); }
      return result; }

   /**
    * Creates a new array to store the contents of this list with
    * twice the capacity of the old one.
    */

}
