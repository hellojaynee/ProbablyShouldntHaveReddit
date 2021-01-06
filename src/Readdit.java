import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Readdit {
	public static void main(String[] args) throws IOException {

		
		// array list holding all of current users
		ArrayList<User> currentUsers = new ArrayList<User>();
		List<String> UserCheck = new List<>();
		
		// read in a list of usernames and passwords
		File userFile = new File("UserNames.txt");
		Scanner userInFile = new Scanner(userFile);
		do {
			String user = userInFile.nextLine();
			UserCheck.addLast(user);
			String password = userInFile.nextLine();
			User newUser = new User(user, password);

			currentUsers.add(newUser);

		} while (userInFile.hasNextLine());

		// read in the Questions
		// read in the answers


		HashTable ht = new HashTable(50);
		BST bst1 = new BST();
		BST2 bst2 = new BST2();

		// reconnect the user database
		File questionFile = new File("QuestionsAndAnswers.txt");
		Scanner QandA = new Scanner(questionFile);
		// read in the question and answer file

		String type = QandA.nextLine();

		do {
			String question = QandA.nextLine();
			String username = QandA.nextLine();
			Long nanoTime = QandA.nextLong();
			int upvotes = QandA.nextInt();
			Question newQuestion = new Question(question, username, nanoTime, upvotes);
			if (QandA.hasNextLine()) {
				QandA.nextLine();
				if (QandA.hasNextLine()) {
					type = QandA.nextLine();
				}
			}

			while (type.equals("-2") && QandA.hasNextLine()) {
				String answer = QandA.nextLine();
				String answeruser = QandA.nextLine();
				Long answernano = QandA.nextLong();
				newQuestion.addAnswer(answer, answeruser, answernano);
				if (QandA.hasNextLine()) {
					QandA.nextLine();
					if (QandA.hasNextLine()) {
						type = QandA.nextLine();
					}
				}
			}
			
			ht.insert(newQuestion);
			bst1.insert(newQuestion);
			bst2.insert(newQuestion);

		} while (QandA.hasNextLine());

		System.out.print("\nWelcome to 'ProbablyShouldn'tRedditAtWork'!\n");
		System.out.print("\nIf you have an account you knwo what to do\n" +"\nRemember you only get 4 password tries!\n" 
		+ "\nIf no account, type in a new username and create an account\n" );
		
		int a = 100; // dummy variable
		String activeUser = "";
		int count = 4;
		while (a != 0) {
			if(count == 0) {
				System.out.println("\nAsta La Vista Baby!\n");
				System.exit(0);
			}else if(count == 1) {
				System.out.println("\nlast try bud... i mean it.\n");
			}else if(count == 2) {
				System.out.println("\nare you kidding me?\n");
			}else if(count == 3) {
				System.out.println("\nTry not to mess it up this time\n");
			}else if(count == 4) {
				
			}
			Scanner input = new Scanner(System.in);

			System.out.print("\nEnter your user name: ");

			String user = input.nextLine();
			System.out.print("Enter your password: ");
			String password = input.nextLine();

			User.bubblesort(currentUsers);
			int location;
			location = User.binarySearch(user, password, currentUsers);

			boolean correctPassword = false;
			if (location != -1) {
				correctPassword = currentUsers.get(location).verifyPassword(password);
			}
			// if user exists
			if (UserCheck.linearSearch(user) != -1) {
				// if user exists and password is correct
				if (location != -1 && correctPassword == true) {

					if (correctPassword == true) {
						System.out.println("\nHello, " + currentUsers.get(location).getUserName()+"! Let's start procrastinating.");
						a = 0;
					}
					activeUser = currentUsers.get(location).getUserName();
					

				} // if user exists and password is incorrect
				else {
					if(count == 1) {
					System.out.println("\nDid I not warn you, " + user + "?.");
					count = 0;
					}else {
						System.out.println("\nThe account " + user + " exists but incorrect password.\nPlease Try Again.");
						count--;
					}
				}
			}
			// if username does not exist
			if (UserCheck.linearSearch(user) == -1) {
				System.out.println("\nSorry! It looks like we don't have that account on file.");
				System.out.println("Make an account below - you'll be avoiding work/real life in no time!");
				System.out.print("\nEnter a username: ");
				String userName = input.nextLine();
				System.out.print("Enter a password: ");
				String password1 = input.next();
				
				User newUser1 = new User(userName, password1);
				currentUsers.add(newUser1);
				System.out.println("\nThank you, " + newUser1.getUserName()+ "! Your account has been created.\n");
				activeUser = newUser1.getUserName();
				
				a = 0;
			}		
		}
		
		Scanner input = new Scanner(System.in);
		

		int dummy = 5;
		int dummy1 = 5;
		int dummy2 = 5;
		int count2 = 4;
		while (dummy != 0) {
			if(count2 == 0) {
				System.out.println("\nAre yo done yet??????");
			
			}else if(count2 == 1) {
				System.out.println("\nummm youve been here already....\n");
			}else if(count2 == 2) {
				
				System.out.println("\nYay once more...\n");
			}else if(count2 == 3) {
				
				System.out.println("\nYay we are back...\n");
			}else if(count2 == 4) {
				
			}else {
				System.out.println("\ns(x.x)J ZZzzZZzzZZzzZZ.....");
				count2 = 4;
			}
			System.out.print("\nSelect from the following option numbers below:\n\n" + "1. * POST *\ta new question to the community!\n"
					+ "2. * DELETE *\tyour posted questions\n" + "3. * SEARCH *\tfor a question \n" + "4. * BROWSE *\texisting question database\n" + "5. * PRINT *\tall questions and answers to file\n" + "6. * LOGOUT *   and exit\n");
			int choice;
			choice = input.nextInt();
			if (choice == 1) {
				// Add a question
				count2--;
				System.out.println("Enter your question: ");
				
				String question;
				input.nextLine();
				question = input.nextLine();
			
				Question newQuestion = new Question(question, activeUser);
			
				ht.insert(newQuestion);
				bst1.insert(newQuestion);
				bst2.insert(newQuestion);
				
				System.out.println( "'" + question + "' was posted successfully by u/" + activeUser + " on: " + newQuestion.getFormattedDate() );
			

			} else if (choice == 2) {
				count2 --;
			// clear scanner 
				input.nextLine();
			//Delete question
				
			System.out.println(activeUser + "'s posts on 'ProbablyShouldn'tRedditAtWork': ");
			// this might return nothing, error check add
			System.out.println();
			ht.authorPrint(activeUser);
			
			
			System.out.println("Enter the text of the question to delete: ");
			
			String questionRemove = input.nextLine();
			
			try {
			
			Question questionToRemove = new Question (questionRemove, activeUser);
			bst2.remove(ht.findQuestion(questionToRemove));
			ht.remove(questionToRemove);
			bst1.remove(questionToRemove);
			
			System.out.println("The question '" + questionRemove + "' has been successfully deleted from the community.");
			
			} catch (NullPointerException e) {
				System.out.print("You don't have any questions posted to the database! Nothing to delete.\n");
			}


			} else if (choice == 3) {
				// Search -- sub-menu
				// - Find and display one element using the primary key
				// - Find and display elements using the secondary key (all matches)
				count2--;
				while (dummy1 != 0) {
				System.out.println("\nSelect from the following option numbers below:\n" + "\n1. * SEARCH BY QUESTION *\tSearch by question text and browse answers \n" + "2. * SEARCH BY UPVOTES *\tSearch by # of community UpVotes see matching posts \n3. * GO BACK *\t\t\tto Main Menu");
				
				int choice1 = input.nextInt();
				input.nextLine();
					if (choice1 == 1) {
						count2--;
						// search the question database, find the match
						// If the question is found -- printout the relevant answers
						// If the question is not found -- add the question?/or discard it and say
						// question is not found
						System.out.print("Enter text of question to see its answers: ");
						
						String searchQ = input.nextLine();
						
						Question dummyQ = new Question(searchQ);
						int search = ht.search(dummyQ);
						if (search == -1) {
							System.out.println("Sorry! " + searchQ + ":"+ " is not in the database.");
							
						
						} else {
							ht.findQuestion(dummyQ).printAnswers();
							
						}
					} else if (choice1 == 2) {
						System.out.print("Enter a number of UpVotes, and we'll show you the post: ");
						//Might do more with this later
						//System.out.println(bst2.findMin());//the max
						//System.out.println(bst2.findMax());//the min
						int votes;
						votes = input.nextInt();
						System.out.println();
						ht.upVotePrint(votes);
						
					}
					else if (choice1 ==3) {
						dummy1 =0;
					}
				}
			}
					else if (choice == 4) {
						count2--;
						// List --- sub-menu
						while (dummy2 != 0) {
						System.out.println("\nSelect from the following browsing options below:\n\n" + "1. * UN-SORTED *\tQuestions (aka 'I'm Feeling Lucky')\n"
								+ "2. * ALPHA *\t\t->Betically Sorted Questions\n" + "3. * HOT *\t\tQuestions! (Sorted by community #UpVotes)\n"
								+ "4. * GO BACK *\t\tto Main Menu");
						int choice2 = input.nextInt();
						if (choice2 == 4) {
							dummy2 =0;
						}
							if (choice2 == 1) {
								// printout question in unsorted manner (HashTable)
								ht.unsortedPrint();
								
							} else if (choice2 == 2) {
								// printout questions in sorted manner based on primary key (BST1)
								bst1.inOrderPrint();
							} else if (choice2 == 3) {
								// printout questions in sorted manner based on upvotes (BST2)
								bst2.inOrderPrint();
								
							} else if (choice2 == 4) {
								dummy2 = 0;

							} else {
								System.out.println("Invalid Entry! Choose from options 1-4.");
								choice2 = input.nextInt();
							}
						}
						dummy2=5;

					} else if (choice == 5) {
						count2--;
						System.out.println("Printing current Questions and Answers to the file...");
						//PrintWriter output = new PrintWriter(questionFile);
						// for output later
						PrintWriter output1 = new PrintWriter(questionFile);
						output1.print(ht.outputPrint());
						System.out.println("** Data has been recorded! **");

					} else if (choice == 6){
						dummy = 0;
						System.out.println("Good-Bye! Get back to work!");
					}else{
						System.out.println("Invalid Entry! Choose from options 1-6.");
						dummy = 5;
									
					}
		}
		// Closing Scanners
				//PrintWriter output = new PrintWriter(questionFile);
				PrintWriter output = new PrintWriter(questionFile);
				output.print(ht.outputPrint());
				
				
				File file = new File("UserNames.txt");
				PrintWriter outUsers = new PrintWriter(file);
				for(int i =0; i<currentUsers.size(); i++) {
				 outUsers.print(currentUsers.get(i));
				}
				
				
				output.close();
				outUsers.close();
				input.close();
				userInFile.close();
				QandA.close();
	}
}