import java.util.*;

public class Dijkstra {
	public static Dictionary[] dijkstra(Graph g, String start) {
		Dictionary<String, Float> distances = new Hashtable<>();
		Dictionary<String, String> previous = new Hashtable<>();
	
		PriorityQueue<QueueObject> queue = new PriorityQueue<QueueObject>();

		Vertex origin = g.getVertexByValue(start);

		queue.add(new QueueObject(origin, 0f));

		for (Vertex v : g.getVertices()) {
			if (v != origin) {
				distances.put(v.getData(), Float.MAX_VALUE);
			}

			previous.put(v.getData(), "Null");
		}

		distances.put(origin.getData(), 0f);

		while (!queue.isEmpty()) {
			Vertex current = queue.poll().vertex;

			for (Edge e : current.getEdges()) {
				Float alternative = distances.get(current.getData()) + e.getWeight();
				String neighborValue = e.getEnd().getData();

				if (alternative < distances.get(neighborValue)) {
					distances.put(neighborValue, alternative);
					previous.put(neighborValue, current.getData());
					queue.add(new QueueObject(e.getEnd(), distances.get(neighborValue)));
				}
			}
		}

		return new Dictionary[] {distances, previous};
	}

	public static void dijkstraResultPrinter(Dictionary[] d) {
		System.out.println("Distances:\n");

		for (Enumeration keys = d[0].keys(); keys.hasMoreElements(); ) {
			String nextKey = keys.nextElement().toString();
			System.out.println(nextKey + ": " + d[0].get(nextKey));
		}

		for (Enumeration keys = d[1].keys(); keys.hasMoreElements(); ) {
			String nextKey = keys.nextElement().toString();
			Vertex nextVertex = (Vertex) d[1].get(nextKey);
			System.out.println(nextKey + ": " + nextVertex.getData());
		}
	}

	public static void shortestPathBetween(Graph g, String start, String end) {
		Dictionary[] dijkstraDictionaries = dijkstra(g, start);
		Dictionary distances = dijkstraDictionaries[0];
		Dictionary previous = dijkstraDictionaries[1];

		Float distance = (Float) distances.get(end);
	
		System.out.println("Shortest distance between " + start + " and " + end);
		System.out.println(distance);

		ArrayList<String> path = new ArrayList<>();

		String v = end;

		while (v != "Null") {
			path.add(0, v);
			v = (String) previous.get(v);
		}

		System.out.println("Shortest path:");
		
		for (String vertex : path) {
			System.out.println(vertex);
		}
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
		g.addEdge("B", "F", 13.9f);
		g.addEdge("B", "C", 13.9f);
		g.addEdge("B", "G", 1.5f);
		g.addEdge("D", "A", 10.5f);
		g.addEdge("F", "D", 5f);

		Dijkstra.shortestPathBetween(g, "A", "C");
	}
}