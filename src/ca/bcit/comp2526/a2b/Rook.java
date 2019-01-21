package ca.bcit.comp2526.a2b;

import java.awt.Color;

/**
 * Sets a piece.
 * @author Yan Wang
 * @version 1
 *
 */
public class Rook extends Piece {

    /**
     * Sets default versionUID.
     */
    private static final long serialVersionUID = 1L;

    private boolean value;

    /**
     * Sets color.
     * @param color.
     */
    public Rook(Color color) {
        super(color, "R");
    }

    /**
     * sets valid steps.
     * @param curB.
     * @param preB.
     */
    public boolean isMoveLegal(Square curB, Square preB) {
    
        value = false;
        if ((curB.getRow1() - preB.getRow1()) == 0 || (curB.getCol1() - preB.getCol1()) == 0) {
            if (preB.getBoard().isValidHorizontal(curB, preB) 
                    && preB.getBoard().isValidVertical(curB, preB)) {
                value = true;
            }
        }
        
        return value;
    
    }

}