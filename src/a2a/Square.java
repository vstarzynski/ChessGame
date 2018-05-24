package a2a;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Implements a Square.
 * The Square class is responsible for checking if the square is occupied.
 * @author Victor Starzynski
 * @version 1.0
 *
 */
public class Square extends JPanel implements MouseListener, 
                                        java.io.Serializable {
    
   
    /**
     * Default Serial.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * The turn of the game.
     */
    private static int turn;
    
    /**
     * Defines if a square is selected or not.
     */
    private static Square selectedSquare;

    /**
     * Piece contained in the square.
     */
    private Piece squarePiece;   
    
    /**
     * Default color of the square.
     */
    private Color defaultColor;
    
    /**
     * References the Board that contains this Square.
     */
    private Board squareBoard;
    
    /**
     * Position of the square in the board.
     */
    private int squarePosition;
    
    /**
     * Unique Chess game that contains this square.
     */
    private Chess chess;
    
    /**
     * Constructor of the class.
     * @param newChess - chess Gane.
     * @param color - Color of the Square.
     * @param board - Board that has all the Squares
     * @param initialPosition - Initial Position of the square.
     */
    public Square(Chess newChess, Board board, Color color,
                        int initialPosition) {
        defaultColor = color;
        squarePosition = initialPosition;
        setBackground(defaultColor);     
        addMouseListener(this);
        squareBoard = board;
        chess = newChess;
        
        // Places all the black pieces in their initial positions
        if (initialPosition == 0 || initialPosition == 07) {
            squarePiece = new BlackRook(); 
            add(squarePiece.getPieceLabel());
        } else if (initialPosition == 01 || initialPosition == 06) {
            squarePiece = new BlackKnight();
            add(squarePiece.getPieceLabel()); 
        } else if (initialPosition == 02 || initialPosition == 05) {
            squarePiece = new BlackBishop();
            add(squarePiece.getPieceLabel());  
        } else if (initialPosition == 03) {
            squarePiece = new BlackQueen();
            add(squarePiece.getPieceLabel());
        } else if (initialPosition == 04) {
            squarePiece = new BlackKing();
            add(squarePiece.getPieceLabel());  
        } else if (initialPosition >= 10 && initialPosition <= 17) {
            squarePiece = new BlackPawn();
            add(squarePiece.getPieceLabel());
        }
        
        // Places all the white pieces in their initial positions
        if (initialPosition == 70 || initialPosition == 77) {
            squarePiece = new WhiteRook();
            add(squarePiece.getPieceLabel());      
        } else if (initialPosition == 71 || initialPosition == 76) {
            squarePiece = new WhiteKnight();
            add(squarePiece.getPieceLabel());     
        } else if (initialPosition == 72 || initialPosition == 75) {
            squarePiece = new WhiteBishop();
            add(squarePiece.getPieceLabel()); 
        } else if (initialPosition == 73) {
            squarePiece = new WhiteQueen();
            add(squarePiece.getPieceLabel());   
        } else if (initialPosition == 74) {
            squarePiece = new WhiteKing();
            add(squarePiece.getPieceLabel()); 
        } else if (initialPosition >= 60 && initialPosition <= 67) {
            squarePiece = new WhitePawn();
            add(squarePiece.getPieceLabel());
        }  
        
        if(squarePiece != null) {
            squareBoard.updatePositions(initialPosition / 10, initialPosition % 10, true);
        } else {
            squareBoard.updatePositions(initialPosition / 10, initialPosition % 10, false);
        }
    } 
    
    public int getTurn (){
        return turn;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    // Used mousePressed because the click was not firing fast enough.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        setBackground(Color.gray);
        turn = squareBoard.getSavedTurn();
        
        if (selectedSquare == null) {
            if (squarePiece != null) {
                selectedSquare = this;
            } else {
                setBackground(defaultColor);
            }
        } else if (selectedSquare.squarePosition == this.squarePosition) {
            selectedSquare = null;
            setBackground(defaultColor);
        } else {
            if (selectedSquare.squarePiece.move(this, selectedSquare, squareBoard.getBoardPositions())) {
                addPiece();
                removePieceFromSelected();
                squareBoard.updatePositions(squarePosition / 10, squarePosition % 10, true);
                squareBoard.updatePositions(selectedSquare.squarePosition / 10, selectedSquare.squarePosition % 10, false);
                setBackground(defaultColor);
                selectedSquare.setBackground(selectedSquare.defaultColor);
                selectedSquare = null;
                turn++;
                squareBoard.saveTurn(turn);
                System.out.println("Turn: \n" + turn);
                
                chess.saveGame(chess);
                
                boolean[][] temp = squareBoard.getBoardPositions();
                
            } else {
                setBackground(defaultColor);
                selectedSquare.setBackground(selectedSquare.defaultColor);
                selectedSquare = null;
            }
        }
    }
    
    /**
     * Displays an error message in a dialog box.
     * 
     * @param msg
     *            String - error msg to display
     */
    public void displayErrorMsg(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
     // Unused
    }

    @Override
    public void mouseEntered(MouseEvent e) {
     // Unused  
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Unused
    }
    
    /**
     * Getter for the position of the square.
     * @return the position.
     */
    public int getPosition() {
        return squarePosition;
    }

    /**
     * Getter for the Piece of the square.
     * @return the piece on the square.
     */
    public Piece getPiece() {
        return squarePiece;
    }
    
    /**
     * Removes the piece from a previously selected Square.
     */
    private void removePieceFromSelected() {    
        selectedSquare.remove(selectedSquare.squarePiece.getPieceLabel());
        selectedSquare.squarePiece = null;
        selectedSquare.repaint();
    }
    
    /**
     * Adds the piece from a selected Square.
     */
    private void addPiece() {
        if (squarePiece != null) {
            remove(squarePiece.getPieceLabel());
        }
        add(selectedSquare.squarePiece.getPieceLabel());
        squarePiece = selectedSquare.squarePiece;
        repaint();
    }
    
}
