/* Store information about each player
 * Owen Cramer
 * 12 March 2021
 * This program stores information about each player
 */

public class Player {
    private String name;
    final private int playerNumber;
    private int startPit, endPit, mancalaPit;
    public int player;

    public Player() {
        name = "none";
        playerNumber = 0;
    }

    public Player(String n, int player) {
        name = n;
        playerNumber = player;
        // Set up valid choices for each player
        if (player == 1) {
            startPit = 0;
            endPit = 5;
            mancalaPit = 6;
        } else {
            startPit = 7;
            endPit = 12;
            mancalaPit = 13;
        }
    }

    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public int getStartPit() {
        return startPit;
    }

    public int getEndPit() {
        return endPit;
    }

    public int getMancalaPit() {
        return mancalaPit;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}