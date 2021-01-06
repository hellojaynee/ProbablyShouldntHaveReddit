import java.util.NoSuchElementException;

public class List<T extends Comparable<T>> {
	private class Node {
		private T data;
		private Node next;
		private Node prev;

		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

	private int length;
	private Node first;
	private Node last;
	private Node iterator;

	/**** CONSTRUCTOR ****/
	/**
	 * Instantiates a new empty list
	 * 
	 * @postcondition creates an emoty List
	 */
	public List() {
		first = last = iterator = null;
		length = 0;

	}

	/**
	 * Instantiates a new List by copying another List
	 * @param original the List to make a copy of
	 * @postcondition a new List object, which is an identical but separate copy of
	 * the List original
	 */
	public List(List<T> original) {
		if (original == null) {
			return;
		}
		if (original.length == 0) {
			this.length = 0;
			this.first = null;
			this.last = null;
			this.iterator = null;
		} else {
			Node temp = original.first;
			while (temp != null) {
				this.addLast(temp.data);
				temp = temp.next;
			}
			this.iterator = null;
		}
	}

	/**** ACCESSORS ****/
	/**
	 * Returns the value stored in the first node 
	 * @precondition (length!=0) List can't be empty
	 * @return the integer value stored at node first
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getFirst()" + "List is Empty. No data to access!");
		}
		return first.data;
	}

	/**
	 * Returns the value stored in the last node
	 * 
	 * @precondition (length!=0) List can't be empty
	 * @return the integer value stored in the node last
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getLast()" +" List is Empty. No data to access!");
		}
		return last.data;
	}

	/**
	 * Returns the elements currently pointed at by the iterator
	 * @precondition iterator cannot be null
	 * @return the elements pointed at by the iterator
	 * @throws NullPointerException when the precondition is violated.
	 */
	public T getIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("getIterator() " + "Iterator is off the end of the list.");
		}
		return iterator.data;
	}

	/**
	 * Returns the current length of the list 
	 * @return the length of the list from 0 to n
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns whether the list is currently empty 
	 * @return whether the list is empty
	 */
	public boolean isEmpty() {
		return length == 0;
	}

	/**
	 * Returns whether the iterator is off the end of the list
	 * @return whether the iterator is null
	 */
	public boolean offEnd() {
		return iterator == null;
	}
	
	/**
     * Determines whether a List is sorted
     * by calling its recursive helper method
     * isSorted
     * Note: An empty List can be
     * considered to be (trivially) sorted
     * @return whether this List is sorted
     */
    public boolean isSorted() {
    	return isSorted(first);
    	
    }
    
    /**
     * Determines whether a List is 
     * sorted in ascending order recursively
     * @return whether this List is sorted
     */
    private boolean isSorted(Node node) {
    	if (node == last || node == null) {
    		return true;
    	}else if(node.data.compareTo(node.next.data) > 0) {
    		return false;
    	}else {
    		return isSorted(node.next);
    	}
    }
    
    /**
     * Returns the index of the iterator
     * from 1 to n. Note that there is 
     * no index 0. Does not use recursion.
     * @precondition !offEnd()
     * @return the index of the iterator
     * @throws NullPointerException when
     * the precondition is violated
     */
    public int getIndex() throws NullPointerException{
    	if(offEnd()) {
    		throw new NullPointerException("getIndex(): Iterator is currently null, can't get the index!");
    	} else if(iterator == first){
    		return 1;
    	} else {
    		Node temp = first;
    		int count = 1;
    		while(temp != iterator) {
    			temp = temp.next;
    			count++;
    		}
    		return count;
    	}
    }
    
    /**
     * Uses the iterative linear search
     * algorithm to locate a specific
     * element in the list
     * @param element the value to search for
     * @return the location of value in the
     * List or -1 to indicate not found
     * Note that if the List is empty we will
     * consider the element to be not found
     * @postcondition: position of the iterator remains
     * unchanged!
     */
    public int linearSearch(T element) {
    	Node temp = first;
    	int index = 1;
    	while (temp != null) {
    		if (temp.data.equals(element)) {
    			return index;
    		}
    		temp = temp.next;
    		index++;
    	}
        return -1;
    }
    
    /**
     * Returns the index from 1 to length
     * where value is located in the List
     * by calling the private helper method
     * binarySearch
     * @param value the value to search for
     * @return the index where value is 
     * stored from 1 to length, or -1 to
     * indicate not found
     * @precondition isSorted()
     * @postcondition the position of the
     * iterator must remain unchanged! 
     * @throws IllegalStateException when the
     * precondition is violated.
     */
    public int binarySearch(T value) throws IllegalStateException {
    	if (!isSorted()) {
    		throw new IllegalStateException("List is not sorted! Cannot perform binary search.");
    	}
    	return binarySearch(1, getLength(), value);
    	
    }
    
    /**
     * Searches for the specified value in
     * the List by implementing the recursive
     * binarySearch algorithm
     * @param low the lowest bounds of the search
     * @param high the highest bounds of the search
     * @param value the value to search for
     * @return the index at which value is located
     * or -1 to indicate not found
     * @postcondition the location of the iterator
     * must remain unchanged
     * @precondition the list must be sorted in increasing order
     */
    private int binarySearch(int low, int high, T value) {
    		T midValue = null;
    		int mid = high/low;
    		int index = 1; 
    		Node temp = first;
    		
    		while (index < mid+1) {
    			if (index == mid) {
    				midValue = temp.data;
    			}
    			temp = temp.next;
    			index++;
    		}
    		
    		if (high < low) {
    			return -1;
    		} else if (value.equals(midValue)) {
    			return index-1;
    		} else if (value.compareTo(midValue) > 0) {
    			return binarySearch(index, high, value);
    		} else {
    			return binarySearch(low, index-2, value);
    		}
    }

    /**
     * Pre: A[] is sorted in increasing order
method binarySearch(A[1 ... n], value, low, high)
    if high < low
        return not found
    mid := low + (high-low)/2; //the midpoint formula
    if a[mid] := value
        return mid
    else if value < A[mid] //search the left half
        return binarySearch(A, value, low, mid-1)
    else //search the right half
        return binarySearch(A, value, mid+1, high)

     */

	/**** MUTATORS ****/
	/**
	 * Creates a new first element
	 * @param data the data to insert at the front of the list
	 * @postcondition new node created and becomes first in the list
	 */
	public void addFirst(T data) {
		if (first == null) {
			first = last = new Node(data);
		} else {
			Node N = new Node(data);
			N.next = first;
			first.prev = N;
			first = N;
		}
		length++;
	}

	/**
	 * Creates a new last element
	 * @param data the data to insert at the end of the list
	 * @postcondition new node created and becomes last in the list
	 */
	public void addLast(T data) {
		if (first == null) {
			first = last = new Node(data);
		} else {
			Node N = new Node(data);
			N.prev = last;
			last.next = N;
			last = N;
		}
		length++;
	}

	/**
	 * Returns moves the iterator to the start of the list
	 * @return place the iterator to the start of the list
	 */
	public void placeIterator() {
		iterator = first;
	}

	/**
	 * removes the element at the front of the list
	 * @precondition list cannot be empty (length!=0)
	 * @postcondition remove the first element in the list
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeFirst(): Cannot remove from an empty List!");
		} else if (length == 1) {
			first = last = iterator = null;
		} else {
			if (iterator == first) {
				iterator = null;
			}
			first = first.next;
			first.prev = null;
		}
		length--;
	}

	/**
	 * removes the element at the end of the list 
	 * @precondition list cannot be empty (length!=0)
	 * @postcondition remove the last element in the list
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeLast(): Cannot remove from an empty List!");
		} else if (length == 1) {
			first = last = iterator = null;
		} else {
			if (iterator == last) {
				iterator = null;
			}
			last = last.prev;
			last.next = null;
		}
		length--;
	}

	/**
	 * Removes the element currently pointed to by the iterator.
	 * @Precondition: Iterator != null
	 * @Postcondition: iterator then points to null
	 * @throws NullPointerException if precondition violated
	 */
	public void removeIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("removeIterator(): " + "cannot remove null object!");
		} else if (iterator == first) {
			removeFirst();
		} else if (iterator == last) {
			removeLast();
		} else {
			iterator.prev.next = iterator.next;
			iterator.next.prev = iterator.prev;
			length--;
		}
		iterator = null;
	}
	
	/**
	 * Add an element after the current element that pointed by the iterator.
	 * @Precondition: Iterator != null
	 * @Postcondition: a new node created place behind the iterator
	 * @throws NullPointerException if precondition violated
	 */
	public void addIterator(T data) throws NullPointerException {
		if (offEnd()) {
			throw new NullPointerException("addIterator(): " + "Iterator is off end. Cannot add.");
		} else if (iterator == last) {
			addLast(data);
		} else {
			Node n = new Node(data);
			Node temp = iterator.next;
			n.next = temp;
			n.prev = iterator;
			iterator.next = n;
			temp.prev = n;
			length++;
		}
	}

	/**
	 * Moves the iterator up by one node
	 * @Precondition: Iterator !offEnd()
	 * @throws NullPointerException when precondition violated
	 */
	public void advanceIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("advanceIterator(): " + "Iterator is null, cannot advance iterator.");
		} else {
			iterator = iterator.next;
		}
	}

	/**
	 * Moves the iterator down by one node 
	 * @Precondition: iterator != null
	 * @throws NullPointerException when precondition violated
	 */
	public void reverseIterator() throws NullPointerException {
		if (iterator == null) {
			throw new NullPointerException("reverseIterator(): " + "Iterator is null, cannot reverse iterator.");
		} else {
			iterator = iterator.prev;
		}
	}
	
	/**
     * Places the iterator at first
     * and then iteratively advances 
     * it to the specified index
     * no recursion
     * @param index the index where
     * the iterator should be placed
     * @precondition 1 <= index <= length
     * @throws IndexOutOfBoundsException
     * when precondition is violated
     */
    public void advanceToIndex(int index) throws IndexOutOfBoundsException{
    	if(index < 1 || index > length) {
    		throw new IndexOutOfBoundsException("advanceToIndex(): " + "Index is out of bound, cannot advance!");
    	} else {
    		placeIterator();
    		int count = 1;
    		while (count != index) {
    			advanceIterator();
    			count++;
    		}
    	}
    }

	/**
	 * Determines whether two Lists have the same data in the same order
	 * @param L the List to compare to this List
	 * @return whether the two Lists are equal
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof List)) {
			return false;
		} else {
			List<T> L = (List<T>) o;
			if (this.length != L.length) {
				return false;
			} else {
				Node temp1 = this.first;
				Node temp2 = L.first;
				while (temp1 != null) { // Lists are same length
					if (temp1.data != temp2.data) {
						return false;
					}
					temp1 = temp1.next;
					temp2 = temp2.next;
				}
				return true;
			}
		}
	}
	

	/**** ADDITIONAL OPERATIONS ****/

	/**
	 * List with each value on its own line At the end of the List a new line
	 * @return the List as a String for display
	 */
	@Override
	public String toString() {
		String result = "";
		Node temp = first;
		while (temp != null) {
			result += temp.data + " ";
			temp = temp.next;
		}
		return result;
	}
	/**
	 * prints the contents of the linked list to the screen in the format 
	 * #. <element> followed by a newline
	 * @return the contents of the linked list to the screen in the numbered list
	 */
	public void printNumberedList() {
		Node temp = first;
		int i = 1;
		while (temp != null) {
			System.out.println( i + ": " + temp.data);
			temp = temp.next;
			i++;
		}
	}
	
	/**
     * Prints a linked list to the console
     * in reverse by calling the private 
     * recursive helper method printReverse
     */
    public void printReversed() {
    	printReversed(last);  	
    }
    
    /**
     * Recursively prints a linked list to the console
     * in reverse order from last to first (no loops)
     * Each element separated by a space
     * Should print a new line after all
     * elements have been displayed
     */    
    private void printReversed(Node node) {
    	if(node == null) {
    		System.out.println();
    	}else {
    		System.out.print(node.data + " ");
    		printReversed(node.prev);
    	}		
     
    }     
	
	
	
	}