import java.util.*;

public class TTT {
    static int sz = 3;
    public static void main(String[] args) {
        System.out.println("Players:");
        System.out.println("  X -> Vishal");
        System.out.println("  O -> Karthik");
        System.out.println();
        System.out.println("How to play:");
        System.out.println("  - Enter your move as: row column");
        System.out.println("  - Both row and column must be 0, 1, or 2 (for the 3x3 board).");
        System.out.println("  - Example: '0 1' places your mark in the first row, second column.");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        boolean play = true;

        // Initialize an empty board. ' ' (space) denotes an empty cell.
        while(play){
            char[][] board = new char[sz][sz];
            for(int i = 0; i<sz; i++){
                for(int j = 0; j < sz; j++){
                    board[i][j] = ' ';
                }
            }

             // Start with player 'X' as current player (Vishal).
            char CP = 'X';
            boolean finish = false;

            // Main game loop - runs until someone wins or it's a draw.
            while(!finish){
                print(board);
                String s;
                if(CP=='X') s = "Vishal";
                else s = "Karthik";
                System.out.print(s + "'s turn to play : ");

                int row = -1, col = -1;
                 // Read and validate input in a loop until a valid move is entered.
                while(true){
                    String line = scanner.nextLine();
                    line.trim();
                    String[] parts = line.split("\\s+");
                    if(parts.length!=2){
                        System.out.print("Invalid input. Enter two numbers between 0 and 2 separated by space: ");
                        continue;
                    }
                    try{
                        row = Integer.parseInt(parts[0]);
                        col = Integer.parseInt(parts[1]);
                    }catch(NumberFormatException e){
                        System.out.print("Invalid numbers. Try again: ");
                        continue;
                    }
                    if(row < 0||row >= sz||col < 0||col >= sz){
                        System.out.print("Out of bounds. Row and column must be 0, 1, or 2: ");
                        continue;
                    }
                    if(board[row][col]!=' '){
                        System.out.print("Cell already occupied. Choose another cell: ");
                        continue;
                    }
                    break;
                }

                // Place the current player's mark on the board.
                board[row][col] = CP;

                if(won(board, CP)){
                    print(board);
                    String st;
                    if(CP=='X') st = "Vishal";
                    else st = "Karthik";
                    System.out.println(st+" wins!");
                    finish = true;
                }else if(isFull(board)){
                    print(board);
                    System.out.println("It's a draw!");
                    finish = true;
                }else
                    CP = CP=='X' ? 'O' : 'X';
                
            }

            // Ask whether players want another round.
            System.out.print("Do you want to play again? (y/n): ");
            String res = scanner.nextLine();
            res.trim();
            while(!res.equalsIgnoreCase("y") && !res.equalsIgnoreCase("n")){
                System.out.print("Please enter 'y' or 'n': ");
                res = scanner.nextLine();
                res.trim();
            }
            play = res.equalsIgnoreCase("y");
            System.out.println();
        }

        System.out.println("Thanks for playing! Goodbye.");
    }

    //Prints the current board to the console.
    private static void print(char[][] board) {
        System.out.println("Current Board");
        System.out.println("  0 1 2");
        for (int i = 0; i < sz; i++) {
            System.out.print(i + " ");
            for(int j = 0; j<sz; j++){
                char c = (board[i][j] == ' ') ? ' ' : board[i][j];
                System.out.print(c);
                if(j < sz - 1){
                    System.out.print("|");
                }
            }
            System.out.println();
            if(i < sz - 1){
                System.out.println("  -----");
            }
        }
        System.out.println();
    }

    // Checks whether the given player has a winning combination on the board.
    public static boolean won(char[][] b, char p){
        // rows and columns
        for(int i = 0; i < sz; i++){
            if(b[i][0]==p && b[i][1]==p && b[i][2]==p){
                return true;
            }
            if(b[0][i]==p && b[1][i]==p && b[2][i]==p){
                return true;
            }
        }
        // diagonals
        if (b[0][0] == p && b[1][1] == p && b[2][2] == p){
            return true;
        }
        if (b[0][2] == p && b[1][1] == p && b[2][0] == p){
            return true;
        }

        return false;
    }

    // Returns true when the board has no empty cells left.
    public static boolean isFull(char[][] b){
        for(int i = 0; i<sz; i++){
            for(int j = 0; j<sz; j++){
                if (b[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }
}
