package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.io.Serializable;
import javax.swing.JLabel;


/**
 * Sets a piece.
 * @author Yan Wang
 * @version 1
 *
 */
public abstract class Piece extends JLabel implements Serializable {

    private static final long serialVersionUID = 1L;
    private Color c1;
    private String s1 = " ";
    private Square square = null;
    
    /**
     * Sets piece.
     */
    public Piece(Color c1, String s1) {
        super(s1);
        this.setForeground(c1);
        this.c1 = c1;
        this.s1 = s1;
    }
    
    /**
     * Gets square.
     * @return square 
     */
    public Square getSquare() {
        return square;
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

    /**
     * get string.
     * @return the s1
     */
    public String getS1() {
        return s1;
    }

    /**
     * set name.
     * @param s1 the s1 to set
     */
    public void setS1(String s1) {
        this.s1 = s1;
    }
    
    /**
     * Checks the move is legal.
     * @param dest destination square
     * @param src beginning square
     */
    public abstract boolean isMoveLegal(Square dest, Square src);
}
