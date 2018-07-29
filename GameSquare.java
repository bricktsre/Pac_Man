import java.awt.Color;
import java.awt.Graphics;

public class GameSquare {
	private final int length = 25;		//length of a game square
	private int dx;					//Center block top left hand corner x-coordinate
	private int dy;					//Center block top left hand corner y-coordinate	
	private boolean iswall;				//Is this GameSquare a wall 
	private boolean hasdot;				//Does this GameSquare contain a dot 
	private boolean hasbigdot;			//Does this GameSquare have a big dot
	
	
	public GameSquare(boolean a, boolean b, boolean c, int y,int x) {
		iswall=a;
		hasdot=b;
		hasbigdot=c;
		dx=x*25;
		dy=y*25;
		
	}
	
	/*Draws the GameSquares
	 *Square is either a wall(blue border), has a point(white circle inside), or empty 
	 */
	public void draw(Graphics g) {
		if(iswall) {
			g.setColor(Color.BLUE);
			g.drawRect(dx, dy, length, length);
		}else if(hasdot) {
			g.setColor(Color.WHITE);
			g.fillOval(dx+8, dy+8, 11, 11);
		}
		else if(hasbigdot){
			g.setColor(Color.WHITE);
			g.fillOval(dx+3, dy+3, 20, 20);
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
}
