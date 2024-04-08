import java.util.*;

class Games {

    Scanner sc = new Scanner(System.in);
    static String Won = "";
    static String game = "";

    

    public void playGames() {
        
        while(true){
            System.out.println("Which game do you wish to play : \n1. Gladiator Game\n2. Wordle\n3. Number Guessing\n4. Maze Runner\n5. TicTacToe\n6. Exit");
            int choice =sc.nextInt();
            if(choice==5){
                break;
            }
            switch(choice){
                case 1:
                GladiatorGame g = new GladiatorGame();
                g.gladgame();
                Won = g.winning();
                game = "Gladiator Game";
                break;
                case 2:
                WordGuessingGame w = new WordGuessingGame();
                w.guessword();
                Won = w.winning();
                game = "Wordle";
                break;
                case 3:
                NumberGuessingGame n = new NumberGuessingGame();
                n.guessNumber();
                Won = n.winning();
                break;
                case 4:
                MazeSolver m = new MazeSolver();
                m.solvemaze();
                Won = m.winning();
                game = "Maze Solver";
                break;
                case 5:

                TicTacToe t = new TicTacToe();
                t.playTicTacToe();
                Won = t.winnning();
                break;
                default:
                System.out.println("Invalid index.");
                break;
                }
            }

    }
    
    public String wining() {

        return Won;
    }
    public String gamePlayed() {
        return game;
    }
}


