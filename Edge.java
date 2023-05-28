public class Edge {
	private Vertex start;
	private Vertex end;
	private Float weight;

	public Edge(Vertex startV, Vertex endV, Float inputWeight) {
		this.start = startV;
		this.end = endV;
		this.weight = inputWeight;
	}

	public Vertex getStart() {
		return this.start;
	}

	public Vertex getEnd() {
		return this.end;
	}

	public Float getWeight() {
		return this.weight;
	}
}