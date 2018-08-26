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
	private Ghost [] ghosts = new Ghost[1];		//Array of ghosts
	private Character[] characters;					//Array of pacman and ghost characters	
	private Node[] nodes;						//Array of Nodes
	
	public gameEnvironment(MapReader m) {
		gsquare = new GameSquare[height][width];
		initializeBoard(m);
		initializeNodes();
		pman = new pacman(348,588,nodes[37]);
		pman.changeTarget(nodes[29],Direction.LEFT);
		characters = new Character[1+ghosts.length];
		characters[0]=pman;
		for(int i =0;i<ghosts.length;i++) {
			ghosts[i]= new Ghost(36,36,Color.GREEN,nodes[0]);
			characters[i+1]=ghosts[i];
		}	
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
	
	//Update the game
	public void update() {
		pman.move();
		//for(Ghost a: ghosts)
			//makePath(a);
		pointsDeaths();
	}
	
	private void makePath(Ghost a) {
		
		a.setPath((new PathfindingAlgos().astar(nodes, a.getNodeAt(), pman.getTargetNode())));
		a.move();
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
	
	/*First goes through and sees if the pacman character and any of the ghost characters are in the same GameSquare
	 * if they are, the pacman loses a life and all characters reset their positions
	 * Secondly if the pacman is in a GameSquare containg a point than he eats it and increases his score
	 */
	private void pointsDeaths() {
		for(Ghost a: ghosts) {
			if((new Rectangle(a.getX()-10,a.getY()-10,20,20)).intersects(new Rectangle(pman.getX()-10,pman.getY()-10,20,20))){
				pman.loseLife();
				for(Character c: characters)
					c.resetPosition();
				pman.targetNodeNull();
				pman.changeTarget(nodes[29],Direction.LEFT);
				pman.changeNextTargetNode(null);
				break;
			}
		}
		if(gsquare[pman.getY()/25][pman.getX()/25].hasDot()) {
			pman.increaseScore(10);
			gsquare[pman.getY()/25][pman.getX()/25].removeDot();
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
