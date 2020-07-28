package tictactoe;

import java.util.Scanner;

public class Main {
    
    private static boolean fullRowX(char[][] fields) {

        /*
        ------------------ALL OPERATIONS BOTTOM TO UP --------------------------------
         */

        for (int row = 0; row < 3; ++row) {
            if (fields[row][0] == 'X' && fields[row][1] == 'X' && fields[row][2] == 'X') {
                return true;
            }
        }

        for (int col = 0; col < 3; ++col) {
            if (fields[0][col] == 'X' && fields[1][col] == 'X' && fields[2][col] == 'X') {
                return true;
            }
        }

        if (fields[0][0] == 'X' && fields[1][1] == 'X' && fields[2][2] == 'X') {
            return true;
        }

        if (fields[0][2] == 'X' && fields[1][1] == 'X' && fields[2][0] == 'X') {
            return true;
        }

        return false;

    }

    private static boolean fullRowO(char[][] fields) {

        for (int row = 0; row < 3; ++row) {
            if (fields[row][0] == 'O' && fields[row][1] == 'O' && fields[row][2] == 'O') {
                return true;
            }
        }
        if (fields[0][0] == 'O' && fields[1][1] == 'O' && fields[2][2] == 'O') {
            return true;
        }

        if (fields[0][2] == 'O' && fields[1][1] == 'O' && fields[2][0] == 'O') {
            return true;
        }

        for (int col = 0; col < 3; ++col) {
            if (fields[0][col] == 'O' && fields[1][col] == 'O' && fields[2][col] == 'O') {
                return true;
            }
        }

        return false;

    }

    private static boolean isFull(char[][] fields) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (fields[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] bottomToTop = new char[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                bottomToTop[i][j] = ' ';
            }
        }

        String x;
        String y;
        int posx;
        int posy;
        boolean xPlays = true;

        // start the game
        while (true) {
            System.out.println("\n---------");
            int indexIn = 0;
            int indexOut = 2;
            for (int i = 0; i < 3; i++) {
                System.out.print('|');
                for (int j = 0; j < 3; j++) {
                    System.out.print(" " + bottomToTop[indexOut][indexIn++]);
                    if (indexIn > 2) {
                        indexIn = 0;
                        indexOut--;
                    }
                }
                System.out.println(" |");
            }
            System.out.println("---------");

            // check state:---
            // if anyone wins OR game draws at last, print status and break out (THE END).
            // .....
            if (fullRowO(bottomToTop)) {
                System.out.println("O wins");
                break;
            } else if (fullRowX(bottomToTop)) {
                System.out.println("X wins");
                break;
            } else if (isFull(bottomToTop)) {
                System.out.println("Draw");
                break;
                // the draw condition is checked only at last. IT CAN BE IMPROVED TO CHECK POSSIBILITY OF DRAW DURING THE GAME.
            }

            // else, continue the game loop. Come back to check status after assignment of new position.
            while (true) {
                System.out.println("Enter the coordinates: ");
                x = sc.next();
                y = sc.next();
                posx = Integer.parseInt(x);
                posy = Integer.parseInt(y);

                // input validation
                if (!x.matches("-?\\d+(\\.\\d+)?") || !y.matches("-?\\d+(\\.\\d+)?")) {     // the regex is for numbers
                    System.out.println("You should enter numbers!");
                    continue;
                } else if (!(posx >= 1 && posx <=3 && posy >= 1 && posy <= 3)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                // position assignment, else check position occupancy
                if (bottomToTop[posy - 1][posx - 1] == ' ') {
                    bottomToTop[posy - 1][posx - 1] = xPlays ? 'X' : 'O';
                    xPlays ^= true; // rotate xPlays
                    break;  // print fields, check status

                } else {
                    System.out.println("The cell is occupied! Choose another one!");
                    // take input again (repeat this loop)
                }

            }
            // NOTHING; THE END-----------------------------------------------------------------------------------------------------
        }
    }
}
