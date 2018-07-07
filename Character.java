//Class containing many methods necessary for the movement of the characters around the map
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Character {
	protected final int speed=4;		//Set speed of the character
	protected CardinalDirection d;		//Direction the character is moving in
	protected int imgdx;				//Upper left hand corner x-coordinate of the image of the character
	protected int imgdy;				//Upper left hand corner y-coordinate of the image of the character
	protected final int imgwidth = 36;	//Width of the image
	protected int coldx;				//Upper left hand corner x-coordinate of the collision box of the character
	protected int coldy;				//Upper left hand corner y-coordinate of the collision box of the character
	protected int squarein;				//What square the character is in
	protected int cdx;					//Center block top left hand corner x-coordinate
	protected int cdy;					//Center block top left hand corner y-coordinate
	protected int width;				//How many GameSquares wide the map is
	protected int initialdx;			//Initial Upper left hand corner x-coordinate of the image of the character
	protected int initialdy;			//Initial Upper left hand corner y-coordinate of the image of the character
	
	
	public Character(CardinalDirection a, int startdx, int startdy, int w) {
		d = a;
		initialdx=startdx;
		initialdy=startdy;
		imgdx = initialdx;
		imgdy = initialdy;
		coldx = imgdx;
		coldy = imgdy;
		cdx = coldx+16;
		cdy = coldy+16;
		width = w;
		squarein = ((cdy/36)*width)+(cdx/36);		
	}
	
	//Moves the character with a simply calculation of speed times direction
	public void move() {
		imgdx += d.dx*speed;
		imgdy += d.dy*speed;
		coldx += d.dx*speed;
		coldy += d.dy*speed;
		cdx += d.dx*speed;
		cdy += d.dy*speed;
		squarein = ((cdy/36)*width)+(cdx/36);
	}
	
	//Returns the top left corner of the collision box after a move
	public int[] testMove() {
		int[]a = new int[2];
		a[0] = coldx + (d.dx*speed);
		a[1] = coldy + (d.dy*speed);
		return a;
	}
	
	//Update the direction of the character
	public void changeDirection(CardinalDirection a) {
		d=a;
	}
	
	//Returns the current direction of the character
	public CardinalDirection getDirection() {
		return d;
	}
	
	//returns the current cdx
	public int getCdx() {
		return cdx;
	}
		
	//Returns the current cdy
	public int getCdy() {
		return cdy;
	}
	
	//Returns the squarein variable
	public int getSquareIn() {
		return squarein;
	}
	
	/*Based off the current direction of the character figure out whether
	*the characters cdx or cdy needs to be compared and compare it to the target
	*game square provided as g
	*/
	public int atCenterLineC(GameSquare g, CardinalDirection dir) {
		int a = dir.directionToConsiderCardinal();
		if(a==0)
			return Integer.compare(cdy, g.getCdy());
		else 
			return Integer.compare(cdx, g.getCdx());
	}
	
	//Resets the character back to it's initial position
	public void resetPosition() {
		imgdx = initialdx;
		imgdy = initialdy;
		coldx = imgdx;
		coldy = imgdy;
		cdx = coldx+16;
		cdy = coldy+16;
		squarein = ((cdy/36)*width)+(cdx/36);	
	}
	
	//Empty method for sub classes to implement
	public void draw(Graphics g) {}

}
