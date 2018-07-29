//Class containing many methods necessary for the movement of the characters around the map
import java.awt.Graphics;

public class Character {
	protected final int speed=1;		//Set speed of the character
	protected final int imgwidth = 22;	//Width of the image
	protected int x;				//x-coordinate of the character
	protected int y;				//y-coordinate of the character
	protected int initialdx;			//Initial x-coordinate of the character
	protected int initialdy;			//Initial y-coordinate of the character
	protected Node initialNode;
	protected Node nodeAt;
	protected Node targetNode;
	
	public Character(int x, int y, Node n) {
		initialdx=x;
		initialdy=y;
		this.x = initialdx;
		this.y = initialdy;
		nodeAt=n;
		initialNode=n;
	}
	
	//Moves the character with a simply calculation of speed times direction
	public void move() {}
	
	public Node getNodeAt() {
		return nodeAt;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	//Resets the character back to it's initial position
	public void resetPosition() {
		x = initialdx;
		y = initialdy;
		nodeAt=initialNode;
	}
	
	//Empty method for sub classes to implement
	public void draw(Graphics g) {}

}
