import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Ghost extends Character{
	private Color c;	//Color of the ghost
	
	public Ghost(CardinalDirection a, int initialdx, int initialdy, int w, Color ghostcolor) {
		super(a,initialdx,initialdy, w);
		c=ghostcolor;
	}
	
	public void changeDirection() {
		ArrayList<CardinalDirection> b = new ArrayList<CardinalDirection>();
		b.add(CardinalDirection.UP);
		b.add(CardinalDirection.RIGHT);
		b.add(CardinalDirection.LEFT);
		b.add(CardinalDirection.DOWN);
		b.remove(d);
		int a = (int)(Math.random()*11);
		if(a==7)
			d=b.get(0);
		else if(a==8)
			d=b.get(1);
		else if(a==9)
			d=b.get(2);
	}

	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval(imgdx, imgdy, imgwidth, imgwidth);
	}
}
