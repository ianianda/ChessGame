package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.Serializable;
import javax.swing.JPanel;


/**
 * Sets a piece.
 * @author Yan Wang
 * @version 1
 *
 */
public class Square extends JPanel implements Serializable {

    /**
     * Narrows board.
     */
    public static final double WIDTH = 0.75;
    
    /**
     * Doubles the size.
     */
    public static final int TWO = 2;
    
    private static final long serialVersionUID = 1L;
    private int row1;
    private int col1;
    private Color c1;
    private Board board;
    private Piece piece;
    
    protected Color color;
    protected String s1 = "";
    
    private boolean occupied;
    
    
    /**
     * Sets square.
     * @param color.
     */
    public Square(Board board, int row, int col, Color color) {
        this.color = color;
        this.board = board;
        this.setBackground(color);
        this.row1 = row;
        this.col1 = col;
        Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int i2 = (int)(1.0 / Chess.CHESS * Math.min(localDimension.width * WIDTH , 
                localDimension.height * WIDTH));
        
        Rectangle localRectangle = new Rectangle();
        localRectangle.setBounds(((localDimension.width - i2) / TWO) , 
                ((localDimension.height - i2) / TWO), i2, i2);
        setBounds(localRectangle);
    }
    
    

    /**
     * check if square is occupied.
     * @return the occupied.
     */
    public boolean isOccupied() {
        return occupied;
    }



    /**
     * check occupied setter.
     * @param occupied the occupied to set
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * Paints board content.
     * @param g1 unused
     */
    public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
    }
    
    /**
     * Gets colour.
     * @return color.
     */
    public Color getC1() {
        return c1;
    }
    
    /**
     * Sets piece.
     * @param piece.
     */
    public void setPiece(Piece piece) {
        if (piece != null) {
            if (this.piece != null) {
                this.remove(this.piece);
            }
            this.piece = piece;
            this.add(piece);
            occupied = true;
            this.setS1(piece.getS1());
        }
    }
    
    /**
     * remove a piece.
     */
    public void removePiece() {
        if (occupied) {
            this.remove(this.piece);
            this.piece = null;
            occupied = false;
            this.setS1(" ");
        }

    }
    
    /**
     * set color.
     * @param c1 the color to set.
     */
    public void setC1(Color c1) {
        this.c1 = c1;
    }

    /**
     * Gets piece.
     * @return piece.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * get row number.
     * @return the row1
     */
    public int getRow1() {
        return row1;
    }

    /**
     * set row number.
     * @param row1 the row1 to set
     */
    public void setRow1(int row1) {
        this.row1 = row1;
    }

    /**
     * get color.
     * @return the col1
     */
    public int getCol1() {
        return col1;
    }

    /**
     * get string.
     * @return the s1
     */
    public String getS1() {
        return s1;
    }

    /**
     * set string.
     * @param s1 the s1 to set
     */
    public void setS1(String s1) {
        this.s1 = s1;
    }

    /**
     * set color.
     * @param col1 the col1 to set
     */
    public void setCol1(int col1) {
        this.col1 = col1;
    }

    /**
     * get color.
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * set color.
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets a board.
     * @return board. a board.
     */
    public Board getBoard() {
        return board;
    }
}