	import java.util.Scanner;
	import java.util.Random;
public class numgame {




	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        Random random = new Random();
	        boolean playAgain = true;

	        System.out.println("Welcome to the Guess the Number Game!");

	        while (playAgain) {
	            int randomNumber = random.nextInt(100) + 1;
	            int attempts = 0;
	            int maxAttempts = 10;
	            boolean guessedCorrectly = false;

	            System.out.println("I have generated a number between 1 and 100. Can you guess it?");
	            System.out.println("You have " + maxAttempts + " attempts to guess the number.");

	            while (attempts < maxAttempts && !guessedCorrectly) {
	                System.out.print("Enter your guess: ");
	                int userGuess = scanner.nextInt();
	                attempts++;

	                if (userGuess == randomNumber) {
	                    guessedCorrectly = true;
	                    System.out.println("Congratulations! You guessed the number correctly in " + attempts + " attempts.");
	                } else if (userGuess > randomNumber) {
	                    System.out.println("Your guess is too high. Try again.");
	                } else {
	                    System.out.println("Your guess is too low. Try again.");
	                }
	            }

	            if (!guessedCorrectly) {
	                System.out.println("Sorry, you've used all your attempts. The number was: " + randomNumber);
	            }

	            System.out.print("Do you want to play again? (yes/no): ");
	            String response = scanner.next();
	            playAgain = response.equalsIgnoreCase("yes");
	        }

	        System.out.println("Thank you for playing the Guess the Number Game. Goodbye!");
	        scanner.close();
	    }
	}


