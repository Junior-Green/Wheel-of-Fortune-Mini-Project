/*Junior Green
 * Mr. Bulhao
 * ICS3U1-02
 * 5 May 2019
 */
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class WoF {

	public static void main(String[] args) {
		
		 String wordSolve = "", letterGuess = "", wordGuess = "", playAgain = ""; //Declare and initialize String objects
		 int charCount = 0,choice = 0, money = 0, value = 0; //Declare and initialize integer variables
		 
		 DecimalFormat df = new DecimalFormat("$,##0"); //Creates an object from DecimalFormat class (for formatting money)
		 Scanner sc = new Scanner(System.in); //Creates an object from the Scanner class (used for getting inputs)
		 Random rnd = new Random(); //Creates on object from the Random class (used for picking category and secret word)
		 
		 ArrayList<String> lettersPicked = new ArrayList<String>(); //Declares a String ArrayList
		 String[] vowels = {"A","I","E","O","U"}; //Declares and initializes and Array that stores all vowels
		 String[] categories = {"Movies", "Movies", "Food", "Food", "Games", "Games", "TV Shows", "TV Shows", "Drinks", "Drinks"}; //Declares and initializes and Array that stores all categories
		 String[] secretWordArray = {"END GAME", "INFINITY WAR", "POUTINE", "FRENCH TOAST", "FORTNITE", "MARIO KART", "BREAKING BAD", "GAME OF THRONES", "GATORADE", "COCA COLA"}; //Declares and initializes and Array that stores secret words
		 int[] points = {0,150,200,250,300,400,450,500,600,700,750,800, 900, 1000};
		 
		 int pick = rnd.nextInt(10); //Randomly picks a number from 0-10 and stores it in an int variable
		 
		 String category = categories[pick]; //Picks the category
		 String secretWord = secretWordArray[pick]; //Picks the secret word
		 
		 boolean end = true, endGame = true; //Declare and initialize boolean variables
		 
		 //Do-while loop that controls the whole game
		 do
		 {
			 for (int counter = 0; counter < secretWord.length(); counter++) //Adds dashes to wordSolve variable depending on how much characters there are in the secret word
			 {	
				 if(Character.isLetter(secretWord.charAt(counter)))
				 wordSolve += "-";
				 else 
				 wordSolve += " ";
			 }
			 String tempWordSolve = wordSolve;	
			
			 do //Controls every turn in the game
			 {
				 //Outputs the category and word every turn
				 System.out.printf("%n%-15s%-15s%n", "Category:", category);
				 System.out.printf("%-15s%-15s", "Secret Word:", wordSolve);
				 System.out.printf("%n%n%-15s%-15s%-15s", "1 - Spin the Wheel", "\n2 - Buy a Vowel", "\n3 - Solve the Word");
				 
				 do//Asks to choose 1,2 or 3
				 {
					 try //Tries to get their input
					 {
						 System.out.print("\n\nEnter your selection:\t");
						 choice = sc.nextInt();
						 end = false;
					 }
					 catch(InputMismatchException e) //Catches error if users does not put a digit and prompts them to try again
					 {
						 System.out.print("\nError! You must enter an integer (1-3)");
						 end = true;
						 sc.next();
						 continue;
					 }
					 if (choice < 1 || choice > 3) //If user inputs number and is not between 1-3 prompts the user to try again
					 {
						 System.out.println("\nInteger must be between 1-3. Try again...");
						 end = true; 
					 }
				 }
				 while (end == true);
				 end = true;
				 System.out.println("\n--------------------------------------------------------------------------"); //Separator
				 
				 if (choice == 1) //Option 1 (Spin the wheel)
				 {
					 value = points[rnd.nextInt(14)]; //Randomly picks value number
					 if (value == 0) //If user lands on bankrupt sets money to 0 
					 {
						 money = 0;
						 System.out.printf("%n%-15s", "Oh no! You landed on bankrupt. You lost all your money");
					 }
					 else //Outputs what they spun
					 {
						 System.out.printf("%n%-15s", "You landed on " + df.format(value) + "!");
					 }
					 while (end == true) 
					 {
						 do //Loop that controls the validity of their guessed letter
						 {
							 //Prompts user to input their guessed letter and stores it in variable letterGuess
							 System.out.printf("%n%-15s%-15s", "Enter a letter:", "");
							 letterGuess = sc.next();
							 letterGuess = letterGuess.toUpperCase();
								
							 if ((letterGuess.length() > 1) || !Character.isLetter(letterGuess.charAt(0))) //If the input is not "one letter" prompts user to input letter again
							 {
								System.out.print("\nPlease enter a single letter.");
								end = true;
							 }
							 else if (lettersPicked.contains(letterGuess)) //If the letter was already guessed prompts user to input letter again
							 {
								 System.out.println("\nYou already guessed this letter please try again...");
								 end = true;
								 break;
							 }
							 else //If input passes the preceding ifs check to see if the input is a vowel. If so prompts user to try again
							 {
								 for (int counter = 0; counter < 5; counter++) //Does a linear check through array that contains vowels
								 {
									 if (!(vowels[counter].equals(letterGuess)))
									 {
										 end = false;		 
									 }
									 else //If the letter is a vowel makes the user input it again
									 {
										 end = true;
										 System.out.println("You must enter a  consonant! Please try again...");
										 break;
											 
									 }
								 }
									
							 }								 
						 }while(end == true);
					 }
					 lettersPicked.add(letterGuess); //Adds the successfully guessed letter to the array list
					 wordSolve = "";
					 
					 for (int counter = 0; counter < secretWord.length(); counter++) //For loop that outputs dashes with letters that were guessed correctly
					 {
						
						 if (letterGuess.equalsIgnoreCase(Character.toString(secretWord.charAt(counter))))
						 {	
							 
							 charCount += 1; //Counts how much times letter is in the secretWord
							 wordSolve += "" + letterGuess; //Adds the guessed letter \|mfc2 
						 } 
						 else if (!(letterGuess.equalsIgnoreCase(Character.toString(secretWord.charAt(counter)))) && Character.isLetter(secretWord.charAt(counter)))
						 {
							 wordSolve += "" + tempWordSolve.charAt(counter);
						 }
						 else 
						 wordSolve += " ";
					 }
					 tempWordSolve = wordSolve; //Stores it in a temporary variable
					 
					 if (charCount == 0) //If letter was incorrect
					 {
						 System.out.printf("%n%-10s\n%-15s%n", "Sorry...the letter " + letterGuess + " is not in the word!","You have " + df.format(money));
					 }
					 else //If letter was guessed correctly
					 {
						 System.out.printf("%n%-10s%-15s%n", "There are " + charCount + " ",letterGuess + "'s. You win " + df.format((charCount * value)));
						 money += charCount * value;
					 }
					 
					//Resets all variables
					value = 0;
					letterGuess = "";
					charCount = 0;
					end = true;
				 }
				if (choice == 2) //Option 2 (Buy a Vowel)
				{
						if (money >= 250) //If user has enough money deduct 250 and proceed with letterGuess procedure
						{
							money -= 250;
							 while (end == true)
							 {
								 do //Same procedure has option 1 but check for vowel
								 {
									 System.out.printf("%n%-15s%-15s", "Enter a vowel:", "");
									 letterGuess = sc.next();
									 letterGuess = letterGuess.toUpperCase();
										
									 if (letterGuess.length() > 1)
									 {
										System.out.print("\nPlease enter a single letter.");
										end = true;
									 }
									 else
									 {
										 for (int counter = 0; counter < 5; counter++)
										 {
											 if ((vowels[counter].equals(letterGuess)))
											 {
												 end = false;
												 break;
												 
											 }
											 else if (lettersPicked.contains(letterGuess))
											 {
												 System.out.println("\nYou already guessed this letter please try again...");
												 end = true;
												 break;
											 }
											 else
											 {
												 if(counter == 4)
												 {
													 System.out.println("\nYou must enter a vowel! Please try again...");
													 end = true;
												 }
											 }
										  }	
									  }								 
								 }while(end == true);
							 }
							 
							 lettersPicked.add(letterGuess);
							 end = true;
							 wordSolve = "";
							 
							 for (int counter = 0; counter < secretWord.length(); counter++) //Same as option 1
							 {
								
								 if (letterGuess.equalsIgnoreCase(Character.toString(secretWord.charAt(counter))))
								 {	
									 
									 charCount += 1; 
									wordSolve += "" + letterGuess;
								 }
								 else if (!(letterGuess.equalsIgnoreCase(Character.toString(secretWord.charAt(counter)))) && Character.isLetter(secretWord.charAt(counter)))
								 {
									 wordSolve += "" + tempWordSolve.charAt(counter);
								 }
								 else 
								 wordSolve += " ";
							 }
							 tempWordSolve = wordSolve;
							 
							 if (charCount == 0) //Same as option 1
							 {
								 System.out.printf("%n%-10s\n%-15s%n", "Sorry...the letter " + letterGuess + " is not in the word!","You have " + df.format(money));
							 }
							 else //Same as option 1 
							 {
								 System.out.printf("%n%-10s%-15s%n", "There are " + charCount + " ",letterGuess + "'s. That cost you $250! \nYou have " + df.format(money) + "!");
							 }
							//Resets all variables
							value = 0;
							letterGuess = "";
							charCount = 0;
						} 
						else //If user doesn't have enough money
						System.out.println("You only have " + df.format(money) + " you need $250 to purchase a vowel.");
						end = true;
				}
				if (choice == 3) //Option 3 (Guess Word)
				{
					//Prompts user to guess word and stores it in variable called wordGuess
					System.out.printf("%-15s%-15s", "Enter the secret word:", "");
					sc.nextLine();
					wordGuess = sc.nextLine();
					wordGuess = wordGuess.toUpperCase();
					
					if (wordGuess.equalsIgnoreCase(secretWord)) //If user guesses word correctly congratulates them and asks if user wants to play again
					{
						System.out.println("Congratulations... " + wordGuess + " is correct!\nYou're going home with " + df.format(money));
						System.out.printf("%n%-15s%-15s", "Would you like to play again: (Y or N)", "");
						playAgain = sc.next();
						
						do //Checks if they enter Y or N
						{
							if (playAgain.equalsIgnoreCase("Y")) //If Y then reset all variables and picks new category and secret word 
							{	
								end = false;
								endGame = true;
								value = 0;
								money = 0;
								pick = rnd.nextInt(10);
								category = categories[pick];
								secretWord = secretWordArray[pick];
								wordSolve = "";
					
							}
							else if (playAgain.equalsIgnoreCase("N")) //If no then terminate program
							{
								
								endGame = false;
								end = false;
								System.out.print("\nThank you for playing Wheel of Fortune!");
								break;
							}
							else //If user does not put in Y or N then prompts user to input their choice again
							{
								System.out.print("\nPlease select Y or N...\t");
								playAgain = sc.next();
								end = true;
							}
						}
						while(end == true);
					}
					else //If user does not correctly guess the word
					{
						System.out.println("Sorry... " + wordGuess + " is incorrect!\nThe secret word is " + secretWord);
						System.out.printf("%n%-15s%-15s", "Would you like to play again: (Y or N)", "");
						playAgain = sc.nextLine();
						
						do
						{
							if (playAgain.equalsIgnoreCase("Y")) //Same as above
							{
								end = false;
								endGame = true;
								value = 0;
								money = 0;
								pick = rnd.nextInt(10);
								category = categories[pick];
								secretWord = secretWordArray[pick];
								wordSolve = "";
					
							}
							else if (playAgain.equalsIgnoreCase("N")) //Same as above
							{
								
								endGame = false;
								end = false;
								System.out.println("\nThank you for playing Wheel of Fortune!");
								break;
							}
							else
							{
								System.out.print("\nPlease select Y or N...\t");
								playAgain = sc.next();
								end = true;
							}
						}
						while(end == true);
					}		
				}
			 }
			 while(end == true);
			 end = true;
		 }
		 while(endGame == true);
				 
		 sc.close();
	}

}





