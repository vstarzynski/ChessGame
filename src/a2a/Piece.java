package a2a;

import javax.swing.JLabel;

/**
 * Interface for every piece in the game.
 * @author Victor Starzynski
 * @version 1.0
 *
 */
interface Piece  {

    /**
     * Getter for the image of the piece.
     * @return The Image of the piece.
     */
    JLabel getPieceLabel();
    
    /**
     * Check if the move is valid.
     * @param newSquare square to move
     * @param oldSquare square from
     * @param boardPositions Positions in the board
     * @return if the move is valid
     */ 
    boolean move(Square newSquare, Square oldSquare, boolean[][] boardPositions);
    
    /**
     * Getter for the owner of the Piece.
     * @return the Player.
     */
    Player getOwner();
}
