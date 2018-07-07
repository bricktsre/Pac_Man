import java.awt.Color;
import java.awt.Graphics;

public class pacman extends Character {
	private final Color c = Color.YELLOW;
	protected DiagDirection diagdir;		//Direction of character diagonally if applicable
	private int score;				//Score of the pac man
	private int lives;				//Lives of the pac man
	
	public pacman(CardinalDirection a, int initialdx, int initialdy, int w) {
		super(a,initialdx,initialdy, w);
		diagdir = DiagDirection.NONE;
		score=0;
		lives=3;
	}
	
	//Moves the pacman diagonally
	public void moveDiag() {
		imgdx += diagdir.dx*speed;
		imgdy += diagdir.dy*speed;
		coldx += diagdir.dx*speed;
		coldy += diagdir.dy*speed;
		cdx += diagdir.dx*speed;
		cdy += diagdir.dy*speed;
		squarein = ((cdy/36)*width)+(cdx/36);
	}
	
	//Update the direction of the character
	public void changeDiagDir(DiagDirection a) {
		diagdir=a;
	}
	
	//Returns the current diagonal direction of the pac man
	public DiagDirection getDiagdir() {
		return diagdir;
	}
	
	/*Based off the current direction of the character figure out whether
	*the characters cdx or cdy needs to be compared and compare it to the target
	*game square provided as g
	*/
	public int atCenterLineD(GameSquare g) {
		int a = d.directionToConsiderDiagonal();
		if(a==0)
			return Integer.compare(cdy, g.getCdy());
		else 
			return Integer.compare(cdx, g.getCdx());
	}
	
	//Set the initial diagonal direction of pacman when a change of direction is first made
	public void setDiag(int a) {
		diagdir=diagdir.setDiag(a, d);
	}
	
	//Increases the score of the pacman by parameter int a
	public void increaseScore(int a) {
		score+=a;
	}
	
	//Decreases the lives variable by one
	public void loseLife() {
		lives--;
	}
	
	//Returns the score variable
	public int getScore() {
		return score;
	}
	
	//Return the lives variable
	public int getLife() {
		return lives;
	}
	
	//Draws the pacman character
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval(imgdx, imgdy, imgwidth, imgwidth);
	}
}
