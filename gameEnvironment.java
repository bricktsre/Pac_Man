import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class gameEnvironment {
	private GameSquare[][] gsquare;				//2D array of all the squares making up the map
	private Character [] characters;			//Array of characters including pacman and the ghosts. Pac_Man is always in the first location
	private int height;
	private int width;
	
	public gameEnvironment(MapReader m) {
		int[] a = m.getHeightWidth();
		height=a[1];
		width = a[0];
		gsquare = new GameSquare[width][height];
		initializeBoard(m);
		characters = new Character[1];
		a = m.getStartCoordinates();
		characters[0] = new pacman(CardinalDirection.LEFT,a[0],a[1],width);
	}
	
	//Initializes the playing board with a border of walls and wall blocks randomly inside with a bias
	private void initializeBoard(MapReader m) {								
		int[][] b = m.getMap(width,height);
		
		for(int i = 0;i<height;i++) {
			for(int j =0;j<width;j++) {
				if(b[i][j]==1)
					gsquare[i][j]=new GameSquare(true,false,i,j);
				else
					gsquare[i][j]=new GameSquare(false,true,i,j);
			}
		}
	}
	
	//Update the game
	public void update() {
		move();
	}
	
	/* Focuses entirely on moving the characters 
	 * Pacman(characters[0]) is moved seperately becuase it has unique move mechanics
	 */
	private void move() {
		if(!checkWallCollision(characters[0]))
			characters[0].changeDirection(CardinalDirection.NONE);
		if(characters[0].getDiagdir()==DiagDirection.NONE)
			characters[0].move();
		else
			diagonalMove(characters[0]);
	}
	
	/*Test to see if a character moving would cause it to go into a wall
	 * If it is going to go into a wall then it returns false
	 */
	private boolean checkWallCollision(Character car) {
		int a = car.getSquareIn();
		int row = a/height+car.getDirection().dy;
		int col = a%width+car.getDirection().dx;
		if(gsquare[row][col].isWall()&&(car.atCenterLineC(gsquareIn(car), car.getDirection())==0))
			return false;
		return true;			
	}
	
	/* First this method changes the direction of the pac man character 
	 * Then it tests whether the pac man character could move in the new direction
	 * If it cannot then the pac man character goes back to its original direction
	 */
	public void changeD(CardinalDirection d) {
		if(d == characters[0].getDirection())
			return;
		CardinalDirection olddirection = characters[0].getDirection();
		characters[0].changeDirection(d);
		if(checkWallCollision(characters[0]))
			if(characters[0].atCenterLineD(gsquareIn(characters[0]))==0)
				return;
			else	
				characters[0].setDiag(characters[0].atCenterLineD(gsquareIn(characters[0])));
		else
			characters[0].changeDirection(olddirection);
	}
	
	//Moves the character in a diagonal fashion
	//Checks to see if the diagonal move is complete and if it is changes the diagonal direction something to NONE
	private void diagonalMove(Character car) {
		car.moveDiag();
		if(car.atCenterLineD(gsquareIn(car))==0)
			car.changeDiagDir(DiagDirection.NONE);
	}
	
	//Returns the GameSquare the Character a is currently in
	private GameSquare gsquareIn(Character a) {
		return gsquare[(a.getSquareIn())/height][(a.getSquareIn())%width];
	}
	
	//Calls the draw methods for each GameSquare and Character respectively
	public void draw(Graphics g) {
		for(int i =0;i<gsquare.length;i++) {
			for(int j =0;j<gsquare[0].length;j++)
				gsquare[i][j].draw(g, i, j);
		}
		for(Character a: characters)
			a.draw(g);
	}

}
