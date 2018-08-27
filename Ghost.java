import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class Ghost extends Character{
	private Color c;	//Color of the ghost
	private LinkedList<Node> path;		//Path of nodes leading towards the pacman character
	
	public Ghost(int initialdx, int initialdy, Color ghostcolor,Node n) {
		super(initialdx,initialdy, n);
		c=ghostcolor;
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
		g.setColor(c);
		g.fillOval(x-11, y-11, imgwidth, imgwidth);
	}
}
