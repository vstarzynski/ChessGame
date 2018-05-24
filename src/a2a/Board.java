package a2a;

import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JFrame;

/**
 * Implements the board of a chess game.
 * @author Victor Starzynski
 * @version 1.0
 *
 */
public class Board extends JFrame implements java.io.Serializable {
    
    /**
     * Version UID.
     */
    private static final long serialVersionUID = 19L;

    /**
     * Sets the amount of Squares in a board.
     * Default for a Chess game is 8x8
     */
    private static final int SQUARES = 8;
    
    /**
     * Sets the board size.
     * Default is 600 x 600
     */
    private static final int BOARD_SIZE = 600;
    
    /**
     * Matrix that represents if an Square is free or not.
     */
    private final boolean[][] boardPositions = new boolean[SQUARES][SQUARES];
    
    /**
     * Saves the turn of the game.
     */
    private int saveTurn;    
    
    
    /**
     * Constructor of the Chess Board.
     */
    public Board(Chess chess) {
         
        saveTurn = 0;
        //Window Size
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setSize(BOARD_SIZE, BOARD_SIZE);
        getContentPane().setLayout(new GridLayout(SQUARES, SQUARES));     
        for (int i = 0; i < SQUARES; i++) {
            for (int j = 0; j < SQUARES; j++) {
                if ((i + j) % 2 == 0) {
                    add(new Square(chess, this, new Color(255, 229, 204), 10*i+j));
                } else {
                    add(new Square(chess, this, new Color(102, 51, 0), 10*i+j));
                }
            }
        }      
        //Make window Visible.
        setVisible(true);         
    }
    
    /**
     * Update the positions in the chess board.
     * @param x - x coordinate to update
     * @param y - y coordinate to update
     * @param update - insert of remove a piece in the coordinate
     */
    public void updatePositions(int x, int y, boolean update) {
        boardPositions[x][y] = update;
    }
    
    /**
     * Getter to the Board positions matrix.
     * @return the matrix with positions
     */
    public boolean[][] getBoardPositions() {
        return boardPositions;
    }
    
    /**
     * Show the board.
     */
    public void showBoard() {
        setVisible(true); 
    }
    
    /**
     * Saves the turn.
     * @param turn The turn.
     */
    public void saveTurn(int turn) {
        saveTurn = turn;
    }
    
    /**
     * Get's the turn.
     * @return The Saved Turn
     */
    public int getSavedTurn() {
        return saveTurn;
    }    
    
}
