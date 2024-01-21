
/** This class represents a set. Each set has a linear node that is the start of the set */

public class Set <T> {
	
	/** linear node for the start of the set */
	private LinearNode<T> setStart;
	
	/** constructor that creates the set and sets the start to null */
	public Set() {
		setStart = null;
	}
	
	/** public method that adds the set */
	public void add(T element) {
		
		LinearNode<T> newNode = new LinearNode<T>(element); // creates a new linear node using the given element
		
		if (setStart == null) { // if the linked list is empty, set the new node as the start of the list
			setStart = newNode;
		}
		else {
			newNode.setNext(setStart.getNext()); // if the list isn't empty, the new node is set as the node that comes after to starting node
			setStart.setNext(newNode); // the starting node then connects to the node that is now in front of it
		}
	}
	
	/** public method that returns the length of the set */
	public int getLength() {
		
		int count = 0;
		LinearNode<T> current = setStart;
		
		while (current != null) { // loops until a node points to null
			current = current.getNext();
			count++; // increases the count for each loop
		}
		return count;
	}
	
	/** public method that returns the element at a specific node */
	public T getElement(int i) {
		
		int counter = 0;
		LinearNode<T> current = setStart;
		while (current != null && counter != i) { // just like the getLength method except it now checks if the counter is equal to the given integer 
			current = current.getNext();
			counter++; // counter increase each loop
		}
		
		return current.getElement(); // returns the element at the number of currents node
	}
	
	/** public method that checks if a specific element is in the set  */
	public boolean contains(T element) {
		
		LinearNode<T> current = setStart;
		while(current != null) { // loops until a node points to null
			if (current.getElement().equals(element)) { // if the element at the specific node is equal to the given element, true is returned
				return true;
			}
			else { // if it isn't equal to the given element, the next node is selected 
				current = current.getNext();
			}
		}
		return false; 
	}
	
	/** public method that turns the set into a string */
	public String toString() {
		
		String print = "";
		
		LinearNode<T> current = setStart;
		while (current != null) {
			print = print + current.getElement() + " "; // gets the elements of the nodes and adds them to a string with a space between each element
			current = current.getNext();
		}
		return print;
	}

}
