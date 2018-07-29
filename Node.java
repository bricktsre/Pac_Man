import java.awt.Color;

import java.awt.Graphics;
import java.util.ArrayList;

public class Node implements Comparable<Node>{
	private int row,col;
	ArrayList<Edge> neighbors = new ArrayList<Edge>();
	
	public Node(int x, int y) {
		row = y;
		col = x;
	}
	
	public void addNeighbors(Node node, int cost) {
		neighbors.add(new Edge(node,cost));
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public Edge[] getNeighbors() {
		Edge[] e = new Edge[neighbors.size()];
		for(int i =0; i<e.length;i++)
			e[i]=neighbors.get(i);
		return e;
	}
	
	public Node neighborInDirection(Direction d) {
		for(Edge e: neighbors) {
			Node n = e.getNode();
			switch(d) {
				case RIGHT:
					if(n.getCol()>col)
						return n;
					break;
				case DOWN:
					if(n.getRow()>row)
						return n;
					break;
				case LEFT:
					if(n.getCol()<col)
						return n;
					break;
				case UP:
					if(n.getRow()<row)
						return n;
					break;
				default:
					break;
			}
		}
		return null;
	}
	
	public Direction directionOfNode(Node n) {
		if(n.getCol()>col)
			return Direction.RIGHT;
		else if(n.getCol()<col)
			return Direction.LEFT;
		else if(n.getRow()>row)
			return Direction.DOWN;
		else
			return Direction.UP;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		for(Edge e: neighbors)
			g.drawLine(col*25+13, row*25+13, e.getNode().getCol()*25+13, e.getNode().getRow()*25+13);
		g.setColor(Color.ORANGE);
		g.fillOval(col*25+4,row*25+4,18,18);
	}

	@Override
	public int compareTo(Node o) {
		if(this==null||o==null)
			return 0;
		if(row==o.getRow()&&col==o.getCol())
			return 0;
		else if((row>=o.getRow()&&col<o.getCol())||(row<o.getRow()&&col>=o.getCol()))
			return -1;
		return 1;
	}
}
