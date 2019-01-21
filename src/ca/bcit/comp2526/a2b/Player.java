package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.io.Serializable;

public class Player implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private boolean isTurn;
    private Color c1;

    /**
     * Sets players.
     * @param c1 color of player
     * @param isTurn if it is the player's turn
     */
    public Player(Color c1, boolean isTurn) {
        this.c1 = c1;
        this.isTurn = isTurn;
    }

    /**
     * check if its current piece's turn.
     * @return the isTurn
     */
    public boolean isTurn() {
        return isTurn;
    }

    /**
     * set turn.
     * @param isTurn the isTurn to set
     */
    public void setTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    /**
     * get color.
     * @return the c1
     */
    public Color getC1() {
        return c1;
    }

    /**
     * set color.
     * @param c1 the c1 to set
     */
    public void setC1(Color c1) {
        this.c1 = c1;
    }
}
