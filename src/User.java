import java.util.ArrayList;

public class User {
  private String username; 
  private String password;
  
  public User (String username, String password) {
	  this.username = username; 
	  this.password = password; 
  }
  
  public String getUserName() {
	  return this.username; 
  }
  
  public String getPassword() {
	  return this.password; 
  }
  
  public void setUserName(String username) {
	  this.username = username; 
  }
  
  public void setPassword (String password) {
	  this.password = password; 
  }
  /**
   * Verifies that password equals the
   * password on file for the user
   * @param userInput the password entered
   * @return whether the password entered matches
   * the password stored
   */
  public boolean verifyPassword(String userInput) {
      return userInput.equals(password);
  }
  @Override public String toString() {
	  return this.username + "\n" + this.password + "\n"; 
  }
  /**
	 * Searches for a user whose username and password match those currently stored
	 * in the users ArrayList
	 * 
	 * @param email the email that was input
	 * @param password the password input
	 * @param users the ArrayList storing customers on file
	 * @return the location of the user or -1 if not found
	 */
	public static int binarySearch(String userName, String password, ArrayList<User> users) {
		int low = 0;
		int high = users.size() - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (users.get(mid).getUserName().compareTo(userName) == 0 && users.get(mid).verifyPassword(password)) {
				return mid;
			} else if (users.get(mid).getUserName().compareTo(userName) > 0) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}
	

	/**
	 * Sorts an ArrayList of User objects alphabetically according to email address,
	 * using the bubbleSort algorithm
	 * 
	 * @param users the ArrayList of Users
	 */
	public static void bubblesort(ArrayList<User> users) {
		for (int i = 0; i < users.size(); i++) {
			for (int j = 0; j < users.size() - 1; j++) {
				if (users.get(j).getUserName().compareTo(users.get(j + 1).getUserName()) > 0) {
					User temp = users.get(j);
					users.set(j, users.get(j + 1));
					users.set(j + 1, temp);
				}
			}
		}
	}
  
}