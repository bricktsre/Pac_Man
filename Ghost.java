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
	
	public void setTarget(Node n) {
		targetNode = n;
	}
	
	public void setPath(LinkedList<Node> p) {
		if(!p.isEmpty()){
			path = p;
			targetNode = path.pop();
			d = nodeAt.directionOfNode(targetNode);
		}
	}
	
	public Node getNodeAt() {
		return nodeAt;
	}

	public Node getTargetNode() {
		return targetNode;
	}
	
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval(x-11, y-11, imgwidth, imgwidth);
	}
}
