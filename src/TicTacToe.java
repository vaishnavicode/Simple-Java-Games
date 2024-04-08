import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public String win = "";
    boolean inserted = false, gameOver = false;

    //Declaring number of times player and computer are entering their choices
    static int countPlayer = 0, countComputer = 0;

    //Declaring static singly link list object
    static SinglyLL sll = new SinglyLL();

    public void playTicTacToe() {
        

        //Declaring the board and printing it
        char[][] board = {{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+','-'},{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+','-'}, {' ', '|', ' ', '|', ' '}};
        printBoard(board);

        try (//Declaring the scanner object
        Scanner sc = new Scanner(System.in)) {
            //Declaring gameOver boolean and inserted boolean
            

            //While game is not over, the loop would run indefinitely 
            while(!gameOver){

                //Checking if the game is draw
                if(countPlayer+countComputer == 9){
                    System.out.println("Draw :]");
                    gameOver = true;
                    break;
                }
                
                //Taking input from the user 
                System.out.println("Enter your placement (1-9): ");
                int pos = sc.nextInt();
                inserted = placePiece(board, givesString(pos), "Player");
                //The loop would continue until inserted method return true
                while(!inserted){
                    System.out.println("The spot is already taken, please enter again.");
                    System.out.println("Enter your placement (1-9): ");
                    pos = sc.nextInt();
                    inserted = placePiece(board, givesString(pos), "Player");
                    
                }

                //Adding the choice in our link list
                if(inserted){
                    sll.addLastPlayer(givesString(pos));
                    countPlayer++;

                    //Checking if either player have won
                    if(checkWinner().length()>0){
                        win = checkWinner();
                        System.out.println(win);

                        gameOver = true;
                        break;
                    }   
                }

                
                
                //Adding the computer input using Random
                Random rand = new Random();
                int cpuPos = rand.nextInt(9) + 1;
                inserted = placePiece(board, givesString(cpuPos), "CPU");
                //The loop would continue until inserted method return true
                while(!inserted){
                    cpuPos = rand.nextInt(9) + 1;
                    inserted = placePiece(board, givesString(cpuPos), "CPU");
                }
                if(inserted){
                    //Adding the computer choice in our link list
                    sll.addLastCPU(givesString(cpuPos));
                    countComputer++;
                    //Checking if either player have won
                    if(checkWinner().length()>0){
                        win = checkWinner();
                        System.out.println(win);

                        gameOver = true;
                        break;
                    }  

                }
                
                printBoard(board);


            }
        }
        
        if(gameOver){
            countComputer = 0;
            countPlayer = 0;
        }
        

    }

    public String winning() {
        return win;
    }
    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(" "+c+" ");
            }
            System.out.println();
        }
    }

    public static boolean placePiece(char[][] board, String pos, String user) {

        char symbol = ' ';

        if(user.equalsIgnoreCase("Player")) {
            symbol = 'X';
        } else {
            symbol = 'O';
            
        }

        switch (pos) {
            case "1":
                if(board[0][0] == ' '){
                    board[0][0] = symbol;
                    return true;
                }
                else{
                    return false;
                }
                
            case "2":
                if(board[0][2] == ' '){
                    board[0][2] = symbol;
                    return true;
                }
                else{
                    return false;
                }
                
            case "3":
                if(board[0][4] == ' '){
                    board[0][4] = symbol;
                    return true;
                }
                else{
                    return false;
                }
            case "4":
                if(board[2][0] == ' '){
                    board[2][0] = symbol;
                    return true;
                }
                else{
                    return false;
                }
            case "5":
                if(board[2][2] == ' '){
                    board[2][2] = symbol;
                    return true;

                }
                else{
                    return false;
                }
            case "6":
                if(board[2][4] == ' '){
                    board[2][4] = symbol;
                    return true;
                }
                else{
                    return false;
                }
            case "7":
                if(board[4][0] == ' '){
                    board[4][0] = symbol;
                    return true;
                }
                else{
                    return false;
                }
            case "8": 
                if(board[4][2] == ' '){
                    board[4][2] = symbol;
                    return true;
                }
                else{
                    return false;
                }
            case "9":
                if(board[4][4] == ' '){
                    board[4][4] = symbol;
                    return true;
                }
                else{
                    return false;
                }
        
            default:
                System.out.println("Invalid input!");
                break;
        }

        return false;

        
    }

    public static String checkWinner(){

        String cpuChoices = sll.getCpuChoices();
        String playerChoices = sll.getPlayerChoices();

        boolean cpuWon = contains(cpuChoices);
        boolean playerWon = contains(playerChoices);

        if(cpuWon){
            
            return "Player Lost :[";
        }
        else if(playerWon){
            return "Player Won :]";
            
        }
        else if(countPlayer+countComputer == 9){
            return "Draw :]";
        }

        return "";
       
    }

    public static boolean contains(String s) {
        boolean b123 = s.contains("1") && s.contains("2") && s.contains("3");
        boolean b456 = s.contains("4") && s.contains("5") && s.contains("6");
        boolean b789 = s.contains("7") && s.contains("8") && s.contains("9");
        boolean b147 = s.contains("1") && s.contains("4") && s.contains("7");
        boolean b258 = s.contains("2") && s.contains("5") && s.contains("8");
        boolean b369 = s.contains("3") && s.contains("5") && s.contains("9");
        boolean b159 = s.contains("1") && s.contains("5") && s.contains("9");
        boolean b357 = s.contains("3") && s.contains("5") && s.contains("7");

        return b123||b456||b789||b147||b258||b369||b159||b357;
        
    }

    public static String givesString(int i){
        String s = "";
        switch (i) {
            case 1:
                s = "1";
                break;
            case 2:
                s = "2";
                break;
            case 3:
                s = "3";
                break;
            case 4:
                s = "4";
                break;
            case 5:
                s = "5";
                break;
            case 6:
                s = "6";
                break;
            case 7:
                s = "7";
                break;
            case 8:
                s = "8";
                break;
            case 9:
                s = "9";
                break;
        
            default:
                break;
        }
        return s;
    }

    public String winnning() {
        return null;
    }
    
}

class SinglyLL 
{
    class Node
    {
        String data;
        Node link = null;
        Node(String data)
        {
            this.data = data;
        }
    }

    Node firstPlayer = null;
    Node firstComputer = null;

    void addLastPlayer(String data){
        Node newNode = new Node(data);
        if(firstPlayer==null)
        {
            firstPlayer = newNode;
        }
        else
        {
            Node temp = firstPlayer;
            while(temp.link!=null)
            {
            temp = temp.link;
            }
            temp.link = newNode;
        }
    }
    
    void addLastCPU(String data){
        Node newNode = new Node(data);
        if(firstComputer==null){

            firstComputer = newNode;
        }
        else{

            Node temp = firstComputer;
            while(temp.link!=null){
                temp = temp.link;
            }
            temp.link = newNode;
        }
    }

    String getPlayerChoices(){
        Node temp = firstPlayer;
        String s = "";
        while(temp!=null)
        {
            s = s + temp.data;
            temp = temp.link;
        }
        return s;
    }
    String getCpuChoices(){
        Node temp = firstComputer;
        String s = "";
        while(temp!=null)
        {
            s = s + temp.data;
            temp = temp.link;
        }
        return s;
    }


    
}
    
