/*
 * Main JPanel game is housed in
 */

import java.awt.*;
import javax.swing.*;

public class MainJFrame extends JFrame{
	private gamePanel gp;
	
	//Instantiates a gamePanel object that houses the game
	public MainJFrame(String s) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        
		MapReader m = new MapReader(s);
        gp = new gamePanel( this,m);
        gp.setPreferredSize(new Dimension(700, 825));
        
        getContentPane().add( gp );
        pack();
	}
	
	//Creates a MainJFrame object and then displays it
	public static void main(String[] args) {
		//MainJFrame f = new MainJFrame(args[0]);
		MainJFrame f = new MainJFrame("map.txt");
        f.display();
	}
	
	public void display() {
        EventQueue.invokeLater(new Runnable() {
                public void run() {
                    setVisible(true);
                }
            });
    }

}
