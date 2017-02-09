/*  © LJ MacGillivray
 * 	November 2016
 * 	Button Handler
 */

import java.awt.event.*;
import javax.swing.*;

public class ButtonHandler implements ActionListener
{
    // instance variables - replace the example below with your own
    private TicTacToe ttt;
    private JButton button;

    public ButtonHandler(TicTacToe newTTT, JButton newButton)
    {
        ttt = newTTT;
        button = newButton;
    }

    // Check if button is clicked
    public void actionPerformed(ActionEvent e){
        ttt.ButtonClick(button);
    }
}