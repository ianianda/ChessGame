package ca.bcit.comp2526.a2b;

/**
 * Sets a chess.
 * @author Yan Wang
 * @version 1
 *
 */
public class Chess implements java.io.Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * Sets width of a chessboard.
     */
    public static final int CHESS = 8;
    
    
    @SuppressWarnings("unused")
    private static Board board; //Creates a Board object to call Board class
    
    /**
     * Creates a board.
     */
    public Chess() {
        board = new Board("Chess");
    }
    
    /**
     * Drives chess project.
     * @param args.
     */
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        Chess myGame = new Chess();
    }
}