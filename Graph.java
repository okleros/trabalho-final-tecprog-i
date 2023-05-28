import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> vertices;
	private boolean isWeighted;
	private boolean isDirected;

	public Graph(boolean inputIsWeighted, boolean inputIsDirected) {
		this.vertices = new ArrayList<Vertex>();
		this.isWeighted = inputIsWeighted;
		this.isDirected = inputIsDirected;
	}

	public void addVertex(String data) {
		Vertex newVertex = new Vertex(data);
		this.vertices.add(newVertex);
	}

	public Vertex getVertexByValue(String value) {
		for (Vertex v : this.vertices) {
			if (v.getData().equals(value)) {
				return v;
			}
		}

		return null;
	}

	public void addEdge(String start, String end, Float weight) {
		Vertex vertex1 = this.getVertexByValue(start);
		Vertex vertex2 = this.getVertexByValue(end);

		if (!this.isWeighted) {
			weight = null;
		}

		vertex1.addEdge(vertex2, weight);

		if (!this.isDirected) {
			vertex2.addEdge(vertex1, weight);
		}
	}

	public void removeEdge(String start, String end) {
		Vertex vertex1 = this.getVertexByValue(start);
		Vertex vertex2 = this.getVertexByValue(end);
		
		vertex1.removeEdge(vertex2);

		if (!this.isDirected) {
			vertex2.removeEdge(vertex1);
		}
	}

	public void removeVertex(String targetVertex) {
		Vertex vertex = this.getVertexByValue(targetVertex);

		this.vertices.remove(vertex);
	}

	public ArrayList<Vertex> getVertices() {
		return this.vertices;
	}

	public boolean isWeighted() {
		return this.isWeighted;
	}

	public boolean isDirected() {
		return this.isDirected;
	}

	public void print() {
		for (Vertex v : this.vertices) {
			v.print(this.isWeighted);
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
		g.addEdge("A", "D", 5.6f);
		g.addEdge("A", "E", 55.64f);
		g.addEdge("A", "F", 15.2f);
		g.addEdge("A", "G", 5f);
		g.addEdge("G", "B", 11f);

		g.print();
	}
}