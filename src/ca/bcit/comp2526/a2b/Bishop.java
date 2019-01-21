package ca.bcit.comp2526.a2b;

import java.awt.Color;

/**
 * Sets a piece.
 * @author Yan Wang
 * @version 1
 *
 */
public class Bishop extends Piece {

    private static final long serialVersionUID = 1L;
    
    private boolean value;
    
    /**
     * Bishop constructor.
     * @param color.
     */
    public Bishop(Color color) {
        super(color, "B");
    }

    /**
     * Moves piece.
     */
    public boolean isMoveLegal(Square curB, Square preB) {

        value = false;
        if (((curB.getRow1() - preB.getRow1()) != 0) 
            && (curB.getCol1() - preB.getCol1()) != 0) { 
            if (preB.getBoard().isValiddiagonal(curB, preB)) {
                value = true;
            }
        }
        return value;
    
    }
}
