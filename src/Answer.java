
import java.text.SimpleDateFormat;
import java.util.Date;

public class Answer {
	private String answer; 
	private long nanoTime; 
	private String author; 
	
	Answer(String answer, String author){
		this.answer = answer; 
		this.author = author; 
		this.nanoTime = System.currentTimeMillis();
	}
	Answer(String answer, String author, long nanoTime) {
		this(answer, author);
		this.nanoTime = nanoTime;
	}
	public String getAnswer() {
		return this.answer;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public long getNanoTime() {
		return this.nanoTime;
	}
	
	public void printFormattedDate() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");  
		Date date = new Date(System.currentTimeMillis());  
		System.out.println(formatter.format(date)); 
	}
	
@Override public String toString() {
		  return this.answer;  
	  }
}
