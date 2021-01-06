import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Question implements Comparable<Question>{
  private String question; 
  private String author; 
  private ArrayList<Answer> answers = new ArrayList<Answer>();
  private long nanoTime; 
  private int upVotes;   
 
  
  Question(String question){

	  this.question = question;

	  this.author = "unknown"; 

	  this.nanoTime = System.currentTimeMillis();

	  this.upVotes = 0;

  }
  /**
   * Constructor
   * @param question the question to be added
   * @param author  the username of the person adding the question
   */
  Question(String question, String author){
	  this.question = question;
	  this.author = author; 
	  this.nanoTime = System.currentTimeMillis();
	  this.upVotes = 0;
  }

  Question(String question, String author, long nanoTime) {
	  this(question, author);
	  this.nanoTime = nanoTime;
	  this.upVotes = 0;
  }
  Question(String question, String author, long nanoTime, int upVotes) {
	  this(question, author, nanoTime);
	  this.upVotes = upVotes;  
  }
  
  /*
   ************* Accessors *************
   */
  
  /**
   * Returns the text of the question
   * @return
   */
  public String getQuestion() {
	  return this.question;
  }
  
  /**
   * Returns the username of the person who wrote the question
   * @return
   */
  public String getAuthor() {
	  return this.author;  
  }
  
  /**
   * Returns the number of upVotes to the questions
   * @return current Number of upVotes
   */
  public int getUpvotes() {
	  return this.upVotes;
  }
  
  /**
   * Returns the nanoTime the question was created at
   * @return the nanoTime when the question was created
   */
  public long getNanoTime() {
		return this.nanoTime;
	}
  
/*
 ************* Mutators *************
 */
  
	/**
	 * Sets the text of the question
	 * @param question the text of the question
	 */
public void setQuestion(String question) {
	 this.question = question; 
}

/**
 * Sets the author of the question
 * @param author the username of the person writing the question
 */
public void setAuthor(String author) {
	  this.author = author; 
}


/**
 * Adds an answer to the array of answers for the question
 * @param answer the text of the answer to the question
 * @param author the username of the person answering the question
 */
public void addAnswer(String answer, String author) {
	  Answer newAnswer = new Answer(answer, author);
	  answers.add(newAnswer);
}

public void addAnswer(String answer, String author, long nanoTime) {
	Answer newAnswer = new Answer(answer, author, nanoTime);
	  answers.add(newAnswer);
}

/**
 * Increases the number of upvotes for the question by 1. 
 */
public void increaseUpVotes() {
	  this.upVotes++;
}

  
/*
 ************* Other Methods *************
 */

/**
 * Prints questions in answers, in format to be read back in to the program 
 */
public String outputString() {
	String output = "";
	output += "-1\n" + question + "\n" + author + "\n" + nanoTime + "\n" + upVotes + "\n";
	
	for (int i = 0; i < answers.size(); i++) {
		output += "-2"+ "\n" + answers.get(i).getAnswer() + "\n" + answers.get(i).getAuthor() + "\n" + answers.get(i).getNanoTime() + "\n";
	}
	return output; 
}
  
  /**
   * Prints all of the answers to the questions, formatted
   * 
   */
  public void printAnswers() {
	  for(int i =0; i<answers.size(); i++) {
		  System.out.println(this.answers.get(i));
			}
	  
	 
	 
  }
  
  /**
   * Calculates popularity, based on number of answers, 
   * number of upvotes, and possibly the nanoTime divided by a number?
   * @return the popularity of the question
   */
  public int calculatePopularity() {
	  return answers.size() + this.getUpvotes();
  }
  
	
   /**
    * Prints the date in human-readable format
    * e.g: 2018-11-25 at 01:22:12 CET  
    */
	public String getFormattedDate() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm");  
		Date date = new Date(this.nanoTime);  
		return formatter.format(date); 
	}

  

  /**
   * Prints the Question and its answers, formatted
   */
  @Override public String toString() {
	  return this.question +  "\nPosted by: u/"+this.getAuthor()+ " on: " + this.getFormattedDate() + "\nUpVotes: "+this.getUpvotes()+"\n";  
  }
  
  /**
	 * Returns whether two Question objects Have the same question and author
	 */
	@Override
	public boolean equals(Object o) {
  	if (this == o) {
  		return true;
  	} else if ( o == null || !(o instanceof Question)) {
  		return false;
  	} else {
  		Question that = (Question) o;
  		if (this.author.equals(that.author) && this.question.equals(that.question)) {
  			return true;
  			}else {
  			return false;
  			}
  	}
  	}
	

	@Override
	public int compareTo(Question arg) {
		if (this == arg) {
			return 0;
		} 
		if (!(this.author.equals(arg.author))) {
			return this.question.compareTo(arg.question);
		}
		return this.author.compareTo(arg.author);
	}
	
	public int compareToSecondaryKey(Question arg) {
		if (this == arg) {
			return 0;
		} 
		if ((this.upVotes != arg.upVotes)){ 
			return arg.upVotes - this.upVotes;
		}
		return this.question.compareTo(arg.question);
	}
	
	public int compareToPrimaryKey(Question arg) {
		if (this == arg) {
			return 0;
		} 
		if (!(this.question.equals(arg.question))) {
			return this.question.compareTo(arg.question);
		}
		return this.author.compareTo(arg.author);
	}
	
  
  
	   /**
     * Returns a consistent hash code for 
     * each Question Object by summing the Unicode values
     * of each character in the key
     * key = question + author
     * @return the hash code
     */

    @Override public int hashCode() {
        String key = question;
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += (int) key.charAt(i);
        }
        return sum;
    }
}


  
  
  
  

  
  
  
  

