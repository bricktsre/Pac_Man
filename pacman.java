import java.awt.Color;
import java.awt.Graphics;

public class pacman extends Character {
	private final Color c = Color.YELLOW;
	private Node nextTargetNode;
	private int score;				//Score of the pac man
	private int lives;				//Lives of the pac man
	
	public pacman(int initialdx, int initialdy, Node n) {
		super(initialdx,initialdy,n);
		score=0;
		lives=3;
	}

	/* The method only does something if the pacman is at the target node so that is checked first
	 * Then there are three cases
	 * Case 1: The pacman has another node in line to be the target node
	 * Case 2: The pacman has another node in the direction it is traveling to be used as another target node
	 * Case 3: There is no new target node
	 */
	@Override
	protected void checkTargetNode() {
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
	
	
	/* Changes the Target node of the pacman character
	 * Parameters are a node to be the new target node and a direction to be the new direction
	 * If targetnode has a value then nextTargetNode is used to store the value
	 */
	public void changeTarget(Node n, Direction d) {
		if(targetNode==null) {
			targetNode = n;
			this.d=d;
		}else
			nextTargetNode=n;
	}
	
	//Sets the node at which the pacman is at
	public void setNodeAt(Node n) {
		nodeAt=n;
	}
	
	//Returns the target node of the pacman
	public Node getTargetNode() {
		return targetNode;
	}
	
	//Changes the nextTargetNode variable
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
}
