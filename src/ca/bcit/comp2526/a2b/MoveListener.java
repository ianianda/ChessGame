package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * Sets a piece.
 * @author Yan Wang
 * @version 1
 *
 */
public class MoveListener extends MouseAdapter implements Serializable {

    private static final long serialVersionUID = 1L;
    private Square preB = null;
    private Board board;
    

    /**
     * Moves mouse actions.
     * @param board.
     */
    public MoveListener(Board board) {
        this.board = board;
    }

    /**
     * Clicks mouse.
     */
    @Override
    public void mouseClicked(MouseEvent e1) {
        Square curB = (Square) e1.getSource(); 
        
        if (preB == null) { // if it is the first time to click the mouse
        
            if (curB.getPiece() != null) { // if there is a piece on the clicked square
                preB = curB;
                curB = null;
            }
        } 

        if (curB != null && preB != null) {
            // if it is the second time to click the mouse and current square has a piece
            
            if (preB.getBoard().getCurrentPlayer().getC1() == preB.getPiece().getC1()) {
                if (preB.getPiece().isMoveLegal(curB, preB))  {
                    if ((curB.isOccupied())) {
                        if (preB.getPiece().getC1() != curB.getPiece().getC1()) {
                            preB.getBoard().switchTurn();
                            curB.setPiece(preB.getPiece());
                            preB.removePiece();
                            preB.repaint();
                            curB.repaint();
                            board.repaint();
                        }
                    } else {
                        preB.getBoard().switchTurn();
                        curB.setPiece(preB.getPiece());
                        preB.removePiece();
                        preB.repaint();
                        curB.repaint();
                        board.repaint();
                    }
                }
            }
            preB = null;
        }
        
    }
    
    /**
     * Enters in a square.
     */
    @Override
    public void mouseEntered(MouseEvent e1) {
        // TODO Auto-generated method stub

    }

    /** 
     * Exits a square.
     */
    @Override
    public void mouseExited(MouseEvent e1) {
        // TODO Auto-generated method stub

    }

    /**
     * Presses mouse.
     */
    @Override
    public void mousePressed(MouseEvent e1) {
        Square pressedSquare = (Square) e1.getSource();
        pressedSquare.setBackground(Color.ORANGE);
    }

    /** 
     * Releases mouse.
     */
    @Override
    public void mouseReleased(MouseEvent e1) {
        Square pressedSquare = (Square) e1.getSource();
        pressedSquare.setBackground(pressedSquare.getColor());
    }
}