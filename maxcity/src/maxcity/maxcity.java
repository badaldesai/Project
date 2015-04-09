package maxcity;

class Neighbor{
	public int VertexNum;
	public Neighbor next;
	public Neighbor(int vnum, Neighbor nbr){
		this.VertexNum = vnum;
		next=nbr;
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

