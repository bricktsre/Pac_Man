
public class Edge implements Comparable<Edge>{
	private Node n;
	private int cost;
	
	public Edge(Node node, int cost) {
		n = node;
		this.cost=cost;
	}
	
	public Node getNode() {
		return n;
	}
	
	public int getCost() {
		return cost;
	}

	@Override
	public int compareTo(Edge o) {
		if(cost < o.getCost())
			return -1;
		else if(cost > o.getCost())
			return 1;
		else
			return 0;
	}
}
