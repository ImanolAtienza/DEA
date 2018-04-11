/** * ArrayIterator represents an iterator over the elements of an array.
 * * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/12/08
 */
package dsa;import java.util.*;public class ArrayIterator<T> implements Iterator<T>{  private int count;    // the number of elements in the collection   private LinearNode<T> current;  // the current position in the iteration    /**    * Sets up this iterator using the specified items.
    * 
    * @param collection  the collection to create the iterator for
    * @param size        the size of the collection    */   public ArrayIterator (LinearNode<T> node, int size)   {  current = node;      count = size; }   /**    * Returns true if this iterator has at least one more element    * to deliver in the iteration.
    *
    * @return  true of this iterator has at least one more element to deliver
    *          in the iteration    */   public boolean hasNext()   {  return (current != null);  }   /**    * Returns the next element in the iteration. If there are no    * more elements in this iteration, a NoSuchElementException is    * thrown.
    *
    * @return  the next element in the iteration
    * @throws NoSuchElementException  if an element not found exception occurs    */   public T next()   { if (! hasNext())         throw new NoSuchElementException();    	 T result = current.getElement(); 	 current = current.getNext(); 	 return result;  }   /**    * The remove operation is not supported in this collection.
    * 
    * @throws UnsupportedOperationException  if an unsupported operation
    *                                        exception occurs    */   public void remove() throws UnsupportedOperationException   {  throw new UnsupportedOperationException();   }}
