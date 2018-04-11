package restaurant;

import exceptions.EmptyCollectionException;

public class CustomerQueue {
	/*
	 * This is a queue of groups of customers
	 */

	private NodeQ first;
	private NodeQ last;
	private int count;

	public CustomerQueue() {
		first = null;
		last = null;
		count = 0;
	}

	public NodeQ getFirst() {
		return first;
	}

	public void setFirst(NodeQ first) {
		this.first = first;
	}

	public NodeQ getLast() {
		return last;
	}

	public void setLast(NodeQ last) {
		this.last = last;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isEmpty() {
		return (count == 0);
	} 

	public void print() {
		NodeQ current = first;
		Client aux;
		
		if (isEmpty()) 
			throw new exceptions.EmptyCollectionException("list");
		
		while (current != null) {
			aux = current.getAclient();
			System.out.print("\n" + aux.getTin() + " " + aux.getTshirt() + " " + aux.getBill());
			current = current.getNext();
		}
	}
	
	public void print(int tshirt) {
		if (isEmpty())
			throw new exceptions.EmptyCollectionException("list");
		
		NodeQ current = first;
		Client aux;
		
		while (current != null) {
			aux = current.getAclient();
			if (aux.getTshirt() == tshirt) 
				System.out.print("\n" + aux.getTin() + " " + aux.getTshirt());
			
			current = current.getNext();
		}
	}
	
	public void addClient2rear(Client clientdata) {
		NodeQ nClient = new NodeQ(clientdata);
		
		if (isEmpty()) 
			first = last = nClient;
		else {
			last.setNext(nClient);
			nClient.setPrevious(last);
			last = nClient;
		}
		
		count++;
	}

	public void addClient2front(Client clientdata) {
		NodeQ nClient = new NodeQ(clientdata);
		
		nClient.setNext(first.getNext());
		first.getNext().setPrevious(nClient);
		first = nClient;
		
		count++;
	}



	public void insertGroup(CustomerQueue groupclients) {
		groupclients.getFirst().setPrevious(this.last);
		this.last.setNext(groupclients.getFirst());
		
		this.last = groupclients.getLast();
	}

	public void invoiceGroups() {
		if (isEmpty())
			throw new exceptions.EmptyCollectionException("list");
		
		double sumOfBills = 0;
		NodeQ current = first;
		NodeQ nextN = current.getNext();
		
		while (nextN != null) {
			Client currentC = current.getAclient();
			Client nextNC = nextN.getAclient();
			
			if (currentC.getTshirt() == nextNC.getTshirt()) {
				sumOfBills += currentC.getBill(); 
			} else {
				sumOfBills += currentC.getBill();
				System.out.print("\nThe sum of the bill of group " + currentC.getTshirt() +
						" is:\t" + sumOfBills);
				sumOfBills = 0;
			}
			
			current = nextN;
			nextN = current.getNext();
		}
		
		if (current.getAclient().getTshirt() == current.getPrevious().getAclient().getTshirt()) {
			sumOfBills += current.getAclient().getBill();
			System.out.print("\nThe sum of the bill of group " + current.getAclient().getTshirt() +
					" is:\t" + sumOfBills);
		} else {
			sumOfBills = current.getAclient().getBill();
			System.out.print("\nThe sum of the bill of group " + current.getAclient().getTshirt() +
					" is:\t" + sumOfBills);
		}
	}

	public void insertClient(Client clientdata) {
		NodeQ current = first;
		NodeQ nClient = new NodeQ(clientdata);
		Boolean find = false;
		
		if (!isEmpty()) {
			while (current != null && !find) {
				if (current.getAclient().getTshirt() == clientdata.getTshirt())
					find = true;
				else 
					current = current.getNext();
			}
			
			if (current != null) {
				if (current.getPrevious() == null)
					addClient2front(clientdata);
				else {
					current.getPrevious().setNext(nClient);
					nClient.setPrevious(current.getPrevious());
					current.setPrevious(nClient);
					nClient.setNext(current);
				}	
			} else {
				addClient2rear(clientdata);
			}
		} else {
			first = last = nClient;
		}
		
		count++;
	}

	public Client removeFirstinLine() {
		Client c;
		if (!isEmpty()) {
			if (first != null) {
				c = first.getAclient();
				first = first.getNext();
				count--;
			} else {
				c = null;
				count--;
			}
		
			if (isEmpty()) 
				last = null;
		} else
			c = null;
		
		return c;
	}
	
	public CustomerQueue numberTshirt(Client c) {
		NodeQ current = first;
		boolean flag = true;
		while(current != null && flag) {
			if (current.getAclient().getTshirt() == c.getTshirt())
				flag = false;
			else
				current = current.getNext();
		}
		
		CustomerQueue n = new CustomerQueue();
		n.setFirst(current);
		flag = true;
		int i = 1;
		while(current != null & flag) {
			if (current.getAclient().getTshirt() != c.getTshirt()) 
				flag = false;
			else {
				current = current.getNext();
				i++;
			}
		}
		i--;
		n.setLast(current.getPrevious());
		n.setCount(i);
		return n;
	}
}
