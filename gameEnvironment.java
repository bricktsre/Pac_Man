import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class gameEnvironment {
	private final int height=31;				//Number of GameSquares high the map is
	private final int width=28;					//Number of GameSquares wide the map is
	private GameSquare[][] gsquare;				//2D array of all the squares making up the map
	private pacman pman;						//Pacman character
	private Ghost[] ghosts = new Ghost[2];		//Array of ghosts
	private Character[] characters;				//Array of pacman and ghost characters	
	private Node[] nodes;						//Array of Nodes
	private int timer = 0;							//Int to be used as a makeshift timer
	
	public gameEnvironment(MapReader m) {
		gsquare = new GameSquare[height][width];
		initializeBoard(m);
		initializeNodes();
		pman = new pacman(348,588,nodes[37]);
		pman.changeTarget(nodes[29],Direction.LEFT);
		characters = new Character[1+ghosts.length];
		characters[0]=pman;	
		ghosts[0]= new Ghost(38,38,Color.GREEN,nodes[0]);
		characters[1]=ghosts[0];	
		ghosts[1]= new Ghost(663,38,Color.PINK, nodes[57]);
		characters[2]=ghosts[1];	
	}
	
	/* Initializes the gamesquare array from the provided map
	 * 1 is a wall. 0 is a space with a dot. 6 is a space with a big dot. 3 is an empty space
	 */
	private void initializeBoard(MapReader m) {								
		int[][] b = m.getMap();	
		for(int i = 0;i<height;i++) {
			for(int j =0;j<width;j++) {
				if(b[i][j]==1)
					gsquare[i][j]=new GameSquare(true,false,false,i,j);
				else if(b[i][j]==0)
					gsquare[i][j]=new GameSquare(false,true,false,i,j);
				else if(b[i][j]==6)
					gsquare[i][j]=new GameSquare(false,false,true,i,j);
				else
					gsquare[i][j]=new GameSquare(false,false,false,i,j);
			}
		}
	}
	
	/* Establishes an map containing the nodes and edges connecting them within the pacman map.
	 * These are stored in an array in a specific order starting with the top left corner and reading down.
	 */
	private void initializeNodes() {
		try {
			Scanner a = new Scanner(new File("graph.txt"));
			nodes = new Node[a.nextInt()];
			for(int i =0;i<nodes.length;i++)
				nodes[i]= new Node(a.nextInt(),a.nextInt());
			for(int i =0;i<nodes.length;i++) {
				while(!a.hasNext("-1")) {
					int node = a.nextInt()-1;
					int cost = a.nextInt();
					nodes[i].addNeighbors(nodes[node], cost);
					nodes[node].addNeighbors(nodes[i], cost); 
				}a.nextInt();
			}
			a.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/* Updating the state of the game
	 * Step 1: move the pacman and ghosts
	 * Step 2: Update the score and check for pacman-ghost collisions
	 */
	public void update() {
		pman.move();
		for(Ghost a: ghosts)
			makePath(a);
		pointsDeaths();
		
		if(timer==600) {
			for(Ghost g: ghosts)
				g.setEdible(false);
			timer=0;
		}else if(timer>0)
			timer++;
	}
	
	/* Generates a path of nodes for the ghost to take
	 * A* pathfinding is used to generate the path
	 * Firstly the ghost can only "change direction" if they are at a node
	 * Case 1: Ghost has been eaten, travel to initial node then return to normal pathfinding
	 * Case 2: Ghost is edible and runs away from the pacman
	 * Case 3: Ghost moves normally towards the pacman
	 */
	private void makePath(Ghost a) {
		if(a.atNode(a.getNodeAt())) {
			if(a.getEaten()) {
				if(a.atNode(a.getInitialNode())&&timer==0)
					a.setEaten(false);
				else
					a.setPath((new PathfindingAlgos().astar(nodes,  a.getNodeAt(), a.getInitialNode())));
			}else if(a.getEdible()) {
				a.setPath((new PathfindingAlgos().astar(nodes,  a.getNodeAt(), a.getInitialNode())));
			}else {
				if(pman.getTargetNode()!=null)
					a.setPath((new PathfindingAlgos().astar(nodes, a.getNodeAt(), pman.getTargetNode())));
				else
					a.setPath((new PathfindingAlgos().astar(nodes, a.getNodeAt(), pman.getNodeAt())));
			}
		}a.move();
	}
	
	/* Changes the direction of the Pacman character
	 * Case 1: New direction is opposite of current direction
	 * Case 2: Pacman currently isn't moving
	 * Case 3: A standard change of direction
	 */
	public void changeD(Direction d) {
		if(d.opposite()==pman.getDirection()) {
			Node n = pman.getTargetNode();
			pman.targetNodeNull();
			pman.changeTarget(pman.getNodeAt(), d);
			pman.setNodeAt(n);	
			pman.changeNextTargetNode(null);
		}else if(pman.getTargetNode()==null) {
			Node n = pman.getNodeAt().neighborInDirection(d);
			if(n!=null)
				pman.changeTarget(n,d);
		}else {
			Node n = pman.getTargetNode().neighborInDirection(d);
			if(n!=null)
				pman.changeTarget(n,d);
		}
	}
	
	/*First goes through and sees if the pacman character and any of the ghost characters are intersecting
	 * if they are, the pacman loses a life and all characters reset their positions
	 * Secondly if the pacman is in a GameSquare containg a point than he eats it and increases his score
	 */
	private void pointsDeaths() {
		for(Ghost a: ghosts) {
			if((new Rectangle(a.getX()-10,a.getY()-10,20,20)).intersects(new Rectangle(pman.getX()-10,pman.getY()-10,20,20))){
				if(!a.getEdible() && !a.getEaten()) {
					pman.loseLife();
					for(Character c: characters)
						c.resetPosition();
					pman.targetNodeNull();
					pman.changeTarget(nodes[29],Direction.LEFT);
					pman.changeNextTargetNode(null);
					break;
				}
				else{
					if(!a.getEaten()){				
						pman.increaseScore(100);
						a.setEaten(true);
					}
				}
			}
		}
		if(gsquare[pman.getY()/25][pman.getX()/25].hasDot()) {
			pman.increaseScore(10);
			gsquare[pman.getY()/25][pman.getX()/25].removeDot();
		}
		if(gsquare[pman.getY()/25][pman.getX()/25].hasBigDot()) {
			gsquare[pman.getY()/25][pman.getX()/25].removeBigDot();
			for(Ghost a: ghosts)
				a.setEdible(true);
			timer++;
		}
				
	}
	
	//Returns the score of the pacman
	public int getScore() {
		return pman.getScore();
	}
	
	//Returns the lives of the pacman
	public int getLives() {
		return pman.getLife();
	}
	
	//Calls the draw methods for each GameSquare and Character respectively
	public void draw(Graphics g) {
		for(int i =0;i<gsquare.length;i++) {
			for(int j =0;j<gsquare[0].length;j++)
				gsquare[i][j].draw(g);
		}
		for(Character a: characters)
			a.draw(g);
			
	}

}
