/*
 * JPanel holding the game itself
 */
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;



@SuppressWarnings("serial")
public class gamePanel extends JPanel {
	private MainJFrame parent;					//reference to JFrame this panel is in
	private javax.swing.Timer timer;			//Timer to run the game
	private gameEnvironment environ;			//class containing the objects to be drawn and moved
	private JLabel score;						//JLabel displaying the score
	private JLabel lives;						//JLabel displaying lives left of the pacman
	
	public gamePanel(MainJFrame parentFrame, MapReader m) {
		parent = parentFrame;			
		setBackground(Color.BLACK);
		setLayout(null);
		environ = new gameEnvironment(m);
		
		addKeyListener(new keylistener());					//keylistener for changing direction
		
		int[] a = m.getHeightWidth();
		score = new JLabel("Score: " + environ.getScore());
		score.setBounds(100, a[0]*36+5, 200, 45);
		score.setFont(new Font("Dialog", Font.PLAIN,24));
		score.setForeground(Color.WHITE);
		lives = new JLabel("Lives: " + environ.getLives());
		lives.setBounds(a[1]*36-200, a[0]*36+5, 100, 45);
		lives.setFont(new Font("Dialog", Font.PLAIN,24));
		lives.setForeground(Color.WHITE);
		
		timer = new javax.swing.Timer(40, new TimerListener(this));
		timer.start();
		setFocusable(true);
		
		this.add(score);
		this.add(lives);
	}
	
	@Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        environ.draw(g);
    }

	private class TimerListener implements ActionListener{
		private gamePanel gameP;				//reference to gamePanel
		
		public TimerListener(gamePanel p) {
			gameP = p;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {		//Main method running the whole game
			environ.update();
			score.setText("Score: " + environ.getScore());
			lives.setText("Lives: " + environ.getLives());
			repaint();
			if(environ.getLives()==0) {
				timer.stop();
				buildDialog();
			}
		}
	}
	
	public void buildDialog()
	  {
	    JOptionPane optionPane = new JOptionPane(new JLabel("GAME OVER",JLabel.CENTER));
	    JDialog dialog = optionPane.createDialog("");
	    dialog.setModal(true);
	    dialog.setVisible(true);
	    System.exit(0);
	  }
	
	//Changes the direction of the pacman character in accordance with the arrow keys pressed
	private class keylistener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){  
        	if(e.getKeyCode() == KeyEvent.VK_LEFT)
        	    environ.changeD(CardinalDirection.LEFT);                
        	else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        	    environ.changeD(CardinalDirection.RIGHT);                
        	else if(e.getKeyCode() == KeyEvent.VK_UP)
        	    environ.changeD(CardinalDirection.UP);                
        	else if(e.getKeyCode() == KeyEvent.VK_DOWN)
        	    environ.changeD(CardinalDirection.DOWN);
        }
    }
}
