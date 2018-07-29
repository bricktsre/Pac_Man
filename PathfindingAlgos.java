import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class PathfindingAlgos {

	public LinkedList<Node> breadthFirstSearch(Node[] nodes, Node start, Node goal) {
		ArrayBlockingQueue<Node> frontier = new ArrayBlockingQueue<Node>(nodes.length); 
		frontier.offer(start);
		Map<Node,Node> camefrom = new HashMap<>();
		camefrom.put(start, null);
		
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			if(current.compareTo(goal)==0)
				break;
			Edge[] e = current.getNeighbors();
			for(Edge a: e) {
				if(!camefrom.containsKey(a.getNode())) {
					frontier.offer(a.getNode());
					camefrom.put(a.getNode(), current);
				}
					
			}
		}
		LinkedList<Node> path = new LinkedList<Node>();
		Node current = goal;
		while(current.compareTo(start)!=0) {
			path.push(current);
			current = camefrom.get(current);
		}
		return path;
	}

	public LinkedList<Node> Dijkstra(Node[] nodes, Node start, Node goal) {
		PriorityQueue<Edge> frontier = new PriorityQueue<Edge>();
		frontier.offer(new Edge(start, 0));
		Map<Node,Node> camefrom = new HashMap<>();
		camefrom.put(start, null);
		Map<Node,Integer> costsofar = new HashMap<>();
		costsofar.put(start, 0);
		
		while(!frontier.isEmpty()) {
			Edge current = frontier.poll();
			if(current.getNode().compareTo(goal)==0)
				break;
			Edge[] e = current.getNode().getNeighbors();
			for(Edge a: e) {
				int newcost = costsofar.get(current.getNode())+a.getCost();
				if(!costsofar.containsKey(a.getNode())||(newcost<costsofar.get(a.getNode()))) {
					costsofar.put(a.getNode(), newcost);
					frontier.offer(new Edge(a.getNode(),newcost));
					camefrom.put(a.getNode(), current.getNode());
				}
			}
		}
		LinkedList<Node> path = new LinkedList<Node>();
		Node current = goal;
		while(current.compareTo(start)!=0) {
			path.push(current);
			current = camefrom.get(current);
		}
		return path;
	}
	
	public LinkedList<Node> greedyBestFirstSearch(Node[] nodes, Node start, Node goal){
		PriorityQueue<Edge> frontier = new PriorityQueue<Edge>(); 
		frontier.offer(new Edge(start,0));
		Map<Node,Node> camefrom = new HashMap<>();
		camefrom.put(start, null);
		
		while(!frontier.isEmpty()) {
			Edge current = frontier.poll();
			if(current.getNode().compareTo(goal)==0)
				break;
			Edge[] e = current.getNode().getNeighbors();
			for(Edge a: e) {
				if(!camefrom.containsKey(a.getNode())) {
					int priority = heuristic(goal,a.getNode());
					frontier.offer(new Edge(a.getNode(),priority));
					camefrom.put(a.getNode(), current.getNode());
				}
					
			}
		}
		LinkedList<Node> path = new LinkedList<Node>();
		Node current = goal;
		while(current.compareTo(start)!=0) {
			path.push(current);
			current = camefrom.get(current);
		}
		return path;
	}
	
	private int heuristic(Node a, Node b) {
		//return Math.abs(a.getCol()-b.getCol())+Math.abs(a.getRow()-b.getRow());											//Manhattan Distance
		return (int)Math.sqrt(Math.pow(Math.abs(a.getCol()-b.getCol()), 2)+Math.pow(Math.abs(a.getRow()-b.getRow()), 2));	//Straight line distance
	}

	public LinkedList<Node> astar(Node[] nodes, Node start, Node goal) {
		PriorityQueue<Edge> frontier = new PriorityQueue<Edge>();
		frontier.offer(new Edge(start, 0));
		Map<Node,Node> camefrom = new HashMap<>();
		camefrom.put(start, null);
		Map<Node,Integer> costsofar = new HashMap<>();
		costsofar.put(start, 0);
		
		while(!frontier.isEmpty()) {
			Edge current = frontier.poll();
			if(current.getNode().compareTo(goal)==0)
				break;
			Edge[] e = current.getNode().getNeighbors();
			for(Edge a: e) {
				int newcost = costsofar.get(current.getNode())+a.getCost();
				if(!costsofar.containsKey(a.getNode())||(newcost<costsofar.get(a.getNode()))) {
					costsofar.put(a.getNode(), newcost);
					frontier.offer(new Edge(a.getNode(),(newcost+heuristic(goal,a.getNode()))));
					camefrom.put(a.getNode(), current.getNode());
				}
			}
		}
		LinkedList<Node> path = new LinkedList<Node>();
		Node current = goal;
		while(current.compareTo(start)!=0) {
			path.push(current);
			current = camefrom.get(current);
		}
		return path;
	}
}
