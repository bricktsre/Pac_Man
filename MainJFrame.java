/*
 * Main JPanel game is housed in
 */

import java.awt.*;
import javax.swing.*;

public class MainJFrame extends JFrame{
	private gamePanel gp;
	
	//Instantiates a gamePanel object that houses the game
	public MainJFrame(int w, int h) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        
		MapReader m = new MapReader("map.txt");
		int[] a = m.getHeightWidth();
        gp = new gamePanel( this,m);
        gp.setPreferredSize(new Dimension(a[1]*36, a[0]*36+50));
        
        getContentPane().add( gp );
        pack();
	}
	
	//Creates a MainJFrame object and then displays it
	public static void main(String[] args) {
		int width,height;
		width = 20 ;//Integer.parseInt(args[0]);				//number of squares wide the game should be
		height = 20; //Integer.parseInt(args[1]);				//number of squares high the game should be
		MainJFrame f = new MainJFrame(width,height);
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
