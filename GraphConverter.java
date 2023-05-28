import java.io.*;
import java.util.*;

public class GraphConverter {
	public static Graph fileToGraph(String f) throws IOException {
		boolean isWeighted, isDirected;
		Float weight;

		BufferedReader reader = new BufferedReader(new FileReader(f));	
		String line;
		String[] linesplit;

		linesplit = reader.readLine().split("");

		if (linesplit[0].equals("W")) {
			isWeighted = true;
		
		} else if (linesplit[0].equals("U"))	 {
			isWeighted = false;

		} else {
			return null;
		
		}

		if (linesplit[1].equals("D")) {
			isDirected = true;
		
		} else if (linesplit[1].equals("U"))	 {
			isDirected = false;

		} else {
			return null;
		
		}

		Graph g = new Graph(isWeighted, isDirected);

		linesplit = reader.readLine().replace(",", "").replace(" ", "").replace("{", "").replace("}", "").split("");

		for (String vertex : linesplit) {
			g.addVertex(vertex);
		}


		while ((line = reader.readLine()) != null) {
			linesplit = line.split(" ");

			if (isWeighted) {
				g.addEdge(linesplit[0], linesplit[1], Float.valueOf(linesplit[2]));

			} else {
				g.addEdge(linesplit[0], linesplit[1], 0f);

			}
				
		}

		reader.close();
		
		return g;
	}

	public static File graphToFile(Graph g) throws IOException {
		ArrayList<String> vertices = new ArrayList<String>();
		ArrayList<String> edges = new ArrayList<String>();
		Integer hash = g.hashCode();
		String line = "";

		BufferedWriter writer = new BufferedWriter(new FileWriter("graphs/graph-" + hash.toString() + ".txt"));
		
		if (g.isWeighted()) {
			line += "W";
		
		} else {
			line += "U";
		}

		if (g.isDirected()) {
			line += "D";
		
		} else {
			line += "U";
		}

		line += "G";

		writer.write(line);

		for (Vertex v : g.getVertices()) {
			vertices.add(v.getData());

			for (Edge e : v.getEdges()) {
				if (g.isWeighted()) {
					line = e.getStart().getData() + " " + e.getEnd().getData() + " " + e.getWeight().toString();
					
				} else {
					line = e.getStart().getData() + " " + e.getEnd().getData();
				
				}
				if (g.isDirected()) {
					edges.add(line);
				
				} else {
					if (g.isWeighted()) {
						if (!edges.contains(e.getEnd().getData() + " " + e.getStart().getData() + " " + e.getWeight().toString())) {
							edges.add(line);
						}

					} else {
						if (!edges.contains(e.getEnd().getData() + " " + e.getStart().getData())) {
							edges.add(line);
						}						
					}
				}
			}
		}

		line = "\n" + vertices.toString().replace("[", "{").replace("]", "}");

		writer.write(line);

		for (String edge : edges) {
			writer.write("\n" + edge);			
		}

		writer.close();

		return null;
	}

	public static void main (String[] args) throws IOException {
		Graph g = GraphConverter.fileToGraph(args[0]);

		ArrayList<Vertex> visitedVerticesDFS = new ArrayList<Vertex>();
		ArrayList<Vertex> visitedVerticesBFS = new ArrayList<Vertex>();

		visitedVerticesBFS = GraphTraverser.BFS(g, "A", visitedVerticesBFS);
		visitedVerticesDFS = GraphTraverser.DFS(g, "A", visitedVerticesDFS);

		System.out.println("BFS:");
		for (Vertex v : visitedVerticesBFS) {
			System.out.println(v.getData());
		}

		System.out.println("\nDFS:");
		for (Vertex v : visitedVerticesDFS) {
			System.out.println(v.getData());
		}
	}
}