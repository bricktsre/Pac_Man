import java.awt.Color;
import java.awt.Graphics;

public class pacman extends Character {
	private final Color c = Color.YELLOW;
	private Node nextTargetNode;
	private Direction d;
	private int score;				//Score of the pac man
	private int lives;				//Lives of the pac man
	
	public pacman(int initialdx, int initialdy, Node n) {
		super(initialdx,initialdy,n);
		score=0;
		lives=3;
	}
	
	public void move() {
		if(targetNode==null)
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
		checkTargetNode();
		
	}
	
	private void checkTargetNode() {
		if(((x-13)/25==targetNode.getCol()) && ((y-13)/25==targetNode.getRow()) &&((x-13)%25==0) && ((y-13)%25==0)) { 
			nodeAt = targetNode;
			if(nextTargetNode!=null) {
				targetNode=nextTargetNode;
				nextTargetNode=null;
				d = nodeAt.directionOfNode(targetNode);
			}else if(nodeAt.neighborInDirection(d)!=null)
				targetNode=nodeAt.neighborInDirection(d);
			else {
				targetNode=null;
				d=Direction.NONE;
			}
		}
	}
	
	public void changeTarget(Node n, Direction d) {
		if(targetNode==null) {
			targetNode = n;
			this.d=d;
		}else
			nextTargetNode=n;
	}
	
	public void setNodeAt(Node n) {
		nodeAt=n;
	}
	
	public Node getTargetNode() {
		return targetNode;
	}
	
	public void changeNextTargetNode(Node n) {
		nextTargetNode = n;
	}
	
	public void targetNodeNull() {
		targetNode=null;
	}
	
	//Increases the score of the pacman by parameter int a
	public void increaseScore(int a) {
		score+=a;
	}
	
	//Decreases the lives variable by one
	public void loseLife() {
		lives--;
	}
	
	//Returns the score variable
	public int getScore() {
		return score;
	}
	
	//Return the lives variable
	public int getLife() {
		return lives;
	}
	
	//Draws the pacman character
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval(x-11, y-11, imgwidth, imgwidth);
	}

	public Direction getDirection() {
		return d;
	}
}
