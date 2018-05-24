package a2a;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.*;

/**
 * Implements a Black King.
 * @author Victor Starzynski
 * @version 1.0
 *
 */
public class BlackKing implements Piece, java.io.Serializable {

    /**
     * Image of the string.
     */
    private ImageIcon pieceImage;
    
    /**
     * Label with the Image of the piece.
     */
    private JLabel pieceLabel;
    
    /**
     * Owner of the Piece.
     */
    private Player owner;
    
    /**
     * Constructor of the Piece.
     */
    public BlackKing() {
            URL url = BlackKing.class.getResource("/resources/black_king.png");
            pieceImage = new ImageIcon(url);
            pieceLabel = new JLabel("", pieceImage, JLabel.CENTER);
            owner = new Player("Black");
    }
    
    /**
     * Getter for the image of the piece.
     * @return The Image of the piece.
     */
    public JLabel getPieceLabel() {
        return pieceLabel;
    }  
    
    /**
     * Getter for the owner of the Piece.
     * @return the Player.
     */
    public Player getOwner() {
        return owner;
    }
    
    /**
     * Check if the move is valid.
     * @param newSquare square to move
     * @param oldSquare square from
     * @param boardPositions The map of the board
     * @return if the move is valid
     */ 
    public boolean move(Square newSquare, Square oldSquare, boolean[][] boardPositions) {
        
        /* Checks if it's the White Player Turn */
        if (oldSquare.getTurn() % 2 == 0) {
            return false;
        }
        
        //Check if the movement of the Pawn is valid
        int oldRow = oldSquare.getPosition() / 10;
        int oldCol = oldSquare.getPosition() % 10;
        
        int newRow = newSquare.getPosition() / 10;
        int newCol = newSquare.getPosition() % 10;
        
        int rowShift = newRow - oldRow;
        int colShift = newCol - oldCol;
        
        // Check valid diagonal movement (bishop's movement)
        if (Math.abs(rowShift) <= 1 && Math.abs(colShift) <= 1) {
            if (boardPositions[newRow][newCol]) {
                String newS = newSquare.getPiece().getOwner().getPlayerName();
                String oldS = oldSquare.getPiece().getOwner().getPlayerName();
                if (!newS.equals(oldS)) {
                    return true;
                }
            } else {
                return true;
            }              
        }   
        return false; 
    }
}