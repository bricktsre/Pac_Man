
public class Edge implements Comparable<Edge>{
	private Node n;
	private int cost;
	
	public Edge(Node node, int cost) {
		n = node;
		this.cost=cost;
	}
	
	//Returns the node in the Edge
	public Node getNode() {
		return n;
	}
	
	//Returns the cost of the Edge
	public int getCost() {
		return cost;
	}

	/* Implements the comparable interface
	 * Case -1: the parameter edge costs more than this.cost
	 * Case 0: the parameter edge costs the same as this.cost
	 * Case 1: the parameter edge costs less than this.cost 
	 */
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
