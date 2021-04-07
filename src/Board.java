/* Tracking and printing the Mancala Board
 * Owen Cramer
 * 12 March 2021
 * Creates the board layout
 */

import java.util.*;
import java.lang.*;

public class Board {
    Scanner input = new Scanner(System.in);
    final private int[] board = {4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0};
    final private char[] labels = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N'};

    public void printSolidLine(int dots, boolean newLine) {
        for (int i = 0; i < dots; i++) {
            System.out.print("-");
        }
        if (newLine) System.out.println();
    }

    private void printDottedLine(int dots, boolean newLine) {
        for (int i = 0; i < dots; i++) {
            System.out.print("|    ");
        }
        if (newLine) System.out.println('|');
    }

    private void printTopPlayer() {
        for (int i = 13; i > 6; i--) {
            System.out.printf("|  " + ANSI_Colors.GREEN_BOLD + "%c" + ANSI_Colors.RESET + " ", labels[i]);
        }
        printDottedLine(1, true);
        for (int i = 13; i > 6; i--) {
            System.out.printf("| %2d ", board[i]);
        }
        printDottedLine(1, true);
    }

    private void printBottomPlayer() {
        printDottedLine(1, false);
        for (int i = 0; i <= 6; i++) {
            System.out.printf("| %2d ", board[i]);
        }
        printDottedLine(0, true);
        printDottedLine(1, false);
        for (int i = 0; i <= 6; i++) {
            System.out.printf("|  " + ANSI_Colors.BLUE_BOLD + "%c" + ANSI_Colors.RESET + " ", labels[i]);
        }
        printDottedLine(0, true);
    }

    public void printBoard() {
        printSolidLine(41, true);
        printDottedLine(8, true);
        printTopPlayer();
        printDottedLine(8, true);
        printSolidLine(41, true);
        printDottedLine(8, true);
        printBottomPlayer();
        printDottedLine(8, true);
        printSolidLine(41, true);
    }

    /* int playerMove(Player player)
     * Input - the player to move next
     * Output - the number of the player to move next.
     */


    int findIndex(char letter) {
        int retVal = -1;
        int index = -1;
        while (index < labels.length-1) {
            index++;
            if (letter == labels[index]) {
                retVal = index;
                break;
            }
        }
        return retVal;
    }
    /* int checkSideEmpty()
     * Input: None
     * Output: 0 no side empty, 1 player 1 side empty, 2 player 2 side empty, 2 both sides empty
     */

    public int checkSideEmpty() {
        int retVal, p1 = 0, p2 = 0;
        // Checks player 1's for empty pits.
        for (int i = 0; i < 6; i++) {
            if (board[i] != 0) {
                p1 = 0;
                break;
            } else p1 = 1;
        }
        // Checks player 2's for empty pits.
        for (int i = 0; i < 11; i++) {
            if (board[i] != 0) {
                p2 = 0;
                break;
            } else p2 = 1;
        }
        retVal = p1 + p2;
        return retVal;
    }

    public int playerMove(Player player) {
        int retVal, index = -1;
        boolean loop = true;
        char letter = 'Z';
        printBoard();
        do {
            System.out.print("Hello " + ANSI_Colors.BLUE_BOLD + player.getName() + ANSI_Colors.RESET + ", choose a pit between " + ANSI_Colors.GREEN_BOLD
                    + labels[player.getStartPit()] + ANSI_Colors.RESET + " and " + ANSI_Colors.GREEN_BOLD
                    + labels[player.getEndPit()] + ANSI_Colors.RESET + ": ");
            loop = true;
            try {
                letter = input.nextLine().toUpperCase().charAt(0);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ANSI_Colors.RED_BOLD + "Please enter a letter. " + ANSI_Colors.RESET);
                letter = 'Z';
            }
            index = findIndex(letter);
            if (index != -1 && index >= player.getStartPit() && index <= player.getEndPit()
                    && board[index] > 0) loop = false;
            else {
                System.out.println("Select a pit on your side that contains stones.");
                if (checkSideEmpty() > 0) {
                    loop = false;
                    System.out.println("At least one side is empty, the game is now over.");
                    index = -1;
                }
            }
        } while (loop);


        if (index == -1) retVal = 0;
        else {
            int stones = board[index];
            board[index] = 0;
            while (stones > 0) {
                index++;
                if ((player.getPlayerNumber() == 1 && index == 13) || (player.getPlayerNumber() == 2 && index == 6)) index++;
                if (index == 14) index = 0;
                board[index]++;
                stones--;
            }
            if (player.getPlayerNumber() == 1 && player.getMancalaPit() == index) retVal = 1;
            else if (player.getPlayerNumber() == 2 && player.getMancalaPit() == index) retVal = 2;
            else if (player.getPlayerNumber() == 1) retVal = 2;
            else retVal = 1;
        }
        return retVal;
    }
}