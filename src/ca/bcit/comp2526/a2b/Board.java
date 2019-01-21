package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 * Creates a chessboard.
 * @author Yan Wang
 * @version 1
 *
 */
public class Board extends JFrame implements Serializable {

    /**
     * Sets chess bound 8.
     */
    public static final int CHESS = 8;

    /**
     * Narrows board.
     */
    public static final double WIDTH = 0.75;
    
    /**
     * the row and column of board.
     */
    public static final int SIZE = 8;
    
    /**
     * Create a JFrame for serialization.
     */
    public static JFrame gameBoard;
    
    /**
     * Doubles the size.
     */
    public static final int TWO = 2;
    
    /**
     * Creates JMenuBar for serialization.
     */
    public JMenuBar menuBar;

    /**
     * Initializes square.
     */
    public static Square[][] squares;

    private Player p1;
    private Player p2;
    private Square[][] board;
    private boolean value;
    
    private static final long serialVersionUID = 1L;

    private MoveListener moveListener; //creates a move listener.
    
    /**
     * Initializes a board.
     */
    public Board(String gameName) {

        super(gameName);
        
        gameBoard = this;
        squares = new Square[Chess.CHESS][Chess.CHESS];
        moveListener = new MoveListener(this);
        this.setLayout(new GridLayout(Chess.CHESS, Chess.CHESS));
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        // Define and add menu items.
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        
        // Add the same and load actions.
        JMenuItem saveAction = new JMenuItem("Save Game");
        JMenuItem loadAction = new JMenuItem("Load Game");
        fileMenu.add(saveAction);
        fileMenu.add(loadAction);
        
        initBoard();
        
        initMenu(gameBoard);
        
        setup();
        
        p1 = new Player(Color.RED, true);
        p2 = new Player(Color.BLACK, false);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.centre();
        this.setVisible(true);               
    }
    
    /**
     * Checks if movement is valid in horizontal way.
     * @param curB
     *            the destination square.
     * @param preB
     *            start square.
     * @return
     *            true if it is valid while false if it is not.
     */
    public boolean isValidHorizontal(Square curB, Square preB) {
    
        value = true;
    
        int xdistance = curB.getRow1() - preB.getRow1();
        int ydistance = curB.getCol1() - preB.getCol1();
        int i1;
    
        if ((ydistance < 0) && (xdistance == 0)) {
            for (i1 = curB.getCol1(); i1 < preB.getCol1(); i1 += 1) {
                if (Board.squares[preB.getRow1()][i1].isOccupied()) {
                    value = false;
                }
            }
        } else if ((ydistance > 0) && (xdistance == 0)) {
            for (i1 = preB.getCol1(); i1 < curB.getCol1(); i1 += 1) {
                if (Board.squares[preB.getRow1()][i1].isOccupied()) {
                    value = false;
                }
            }
        }
        return value;
    }
    
    /**
     * Checks if the movement is valid in vertical way.
     * @param curB
     *            destination square.
     * @param preB
     *            start square.
     * @return
     *        true if it is valid, while false if it is not.
     */
    public boolean isValidVertical(Square curB, Square preB) {
        int xdistance = curB.getRow1() - preB.getRow1();
        int ydistance = curB.getCol1() - preB.getCol1();
        int i1;
        
        if ((ydistance == 0) && (xdistance < 0)) {
            for (i1 = curB.getRow1(); i1 < preB.getRow1(); i1 += 1) {
                if (Board.squares[i1][preB.getCol1()].isOccupied()) {
                    value = false;
                }
            }
        } else if ((ydistance == 0) && (xdistance > 0)) {
            for (i1 = preB.getRow1(); i1 < curB.getRow1(); i1 += 1) {
                if (Board.squares[i1 + 1][preB.getCol1()].isOccupied()) {
                    value = false;
                }
            }
        }
        return value;
    }

    /**
     * Checks if the movement is valid in diagonal way.
     * @param curB
     *            destination square.
     * @param preB
     *            start square.
     * @return
     *        true if it is valid, while false if it is not.
     */
    public boolean isValiddiagonal(Square curB, Square preB) {
        boolean value = true;
        int xdistance = curB.getRow1() - preB.getRow1();
        int ydistance = curB.getCol1() - preB.getCol1();
        int i1;
        int j1 = 0;
        
        if ((ydistance != 0) && (xdistance != 0)) {
            if ((xdistance > 0) && (ydistance > 0)) { //move right down
                for (i1 = preB.getCol1(); i1 < curB.getCol1(); i1 += 1) {
                    j1++;
                    if (Board.squares[preB.getRow1() + j1][preB.getCol1() + j1].isOccupied()) {
                        value = false;
                    }
                }
            } else if ((xdistance > 0) && (ydistance < 0)) { //move left down
                for (i1 = curB.getCol1(); i1 < preB.getCol1(); i1 += 1) {
                    j1++;
                    if (Board.squares[preB.getRow1() + j1][preB.getCol1() - j1].isOccupied()) {
                        value = false;
                        System.out.println(value);
                    }
                }
            } else if ((xdistance < 0) && (ydistance > 0)) { //move right up
                for (i1 = preB.getCol1(); i1 < curB.getCol1(); i1 += 1) {
                    j1++;
                    if (Board.squares[preB.getRow1() - j1][preB.getCol1() + j1].isOccupied()) {
                        value = false;
                    }
                }
            } else if ((xdistance < 0) && (ydistance < 0)) {
                for (i1 = curB.getCol1(); i1 < preB.getCol1(); i1 += 1) {
                    j1++;
                    if (Board.squares[preB.getRow1() - j1][preB.getCol1() - j1].isOccupied()) {
                        value = false;
                    }
                }
            } 
        }
        return value;
    }
    
