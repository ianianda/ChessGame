package ca.bcit.comp2526.a2b;

import java.awt.Color;

/**
 * Sets a piece.
 * @author Yan Wang
 * @version 1
 *
 */
public class Pawn extends Piece {

    private static final long serialVersionUID = 1L;
    private boolean value;
    
    /**
     * Sets piece.
     * @param color.
     */
    public Pawn(Color color) {
        super(color, "P");
    }

    /**
     * Moves piece.
     */
    public boolean isMoveLegal(Square curB, Square preB) {

        value = false;

        int xdistance = preB.getRow1() - curB.getRow1();
        int ydistance = Math.abs(curB.getCol1() - preB.getCol1());
        
        if ((preB.getRow1() == 1) || (preB.getRow1() == 6)) {
            if (preB.getPiece().getC1() == Color.RED) {
                if ((xdistance == 2 && ydistance == 0) || (xdistance == 1 && ydistance == 0)) {
                    value = true;
                }
            } else {
                if ((xdistance == -2 && ydistance == 0) || (xdistance == -1 && ydistance == 0)) {
                    value = true;
                }
            }
        } else {
            if (preB.getPiece().getC1() == Color.RED) {
                if ((xdistance == 1) && (ydistance == 0)) {
                    value = true;
                }
            } else {
                if ((xdistance == -1) && (ydistance == 0)) {
                    value = true;
                }
            } 
        } 
        return value;
    } 
}