import java.util.Scanner;

class MazeSolver {
    private static char[][] maze = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', 'S', ' ', ' ', ' ', '#', ' ', ' ', '#'},
            {'#', '#', '#', '#', ' ', '#', ' ', '#', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', '#', '#', '#', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'E'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#'}
    };

    private static int currentRow = 1; 
    private static int currentCol = 1; 
    public String win = "";

    public static void printMaze() {
        for (char[] row : maze) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void move(String direction) {
        int newRow = currentRow;
        int newCol = currentCol;

        switch (direction) {
            case "up":
                newRow = currentRow - 1;
                break;
            case "down":
                newRow = currentRow + 1;
                break;
            case "left":
                newCol = currentCol - 1;
                break;
            case "right":
                newCol = currentCol + 1;
                break;
            default:
                System.out.println("Invalid direction.");
                return;
        }

        if (maze[newRow][newCol] != '#') {
            maze[currentRow][currentCol] = ' ';
            currentRow = newRow;
            currentCol = newCol;
            maze[currentRow][currentCol] = 'S';
        }
    }

    public void solvemaze() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                printMaze();

                if (currentRow == 5 && currentCol == 8) {
                    System.out.println("Congratulations! You've reached the exit.");
                    win = "Player Won";
                    break;
                }

                System.out.print("Enter direction (up/down/left/right): ");
                String direction = scanner.nextLine();

                move(direction);
            }
        }

    }

    public String winning() {
        return win;
    }

    
}
