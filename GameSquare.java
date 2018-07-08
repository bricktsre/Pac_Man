import java.awt.Color;
import java.awt.Graphics;

public class GameSquare {
	private final int length = 27;		//length of a game square
	private int centerdx;					//Center block top left hand corner x-coordinate
	private int centerdy;					//Center block top left hand corner y-coordinate	
	private boolean iswall;				//Is this GameSquare a wall 
	private boolean hasdot;				//Does this GameSquare contain a dot 
	private boolean hasbigdot;			//Does this GameSquare have a big dot
	
	
	public GameSquare(boolean a, boolean b, boolean c, int y,int x) {
		iswall=a;
		hasdot=b;
		hasbigdot=c;
		centerdx=x*27+12;
		centerdy=y*27+12;
		
	}
	
	/*Draws the GameSquares
	 *Square is either a wall(blue border), has a point(yellow circle inside), or empty 
	 */
	public void draw(Graphics g, int y, int x) {
		if(iswall) {
			g.setColor(Color.BLUE);
			g.drawRect(x*length, y*length, length, length);
		}else if(hasdot) {
			g.setColor(Color.WHITE);
			g.fillOval(x*length+8, y*length+8, 11, 11);
		}
		else if(hasbigdot){
			g.setColor(Color.WHITE);
			g.fillOval(x*length+3, y*length+3, 20, 20);
		}	
	}
	
	public boolean isWall() {
		return iswall;
	}
	
	public boolean hasDot() {
		return hasdot;
	}
	
	public void removeDot() {
		hasdot=false;
	}
	
	public boolean hasBigDot() {
		return hasbigdot;
	}
	
	public void removeBigDot() {
		hasbigdot=false;
	}
	
	public int getCdx() {
		return centerdx;
	}
	
	public int getCdy() {
		return centerdy;
	}
}
