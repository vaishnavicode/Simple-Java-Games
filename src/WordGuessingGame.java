import java.util.Random;
import java.util.Scanner;

class WordGuessingGame {

    public String win = "";
    static String[] wordList = {
        "apple", "banana", "cherry", "orange", "grape", 
        "strawberry", "blueberry", "kiwi", "mango", "pineapple", 
        "peach", "pear", "plum", "watermelon", "lemon"
    };
    
    public void guessword() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Welcome to the Word Guessing Game!");

        System.out.println();
        System.out.println("Enter the number of words you want to guess (Max words are 15) :");
        System.out.println();
        
        int wordguess = sc.nextInt();

        if(wordguess>15){
            while(wordguess>15){
                System.out.println("Set the number of words again");

                wordguess=sc.nextInt();
            }
        }
        
        for (int i = 0; i < wordguess; i++) {
            playRound();
            
            if (i < wordguess - 1) {
                System.out.println("\nNext word!\n");
            }
        }
        
        sc.close();
    }
    
    void playRound() {
        String win = "";
        Random r1 = new Random();
        String selectedWord = wordList[r1.nextInt(wordList.length)];
        char[] Guessword = new char[selectedWord.length()];
        
        for (int i = 0; i < Guessword.length; i++) {
            Guessword[i] = '_';
        }
        
        int attempts = 6; // Change this to set the number of attempts
        
        
        try (Scanner sc = new Scanner(System.in)) {
            while (attempts > 0) {
                System.out.println();
                System.out.println("Word: " + String.valueOf(Guessword));
                System.out.println("Attempts left: " + attempts);
                System.out.print("Guess a letter: ");
                char guess = sc.next().charAt(0);
                
                boolean correctGuess = false;
                
                for (int i = 0; i < selectedWord.length(); i++) {
                    if (selectedWord.charAt(i) == guess) {
                        Guessword[i] = guess;
                        correctGuess = true;
                    }
                }
                
                
                if (String.valueOf(Guessword).equals(selectedWord)) {

                    System.out.println("\nCongratulations! "+win+" You guessed the word: " + selectedWord);
                    
                    return;
                }
                else{
                    win = "Player Lost";
                }

                if (!correctGuess) {
                    attempts--;
                }

            }
        }
        
        System.out.println("\nSorry, you ran out of attempts. The word was: " + selectedWord);
    }

    public String winning(String w) {
        return w;
    }
}
