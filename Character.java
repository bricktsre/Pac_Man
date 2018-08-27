//Class containing many methods necessary for the movement of the characters around the map
import java.awt.Graphics;

public class Character {
	protected final int speed=1;		//Set speed of the character
	protected final int imgwidth = 22;	//Width of the image
	protected int x;					//x-coordinate of the character
	protected int y;					//y-coordinate of the character
	protected int initialdx;			//Initial x-coordinate of the character
	protected int initialdy;			//Initial y-coordinate of the character
	protected Node initialNode;			//Starting node of the character
	protected Node nodeAt;				//Node the character is at or came from
	protected Node targetNode;			//Node the character is going to
	protected Direction d;				//Direction enum of the character
	
	public Character(int x, int y, Node n) {
		initialdx=x;
		initialdy=y;
		this.x = initialdx;
		this.y = initialdy;
		nodeAt=n;
		initialNode=n;
	}
	
	//Moves the character with a simple calculation of speed times direction
	public void move() {
		if(targetNode==null)
			return;
		else {
			x+=speed*d.dx;
			y+=speed*d.dy;
		}
		checkTargetNode();
	}
	
	//Superclass method for subclass inplementation
	protected void checkTargetNode() {}
	
	//Return nodeat variable
	public Node getNodeAt() {
		return nodeAt;
	}
	
	//Return x-coordinate of character
	public int getX() {
		return x;
	}
	
	//Return the y-coordinate of the character
	public int getY() {
		return y;
	}
	
	//Resets the character back to it's initial position
	public void resetPosition() {
		x = initialdx;
		y = initialdy;
		nodeAt=initialNode;
	}
	
	//Return te direction of the character
	public Direction getDirection() {
		return d;
	}
	
	//Set the direction of the  character
	public void setDirection(Direction d) {
		this.d = d;
	}
	
	//Empty method for sub classes to implement
	public void draw(Graphics g) {}

}
