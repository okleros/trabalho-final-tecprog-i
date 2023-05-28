import java.util.*;

class GraphTraverser {
	public static ArrayList<Vertex> DFS(Graph g, String origin, ArrayList<Vertex> visitedVertices) {
		Vertex start = g.getVertexByValue(origin);

		if (visitedVertices.isEmpty()) {
			visitedVertices.add(start);
		}

		for (Edge e : start.getEdges()) {
			Vertex neighbor = e.getEnd();

			if (!visitedVertices.contains(neighbor)) {
				visitedVertices.add(neighbor);
				GraphTraverser.DFS(g, neighbor.getData(), visitedVertices);
			
			}
		}
		return visitedVertices;
	}

	public static ArrayList<Vertex> BFS(Graph g, String origin, ArrayList<Vertex> visitedVertices) {
		Vertex start = g.getVertexByValue(origin);

		ArrayDeque<Vertex> visitQueue = new ArrayDeque<Vertex>();
		visitQueue.add(start);
		visitedVertices.add(start);

		while (!visitQueue.isEmpty()) {
			Vertex current = visitQueue.remove();

			for (Edge e : current.getEdges()) {
				Vertex neighbor = e.getEnd();

				if (!visitedVertices.contains(neighbor)) {
					visitedVertices.add(neighbor);
					visitQueue.add(neighbor);
				}
			}
		}
		return visitedVertices;
	}

	public static void main(String[] args) {
		Graph g = new Graph(true, true);

		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		g.addVertex("E");
		g.addVertex("F");
		g.addVertex("G");

		g.addEdge("A", "B", 55.6f);
		g.addEdge("A", "C", 155.6f);
		//g.addEdge("A", "D", 5.6f);
		g.addEdge("A", "E", 55.64f);
		g.addEdge("A", "F", 15.2f);
		g.addEdge("A", "G", 5f);
		g.addEdge("G", "B", 11f);
		g.addEdge("G", "F", 13.5f);
		g.addEdge("B", "F", 13.5f);
		g.addEdge("B", "G", 13.5f);
		g.addEdge("D", "A", 13.5f);
		g.addEdge("F", "D", 13.5f);

		ArrayList<Vertex> visitedVerticesBFS = new ArrayList<Vertex>();
		ArrayList<Vertex> visitedVerticesDFS = new ArrayList<Vertex>();

		visitedVerticesBFS = GraphTraverser.BFS(g, "A", visitedVerticesBFS);
		visitedVerticesDFS = GraphTraverser.DFS(g, "A", visitedVerticesDFS);
	
		System.out.println("DFS");
		for (Vertex v : visitedVerticesDFS) {
			System.out.println(v.getData());
		}
		
		System.out.println("\nBFS");
		for (Vertex v : visitedVerticesBFS) {
			System.out.println(v.getData());
		}
	}
}