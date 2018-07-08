//Class containing many methods necessary for the movement of the characters around the map
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Character {
	protected final int speed=3;		//Set speed of the character
	protected CardinalDirection d;		//Direction the character is moving in
	protected int chardx;				//Upper left hand corner x-coordinate of the character
	protected int chardy;				//Upper left hand corner y-coordinate of the character
	protected final int imgwidth = 27;	//Width of the image
	protected int squarein;				//What square the character is in
	protected int centerdx;				//Center block top left hand corner x-coordinate
	protected int centerdy;				//Center block top left hand corner y-coordinate
	protected int width;				//How many GameSquares wide the map is
	protected int initialdx;			//Initial Upper left hand corner x-coordinate of the character
	protected int initialdy;			//Initial Upper left hand corner y-coordinate of the character
	
	
	public Character(CardinalDirection a, int startdx, int startdy, int w) {
		d = a;
		initialdx=startdx;
		initialdy=startdy;
		chardx = initialdx;
		chardy = initialdy;
		centerdx = chardx+12;
		centerdy = chardy+12;
		width = w;
		squarein = ((centerdy/36)*width)+(centerdx/36);		
	}
	
	//Moves the character with a simply calculation of speed times direction
	public void move() {
		chardx += d.dx*speed;
		chardy += d.dy*speed;
		centerdx += d.dx*speed;
		centerdy += d.dy*speed;
		squarein = ((centerdy/36)*width)+(centerdx/36);
	}
	
	//Returns the top left corner of the collision box after a move
	public int[] testMove() {
		int[]a = new int[2];
		a[0] = chardx + (d.dx*speed);
		a[1] = chardy + (d.dy*speed);
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
	
	//returns the current centerdx
	public int getCenterdx() {
		return centerdx;
	}
		
	//Returns the current centerdy
	public int getCenterdy() {
		return centerdy;
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
			return Integer.compare(centerdy, g.getCdy());
		else 
			return Integer.compare(centerdx, g.getCdx());
	}
	
	//Resets the character back to it's initial position
	public void resetPosition() {
		chardx = initialdx;
		chardy = initialdy;
		centerdx = chardx+12;
		centerdy = chardy+12;
		squarein = ((centerdy/36)*width)+(centerdx/36);	
	}
	
	//Empty method for sub classes to implement
	public void draw(Graphics g) {}

}
