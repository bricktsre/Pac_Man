import java.awt.Color;
import java.awt.Graphics;

public class GameSquare {
	private final int length = 36;		//length of a game square
	private int cdx;						//Center block top left hand corner x-coordinate
	private int cdy;						//Center block top left hand corner y-coordinate	
	private boolean iswall;				//Is this GameSquare a wall or not
	private boolean haspoint;			//Does this GameSquare contain a point or not
	
	public GameSquare(boolean a, boolean b, int y,int x) {
		iswall=a;
		haspoint=b;
		cdx=x*36+16;
		cdy=y*36+16;
		
	}
	
	/*Draws the GameSquares
	 *Square is either a wall(blue border), has a point(yellow circle inside), or empty 
	 */
	public void draw(Graphics g, int y, int x) {
		if(iswall) {
			g.setColor(Color.BLUE);
			g.drawRect(x*length, y*length, length, length);
		}else if(haspoint) {
			g.setColor(Color.WHITE);
			g.fillOval(x*length+15, y*length+15, 10, 10);
		}
	}
	
	public boolean isWall() {
		return iswall;
	}
	
	public boolean hasPoint() {
		return haspoint;
	}
	
	public void removePoint() {
		haspoint=false;
	}
	
	public int getCdx() {
		return cdx;
	}
	
	public int getCdy() {
		return cdy;
	}
}
