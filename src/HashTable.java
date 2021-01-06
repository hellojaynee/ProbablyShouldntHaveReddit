
import java.util.ArrayList;
import java.util.NoSuchElementException;

@SuppressWarnings("hiding")
public class HashTable {
    
    private int numElements;
    private ArrayList<List<Question>> Table;

    /**
     * Constructor for the HashTable.java
     * class. Initializes the Table to
     * be sized according to value passed
     * in as a parameter
     * Inserts size empty Lists into the
     * table. Sets numElements to 0
     * @param size the table size
     */
    public HashTable(int size) {
    	numElements = 0;
    	Table = new ArrayList<>();
        for (int i = 0; i < size; i++) {
        	Table.add(new List<Question>());
        }
        
    }
       
    /**Accessors*/
    
    /**
     * returns the hash value in the Table
     * for a given Object 
     * @param t the Object
     * @return the index in the Table
     */
    private int hash(Question q) {
        int code = q.hashCode();
        return code % Table.size();
    }
    
    /**
     * counts the number of elements at this index
     * @param index the index in the Table
     * @precondition 0 <=  index < Table.length
     * @return the count of elements at this index
     * @throws IndexOutOfBoundsException
     */
    public int countBucket(int index) throws IndexOutOfBoundsException{
    	if (index > Table.size()-1 && index > -1 ) {
    		throw new IndexOutOfBoundsException("countBucket(): Cannot count the number of elements, index out of bounds!");
    	}
        return Table.get(index).getLength();
    }
    
    /**
     * returns total number of elements in the Table
     * @return total number of elements
     */
    public int getNumElements() {
    	int total = 0;
        for (int i = 0; i < Table.size(); i++) {
        	total = total + countBucket(i);
        }
        return total;
    }
    
    /**
     * searches for a specified element in the Table
     * @param t the element to search for
     * @return the index in the Table (0 to Table.length - 1)
     * or -1 if t is not in the Table
     */
    public int search(Question q) {
       int index = hash(q);
       if (!Table.get(index).isEmpty()) {
    	   Table.get(index).placeIterator();
    	   if (Table.get(index).getIterator().getQuestion().equals(q.getQuestion())) {
    		   return index;
    	   }
       }
       return -1;
    }
    
     
    /**Manipulation Procedures*/
    
    /**
     * inserts a new element in the Table
     * calls the hash method to determine placement
     * @param t the element to insert
     */
    public void insert(Question q) {    
        int index = hash(q);
        Table.get(index).addLast(q);
        numElements++;
    }  
     
     
    /**
     * removes the element t from the Table
     * calls the hash method on the key to
     * determine correct placement
     * has no effect if t is not in
     * the Table
     * @param t the key to remove
     * @precondition t must be in the table
     * @throws NoSuchElementException when
     * the element is not in the table
     */
    public void remove(Question q) throws NoSuchElementException{
    int index = hash(q); 
    if (search(q) == -1) {
    throw new NoSuchElementException("remove(): Element is not in the table.");
    }else {
    Table.get(index).removeLast();
    numElements--;
    }	
    }


    /**Additional Methods*/
    
    /**
     * 
     * Finds a question based on text of question input into method and returns the question object
     * @param a Question
     */
    public Question findQuestion(Question q) throws NoSuchElementException{
    	for (int i = 0; i < Table.size(); i++) {
    		if (!Table.get(i).isEmpty()) {
    				List<Question> qList = Table.get(i);
    				qList.placeIterator();
    				
    				while (!qList.offEnd()) {
    					Question q2 = qList.getIterator();
    					if (q2.getQuestion().equals(q.getQuestion())) {
    						return q2; 
    					}
    					this.Table.get(i).advanceIterator();
    				}
    		}
        }
    	return null;
    }
    
    /**
     * Returns a list of all questions with a given user
     */
    public void authorPrint(String author) {
    	for (int i = 0; i < Table.size(); i++) {
    		if (!Table.get(i).isEmpty()) {
    				List<Question> qList = Table.get(i);
    				qList.placeIterator();
    			while (!qList.offEnd()) {
    				Question q = qList.getIterator();
    				if (q.getAuthor().equals(author)) {
    					System.out.println(q);
    				}
    				
    				this.Table.get(i).advanceIterator();
    			}
    		}
        }
    }
    
    
    /**
     * Returns a list of all questions with a given number of upvotes
     */
    public void upVotePrint(int upVotes) {
    	for (int i = 0; i < Table.size(); i++) {
    		if (!Table.get(i).isEmpty()) {
    				List<Question> qList = Table.get(i);
    				qList.placeIterator();
    			while (!qList.offEnd()) {
    				Question q = qList.getIterator();
    				if (q.getUpvotes() == upVotes) {
    					System.out.println(q);
    				}
    				
    				this.Table.get(i).advanceIterator();
    			}
    		}
        }
    }
    
    /**
     * Prints all questions stored in the hash table
     */
    public void unsortedPrint() {
    	for (int i = 0; i < Table.size(); i++) {
    		if (!Table.get(i).isEmpty()) {
    			System.out.print(Table.get(i).toString());
    			System.out.println();
    		}
        }
    }
    
    /**
     * Prints all questions and answers formatted for the input file
     */
    public String outputPrint() {
    	String output = "";
    	for (int i = 0; i < Table.size(); i++) {
    		if (!Table.get(i).isEmpty()) {
    			// go through the list and print all questions and answers
    			
    			List<Question> qList = Table.get(i);
    			
    			qList.placeIterator();
    			while (!qList.offEnd()) {
    				Question newQ = (Question) qList.getIterator();
    				output = output + newQ.outputString();
    				this.Table.get(i).advanceIterator();
    			}
    		}
        }
    	return output;
    }

    /**
     * Prints all the keys at a specified
     * bucket in the Table. Each element displayed
     * on its own line, with a blank line 
     * separating each element
     * Above the elements, prints the message
     * "Printing bucket #<bucket>:"
     * Note that there is no <> in the output
     * @param bucket the index in the Table
     * @precondition 0 <=  bucket < Table.length
     * @throws IndexOutOfBoundsException
     */
    public void printBucket(int bucket) throws IndexOutOfBoundsException{
        if (bucket < Table.size() && bucket > -1) {
        	System.out.println("Printing bucket #" + bucket + ":\n");
    	System.out.print(Table.get(bucket).toString());
    	System.out.println();
        }else {
        	throw new IndexOutOfBoundsException("printBucket(): Cannot print bucket, outside the range of table.");
        }
    }
        
    /**
     * Prints the first element at each bucket
     * along with a count of the total elements
     * with the message "+ <count> -1 more 
     * at this bucket." Each bucket separated
     * with to blank lines. When the bucket is 
     * empty, prints the message "This bucket
     * is empty." followed by two blank lines
     */
    public void printTable(){
        for (int i = 0; i < Table.size(); i++) {
        	if (!Table.get(i).isEmpty()) {
        		System.out.println("Bucket: " + i);
        		System.out.print(Table.get(i).getFirst());
        		System.out.println();
        		System.out.println("+ " + (countBucket(i)-1) + " more at this bucket");
        		System.out.println();
        	}else {
        		System.out.println("Bucket: " + i);
        		System.out.println("This bucket is empty.");
        		System.out.println();
        	}
        }
     }  
}

