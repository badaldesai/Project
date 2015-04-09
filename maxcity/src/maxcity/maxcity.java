package maxcity;

class Graph{
	
	private Map<String, List<String>> edges = new HashMap<String, List<String>>();
	
	public void addEdge (String v1, String v2){
		
		List<String> neighbors1 = this.edges.get(v1);
		if(neighbors1==null){
			this.edges.put(v1, neighbors1=new ArrayList<String>());
		}
		neighbors.add(v2);
		
		List<String> neighbors2 = this.edges.get(v2);
		if(neighbors2==null){
			this.edges.put(v2, neighbors2=new ArrayList<String>());
		}
		neighbors.add(v1);
	}
	
	public List<String> getNeighbors(String v){
		List<String> neighbors = this.edges.get(v);
		return neighors;
	}
}

class Vertex{
	String name;
	Neighbor adjList;
	Vertex(String name, Neighbor neighbors){
		this.name = name;
		this.adjList = neighbors;
	}
}


public class maxcity {

}

