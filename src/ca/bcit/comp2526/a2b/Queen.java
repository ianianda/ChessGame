package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.io.Serializable;

/**
 * Sets a piece.
 * @author Yan Wang
 * @version 1
 *
 */
public class Queen extends Piece implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean value;
    
    /**
     * Sets Queen constructor.
     * @param color queen's color.
     */
    public Queen(Color color) {
        super(color, "Q");
    }

    /**
     * Checks if movement is valid.
     * returns true if it is valid, while invalid returns false.
     */
    public boolean isMoveLegal(Square curB, Square preB) {
        
        value = false;
        
        if (curB.getBoard().isValiddiagonal(curB, preB) 
            && curB.getBoard().isValidHorizontal(curB, preB) 
            && curB.getBoard().isValidVertical(curB, preB)) {
            value = true;
        }
        return value;
    }
}