import java.awt.Color;
import java.awt.Graphics;

public class GameSquare {
	private final int length = 27;		//length of a game square
	private int cdx;					//Center block top left hand corner x-coordinate
	private int cdy;					//Center block top left hand corner y-coordinate	
	private boolean iswall;				//Is this GameSquare a wall 
	private boolean hasdot;				//Does this GameSquare contain a dot 
	private boolean hasbigdot;			//Does this GameSquare have a big dot
	
	
	public GameSquare(boolean a, boolean b, boolean c, int y,int x) {
		iswall=a;
		hasdot=b;
		hasbigdot=c;
		cdx=x*36+12;
		cdy=y*36+12;
		
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
	
	public boolean hasPoint() {
		return hasdot;
	}
	
	public void removePoint() {
		hasdot=false;
	}
	
	public int getCdx() {
		return cdx;
	}
	
	public int getCdy() {
		return cdy;
	}
}
