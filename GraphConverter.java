import java.io.*;

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

			g.addEdge(linesplit[0], linesplit[1], Float.valueOf(linesplit[2]));
		}

		reader.close();
		
		g.print();

		return g;
	}

	public static File graphToFile(Graph g) {
		return new File("null");
	}

	public static void main (String[] args) throws IOException {
		GraphConverter.fileToGraph("graphs/testGraph.txt");
	}
}