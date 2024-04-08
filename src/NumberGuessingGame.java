import java.util.Random;
import java.util.Scanner;

class NumberGuessingGame {
    public String win = "";
    public  void guessNumber() {
        try (Scanner sc = new Scanner(System.in)) {
            Random random = new Random();
            
            int secretNumber = random.nextInt(100) + 1; 
            int attempts = 10;
            
            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("You have " + attempts + " attempts to guess the number.");
            
            while (attempts > 0) {
                System.out.print("Enter your guess (between 1 and 100): ");
                int userGuess = sc.nextInt();
                
                if (userGuess < 1 || userGuess > 100) {
                    System.out.println("Invalid input. Please enter a number between 1 and 100.");
                    continue;
                }
                
                if (userGuess == secretNumber) {
                    System.out.println("Congratulations! You've guessed the number.");
                    win = "Player Won";

                    break;
                } else {
                    String hint = userGuess < secretNumber ? "higher" : "lower";
                    System.out.println("Incorrect. Try a " + hint + " number.");
                    int difference = Math.abs(secretNumber - userGuess);
                    if (difference <= 10) {
                        System.out.println("You're very close!");
                    } else if (difference <= 20) {
                        System.out.println("You're getting warmer.");
                    }
                }
                
                attempts--;
                System.out.println("Attempts remaining: " + attempts);
            }
            
            if (attempts == 0) {
                System.out.println("Sorry, you've run out of attempts. The number was: " + secretNumber);
                win = "Player Lost";
            }
        }
        
        
    }
    public String winning() {
        return win;
    }
}
