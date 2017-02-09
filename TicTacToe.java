/*  © LJ MacGillivray
 * 	November 2016
 * 	Tic Tac Toe game 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TicTacToe {
    public static final String PLAYER_X = "X"; 	// player using "X"
    public static final String PLAYER_O = "O"; 	// player using "O"
    public static final String EMPTY = " ";  	// empty cell
    public static final String TIE = "T"; 		// game ended in a tie

    private String player;   	// current player (PLAYER_X or PLAYER_O)
    private String winner;   	// winner: PLAYER_X, PLAYER_O, TIE, EMPTY = in progress
    private int numFreeSquares;	// number of squares still free

    
    private JButton board[][];			// 3x3 array representing the board
    private ButtonHandler handle[][]; 	// Buttons on the board (options)
    private JMenuItem quit;				// Quit Option on menu bar
    private JMenuItem newGame;			// New Game option on menu bar
    private JMenu file = new JMenu("");


    private JFrame frame;
    private JMenuBar menuBar = new JMenuBar();
    JLabel label = new JLabel();
    /*
     * Constructs a new Tic-Tac-Toe board.
     */
    public TicTacToe(){
        frame = new JFrame("Tic Tac Toe");
        frame.setLayout(new GridLayout(0, 3));
        board = new JButton[3][3];
        handle = new ButtonHandler[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new JButton();
                handle[i][j] = new ButtonHandler(this, board[i][j]);
            }
        }

        newGame = new JMenuItem("New Game");
        newGame.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        newGame.addActionListener((ActionEvent event) -> {
            clearBoard();
        });
        quit = new JMenuItem("Quit");
        quit.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
        quit.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        
        file.add(quit);

        clearBoard();
    }

    
    private void clearBoard()
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText(EMPTY);
                board[i][j].addActionListener(handle[i][j]);
                
            }
        }
        winner = EMPTY;
        numFreeSquares = 9;
        player = PLAYER_X;     // Player X always has the first turn.

        print();
    }

    
    private boolean haveWinner() 
    {
        
    	// Checks if minimum number of squares have been taken
        if (numFreeSquares>4){
            return false;
        }

        // Checks straight rows and columns if there is winner
        for(int i = 0; i < 3; i++){
            if ((board[i][0].getText().equals(board[i][1].getText()))&&(board[i][0].getText().equals(board[i][2].getText()))&& !(board[i][0].getText().equals(EMPTY))) {
                return true;
            }
            if ((board[0][i].getText().equals(board[1][i].getText()))&&(board[0][i].getText().equals(board[2][i].getText()))&& !(board[0][i].getText().equals(EMPTY))) {
                return true;
            }
        }

        // Checks diagonals for a winner
        if ( board[0][0].getText().equals(board[1][1].getText()) && board[0][0].getText().equals(board[2][2].getText()) && !(board[1][1].getText().equals(EMPTY))){
            return true;
        }
        if ( board[0][2].getText().equals(board[1][1].getText()) && board[0][2].getText().equals(board[2][0].getText()) && !(board[1][1].getText().equals(EMPTY))) {
            return true;
        }

        // Code only gets here if there is no winner and minimum moves have been taken
        return false;
    }

    public void print() 
    {
        Container cPane = frame.getContentPane();

        JPanel panel = new JPanel();
        JScrollPane pane = new JScrollPane();
        panel.add(pane);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cPane.add(board[i][j]);
            }
        }

        label.setText(toString());
        frame.add(label);
        
        menuBar.add(newGame);
        menuBar.add(quit);
        frame.add(menuBar);

        frame.pack();
        frame.setVisible(true);
    }

    public String toString() 
    {
        if(winner.equals(EMPTY)){
            return player + "'s Turn";
        } else if (winner.equals(TIE)){
            return "Game over.  Tie Game";
        } else if (player.equals(PLAYER_X)){
            return "Game over.  O wins.";
        } else {
            return "Game over.  X wins.";
        }
    }

    public void ButtonClick(JButton doTheHarlemShake){

        if (doTheHarlemShake.getText().equals(EMPTY) && winner.equals(EMPTY)){
            doTheHarlemShake.setText(player);
            numFreeSquares--;
            if (haveWinner()) {
                winner = player; // must be the player who just went
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        board[i][j].removeActionListener(handle[i][j]);
                    }
                }
            } else if (numFreeSquares==0) {
                winner = TIE;
            }

            // change to other player (this won't do anything if game has ended
            if (player==PLAYER_X) {
                player=PLAYER_O;
            } else {
                player=PLAYER_X;
            }

        } else { 
            Toolkit.getDefaultToolkit().beep();
        }

        print();

    }

}
