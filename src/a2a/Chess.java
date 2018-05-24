/**
 * 
 */
package a2a;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

/**
 * Implements a Chess Game.
 * @author Victor Starzynski
 * @version 1.0
 */
public class Chess implements java.io.Serializable {
    
    /**
     * Generic Serial Version.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Board of our chess game.
     */
    //This variable is not used yet, but I intend to use it
    //for save states.
    private Board gameBoard;
    
    /**
     * Constructor of the class.
     */
    public Chess() {
        //No actions yet.
        //Can be used to choose how many games are going
        //to be played at the same time.
    }
    
    /**
     * Starts the game.
     */
    public void startGame() {
        this.createBoard();
    }
    
    /**
     * Create the game board.
     */
    public void createBoard() {
        gameBoard = new Board(this);
    }
    
    /**
     * Resumes the previous game.
     */
    public void continueGame() {
        gameBoard.showBoard();
    }
    
    /**
     * Displays an error message in a dialog box.
     * 
     * @param msg
     *            String - error msg to display
     */
    public static void displayErrorMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error",
                JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Saves the state of the game.
     * @param chess A Chess game.
     * @return If the game was succesfully saved.
     */
    public boolean saveGame(Chess chess) {
        System.out.println("Saving....");
        try {
            FileOutputStream fileOut = new FileOutputStream("chess.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(chess);
            out.close();
            fileOut.close();
            System.out.println("Game saved!\n");
         }catch(IOException i) {
            i.printStackTrace();
            return false;
         }
        return true;   
    }
    
    
    /**
     * Main method.
     * @param args - unused
     */
    public static void main(String[] args) {
       
        int choice;
        
        String message = "Do you want to continue the previous game?";
        String title = "Continue...";
        // display the JOptionPane showConfirmDialog
        choice = JOptionPane.showConfirmDialog(null, message,
                    title, JOptionPane.YES_NO_OPTION);
   
        Chess chess = null;
        
        if (choice == 0) {
            System.out.println("Loading....");
            try {
                FileInputStream fileIn = new FileInputStream("chess.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                chess = (Chess) in.readObject();
                chess.continueGame();
                in.close();
                fileIn.close();
             } catch(Exception i) {
                displayErrorMsg("Chess game not found...\n"
                                    + "Creating a new game!");
                chess = new Chess();
                chess.startGame();
             }           
        } else {
            chess = new Chess();
            chess.startGame();           
        }
    }
}
