import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class Ghost extends Character{
	private Color c;					//Color of the ghost
	private LinkedList<Node> path;		//Path of nodes leading towards the pacman character
	private boolean edible;				//Can the ghost be eaten or not
	private boolean eaten;				//Has the ghost been eaten
	private int edibleVisualCounter;	//Counter used for displaying the ghost when it is edible
	
	public Ghost(int initialdx, int initialdy, Color ghostcolor,Node n) {
		super(initialdx,initialdy, n);
		c=ghostcolor;
		edible=false;
		edibleVisualCounter=1;
	}
	
	/* Checks whether the target node is still valid
	 * Can only update the targetnode if the ghost is at a node
	 * If path isn't empty pop and set as new direction otherwise stop moving
	 */
	@Override
	protected void checkTargetNode() {
		if(((x-13)/25==targetNode.getCol()) && ((y-13)/25==targetNode.getRow()) &&((x-13)%25==0) && ((y-13)%25==0)) {
			nodeAt = targetNode;
			if(!path.isEmpty()) {
				targetNode = path.pop();
				d = nodeAt.directionOfNode(targetNode);
			}else {
				targetNode=null;
				d=Direction.NONE;
			}
		}
	}
	
	//Sets a new targetNode for the ghost
	public void setTarget(Node n) {
		targetNode = n;
	}
	
	//Sets the path of the ghost
	public void setPath(LinkedList<Node> p) {
		if(!p.isEmpty()){
			path = p;
			targetNode = path.pop();
			d = nodeAt.directionOfNode(targetNode);
		}
	}
	
	//Returns the node the ghost is at
	public Node getNodeAt() {
		return nodeAt;
	}

	//Return targetnode variable
	public Node getTargetNode() {
		return targetNode;
	}

	//Draw the ghost as circle with the color specified by the object
	public void draw(Graphics g) {
		if(eaten) 
			g.setColor(Color.GRAY);
		else if(edible) {
			if(edibleVisualCounter<=5)
				g.setColor(Color.WHITE);
			else
				g.setColor(c);
			edibleVisualCounter++;
			if(edibleVisualCounter==12)
				edibleVisualCounter=0;
		}else 
			g.setColor(c);
		g.fillOval(x-11, y-11, imgwidth, imgwidth);
	}

	//Returns the edible variable
	public boolean getEdible() {
		return edible;
	}

	//Sets the eaten varaible to the argument
	public void setEaten(boolean b) {
		eaten = b;
	}

	//Sets the edible variable to the argument
	public void setEdible(boolean b) {
		edible =b;
	}

	//Returns the value of the eaten variable
	public boolean getEaten() {
		return eaten;
	}
}
