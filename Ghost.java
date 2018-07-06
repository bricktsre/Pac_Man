import java.awt.Color;

public class Ghost extends Character{
	private Color c;	//Color of the ghost
	
	public Ghost(CardinalDirection a, int initialdx, int initialdy, int w, Color ghostcolor) {
		super(a,initialdx,initialdy, w);
		c=ghostcolor;
	}
}
