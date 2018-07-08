import java.awt.Color;
import java.awt.Graphics;

public class gameEnvironment {
	private final int height=31;				//Number of GameSquares high the map is
	private final int width=28;					//Number of GameSquares wide the map is
	private GameSquare[][] gsquare;				//2D array of all the squares making up the map
	private pacman pman;						//Pacman character
	private Ghost [] ghosts = new Ghost[1];		//Array of ghosts
	private Character[] car;					//Array of pacman and ghost characters	
	
	public gameEnvironment(MapReader m) {
		gsquare = new GameSquare[height][width];
		initializeBoard(m);
		pman = new pacman(CardinalDirection.LEFT,365,621,width);
		car = new Character[1+ghosts.length];
		car[0]=pman;
		for(int i =0;i<ghosts.length;i++) {
			ghosts[i]= new Ghost(CardinalDirection.NONE,27,27,width,Color.GREEN);
			car[i+1]=ghosts[i];
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
	
	//Update the game
	public void update() {
		//move();
		//pointsDeaths();
	}
	
	/* Focuses entirely on moving the characters 
	 * Pacman is moved seperately because it has unique move mechanics
	 */
	private void move() {
		if(!checkWallCollision(pman))
			pman.changeDirection(CardinalDirection.NONE);
		if(pman.getDiagdir()==DiagDirection.NONE)
			pman.move();
		else
			diagonalMove(pman);
		
		for(Ghost a: ghosts){
			if(a.atCenterLineC(gsquareIn(a), a.getDirection())==0 ) {	//Ghosts can only change direction if they are in the center of their square
				a.changeDirection();
				while(!checkWallCollision(a))
					a.changeDirection();
				a.move();
			}
			else 
				a.move();
		}				 
	}
	
	/*Test to see if a character moving would cause it to go into a wall
	 * If it is going to go into a wall then it returns false
	 */
	private boolean checkWallCollision(Character c) {
		int a = c.getSquareIn();
		int row = a/width+c.getDirection().dy;
		int col = a%height+c.getDirection().dx;
		if(gsquare[row][col].isWall()&&(c.atCenterLineC(gsquareIn(c), c.getDirection())==0))
			return false;
		return true;			
	}
	
	/* First this method changes the direction of the pac man character 
	 * Then it tests whether the pac man character could move in the new direction
	 * If it cannot then the pac man character goes back to its original direction
	 */
	public void changeD(CardinalDirection d) {
		if(d == pman.getDirection())
			return;
		CardinalDirection olddirection = pman.getDirection();
		pman.changeDirection(d);
		if(checkWallCollision(pman))
			if(pman.atCenterLineD(gsquareIn(pman))==0)
				return;
			else	
				pman.setDiag(pman.atCenterLineD(gsquareIn(pman)));
		else
			pman.changeDirection(olddirection);
	}
	
	//Moves the character in a diagonal fashion
	//Checks to see if the diagonal move is complete and if it is changes the diagonal direction something to NONE
	private void diagonalMove(pacman p) {
		p.moveDiag();
		if(p.atCenterLineD(gsquareIn(p))==0)
			p.changeDiagDir(DiagDirection.NONE);
	}
	
	//Returns the GameSquare the Character a is currently in
	private GameSquare gsquareIn(Character a) {
		return gsquare[(a.getSquareIn())/height][(a.getSquareIn())%width];
	}
	
	/*First goes through and sees if the pacman character and any of the ghost characters are in the same GameSquare
	 * if they are, the pacman loses a life and all characters reset their positions
	 * Secondly if the pacman is in a GameSquare containg a point than he eats it and increases his score
	 */
	private void pointsDeaths() {
		for(Ghost a: ghosts) {
			if(gsquareIn(a)==gsquareIn(pman)){
				pman.loseLife();
				for(Character c: car)
					c.resetPosition();
				pman.changeDirection(CardinalDirection.LEFT);
				break;
			}
		}
		if(gsquareIn(pman).hasDot()) {
			pman.increaseScore(10);
			gsquareIn(pman).removeDot();
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
				gsquare[i][j].draw(g, i, j);
		}
		for(Character a: car)
			a.draw(g);
			
	}

}