    /**
     * switch each turn.
     */
    public void switchTurn() {
        if (p1.isTurn()) {
            p1.setTurn(false);
            p2.setTurn(true);
        } else {
            p1.setTurn(true);
            p2.setTurn(false);
        }
    }
    
    /**
     * get current player.
     * @return player's name.
     */
    public Player getCurrentPlayer() {
        if (p1.isTurn()) {
            return p1;
        } else {
            return p2;
        }
    }
    
    /**
     * check which turn.
     * @return if it is curB's turn.
     */
    @SuppressWarnings("unused")
    private boolean isTurn() {
    
        Boolean isTurn = true;
        if (p1.isTurn()) {
            isTurn = true;
        } else {
            isTurn = false;
        }
        
        return isTurn;

    }
    
    private void initBoard() { //initializes a board

        for (int i = 0; i < Chess.CHESS; ++i) {
            for (int j = 0; j < Chess.CHESS; ++j) {
                if ((i + j) % TWO == 0) { // calculates color to print for each square
                    squares[i][j] = new Square(this, i, j, Color.WHITE);
                } else {
                    squares[i][j] = new Square(this, i, j, Color.PINK);
                }
                //squares[7][0].add(new JButton("Save"));
                squares[i][j].addMouseListener(moveListener);
                this.add(squares[i][j]);
            }
        }
    }
        
    private void setup() { //sets up pieces. 
        squares[0][3].setPiece(new King(Color.BLACK));
        squares[7][3].setPiece(new King(Color.RED));
        
        squares[0][4].setPiece(new Queen(Color.BLACK));
        squares[7][4].setPiece(new Queen(Color.RED));
        
        squares[0][2].setPiece(new Bishop(Color.BLACK));
        squares[0][5].setPiece(new Bishop(Color.BLACK));
        squares[7][2].setPiece(new Bishop(Color.RED));
        squares[7][5].setPiece(new Bishop(Color.RED));
        
        squares[0][1].setPiece(new Knight(Color.BLACK));
        squares[0][6].setPiece(new Knight(Color.BLACK));
        squares[7][1].setPiece(new Knight(Color.RED));
        squares[7][6].setPiece(new Knight(Color.RED));
        
        squares[0][0].setPiece(new Rook(Color.BLACK));
        squares[0][7].setPiece(new Rook(Color.BLACK));
        squares[7][0].setPiece(new Rook(Color.RED));
        squares[7][7].setPiece(new Rook(Color.RED));
        
        squares[1][0].setPiece(new Pawn(Color.BLACK)); 
        squares[1][1].setPiece(new Pawn(Color.BLACK)); 
        squares[1][2].setPiece(new Pawn(Color.BLACK));
        squares[1][3].setPiece(new Pawn(Color.BLACK));
        squares[1][4].setPiece(new Pawn(Color.BLACK));
        squares[1][5].setPiece(new Pawn(Color.BLACK));
        squares[1][6].setPiece(new Pawn(Color.BLACK));
        squares[1][7].setPiece(new Pawn(Color.BLACK));

        squares[6][0].setPiece(new Pawn(Color.RED));
        squares[6][1].setPiece(new Pawn(Color.RED));
        squares[6][2].setPiece(new Pawn(Color.RED));
        squares[6][3].setPiece(new Pawn(Color.RED));
        squares[6][4].setPiece(new Pawn(Color.RED));
        squares[6][5].setPiece(new Pawn(Color.RED));
        squares[6][6].setPiece(new Pawn(Color.RED));
        squares[6][7].setPiece(new Pawn(Color.RED));
        
        revalidate();
        repaint();
    }
    
    private void centre() { //Sets board in the center.
        Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int i2 = (int) Math.min(localDimension.width * WIDTH, localDimension.height * WIDTH);

        Rectangle localRectangle = new Rectangle();
        localRectangle.setBounds(((localDimension.width - i2) / TWO), 
                ((localDimension.height - i2) / TWO), i2, i2);

        setBounds(localRectangle);

    }

    /**
     * get a square.
     * @param row.
     * @param column.
     * @return square.
     */
    public Square getSquare(int row, int column) {
        Square square = null;
        if (row < SIZE && column < SIZE && row >= 0 && column >= 0) {
            square = board[row][column];
        }
        return square;
    }
    
    
    /**
     * get board.
     * @return the board
     */
    public Square[][] getBoard() {
        return board;
    }

    /**
     * set a board.
     * @param board the board to set
     */
    public void setBoard(Square[][] board) {
        this.board = board;
    }
    

    private void initMenu(JFrame newBoard) { //initialize JMenuBar for serialization.
        
        // Creates a menu bar for a JFrame
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        // Define and add menu items.
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        
        // Add the same and load actions.
        JMenuItem saveAction = new JMenuItem("SAVE");
        JMenuItem loadAction = new JMenuItem("OPEN");
        fileMenu.add(saveAction);
        fileMenu.add(loadAction);
        
        saveAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {

                try {
                    FileOutputStream fileOutput = new FileOutputStream("test.gam");
                    @SuppressWarnings("resource")
                    ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
                    objOutput.writeObject(newBoard);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        
        loadAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e2) {
               
                try {
                    FileInputStream fileInput = new FileInputStream("test.gam");
                    @SuppressWarnings("resource")
                    ObjectInputStream obInput = new ObjectInputStream(fileInput);
                    JFrame tempBoard = (JFrame) obInput.readObject();
                    tempBoard.setVisible(true);
                } catch (ClassNotFoundException | IOException e3) {
                    e3.printStackTrace();
                }

            }
        });
    }
}