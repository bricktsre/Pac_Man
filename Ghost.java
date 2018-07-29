import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class Ghost extends Character{
	private Color c;	//Color of the ghost
	private LinkedList<Node> path;
	
	public Ghost(int initialdx, int initialdy, Color ghostcolor,Node n) {
		super(initialdx,initialdy, n);
		c=ghostcolor;
	}
	
	public void move() {
		checkTargetNode();
		if(nodeAt.compareTo(targetNode)==0)
			return;
		else {
			if(nodeAt.getRow()<targetNode.getRow())
				y+=speed;
			else if(nodeAt.getRow()>targetNode.getRow())
				y-=speed;
			else if(nodeAt.getCol()<targetNode.getCol())
				x+=speed;
			else if(nodeAt.getCol()>targetNode.getCol())
				x-=speed;
		}
	}
	
	private void checkTargetNode() {
		if(((x-13)/25==targetNode.getCol()) && ((y-13)/25==targetNode.getRow()) &&((x-13)%25==0) && ((y-13)%25==0)) {
			nodeAt = targetNode;
			if(path.isEmpty())
				return;
			targetNode = path.pop();
		}
	}
	
	public void setTarget(Node n) {
		targetNode = n;
	}
	
	public void setPath(LinkedList<Node> p) {
		path =p;
		targetNode = path.pop();
	}
	
	public Node getNodeAt() {
		return nodeAt;
	}

	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval(x-11, y-11, imgwidth, imgwidth);
	}
}
