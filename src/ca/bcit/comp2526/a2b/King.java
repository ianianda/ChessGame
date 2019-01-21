package ca.bcit.comp2526.a2b;

import java.awt.Color;

/**
 * Sets a piece.
 * @author Yan Wang
 * @version 1
 *
 */
public class King extends Piece {

    private static final long serialVersionUID = 1L;

    /**
     * Sets king.
     * @param color.
     */
    public King(Color color) {
         super(color, "K");
    }

    /**
     * Moves piece.
     */
    public boolean isMoveLegal(Square curB, Square preB) {

        int xdistance = Math.abs(curB.getRow1() - preB.getRow1());
        int ydistance = Math.abs(curB.getCol1() - preB.getCol1());
        int distance = xdistance + ydistance;
    
        if (distance == 1 || (distance == 2 && xdistance != 0 && ydistance != 0)) {
            return true;
        } else {
            return false;
        }
    }
}