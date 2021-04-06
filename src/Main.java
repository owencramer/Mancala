/* Main game for Mancala
 * Owen Cramer
 * 12 March 2021
 * Controls how the game runs
 */

import java.util.*;
import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Board board = new Board();
        /* It makes programming simpler if you create an array of players instead of two individual players.
         * First, space has to be saved for the two instances of the Player class. Initializing this data
         * is done later.
         */
        Player[] players = new Player[2];
        int current;
        Scanner input = new Scanner(System.in);
        board.printBoard();

        System.out.println("Welcome to " + ANSI_Colors.RED_BOLD + ANSI_Colors.RED_UNDERLINED + "Mancala"
                + ANSI_Colors.RESET + "!");
        System.out.print("Would you like to see rules first? [Y|N] ");
        String answer = input.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            System.out.println(mancalaRules());
        }
        System.out.print("Who is the first player? ");
        players[0] = new Player(input.nextLine(), 1); // Initialize the first player
        System.out.println("Saved first player as: " + ANSI_Colors.BLUE_BOLD + players[0].getName() + ANSI_Colors.RESET);
        System.out.print("Who is the second player? ");
        players[1] = new Player(input.nextLine(), 2); // Initialize the second player
        System.out.println("Saved second player as: " + ANSI_Colors.GREEN_BOLD + players[1].getName() + ANSI_Colors.RESET);
        System.out.println("Welcome " + ANSI_Colors.BLUE_BOLD + players[0].getName() + ANSI_Colors.RESET + " and " + ANSI_Colors.GREEN_BOLD
                + players[1].getName() + ANSI_Colors.RESET + "! Enjoy the game!");
        current = 0;
        boolean loop = true;
        int playerIndex = 0;
        do {
            System.out.println("It is now time for " + ANSI_Colors.YELLOW_BOLD + players[current].getName() + ANSI_Colors.RESET + " to make a move.");
            playerIndex = board.playerMove(players[playerIndex]);
            if (playerIndex == 0) loop = false;
            else playerIndex--; // Trust that only 0/1/2 can be returned.
            current = playerIndex;
        } while (loop);
    }

    public static String mancalaRules() {
        String rules = "Rules for Mancala:\n" +
                "The board is designed for two players. There are six pits on each players side and a Mancala (large pit) on either side.\n" +
                "To start place four stones in each pit, leaving the Mancala empty. Decide who goes first.\n" +
                "One player picks up all the stones in one of their own pits. Starting with the next pit over (Counter-clockwise), drop one stone in\n" +
                "each pit including your own Mancala and skipping your opponents Mancala. If the last stone is dropped in your own Mancala, you get to\n" +
                "play again. If the last stone is dropped into an empty pit on your own side and there are stones in the pit opposite the pit where you just\n" +
                "dropped the last stone, then you get to capture your own stone and all the stones in the opposite pit and place them all in your own Mancala.\n" +
                "Otherwise, it is the other players turn. When all the pits on one side are empty, the other player gets to take\n" +
                "all of the stones on their own side and place those stones into their own Mancala. At the end, whoever has the most stones in their Mancala wins.";

        return rules;
    }
}