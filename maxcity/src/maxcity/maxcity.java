package maxcity;

import java.util.*;

class Graph{
	
	private Map<String, List<String>> edges = new HashMap<String, List<String>>();
	
	public void addEdge (String v1, String v2){
		
		List<String> neighbors1 = this.edges.get(v1);
		if(neighbors1==null){
			this.edges.put(v1, neighbors1=new ArrayList<String>());
		}
		neighbors1.add(v2);
		
		List<String> neighbors2 = this.edges.get(v2);
		if(neighbors2==null){
			this.edges.put(v2, neighbors2=new ArrayList<String>());
		}
		neighbors2.add(v1);
	}
	
	public Iterable<String> getNeighbors(String v){
		List<String> neighbors = this.edges.get(v);
		return neighbors;
	}
}


class dfs implements Iterator<String> {
	
	Set<String> visited = new HashSet<String>();
	Queue<String> queue = new LinkedList<String>();
	private String next;
		
	public void remove(){
		throw new UnsupportedOperationException();
	}
	
	public boolean hasNext(){
		return this.next != null;
	}
	
	public String next(){
		if(this.next == null){
			throw new NoSuchElementException();
		}
		try{
			this.visited.add(this.next);
			return this.next;
		}finally {
			this.advance();
		}
	}
	
	public class maxcity{
		
	}
}

