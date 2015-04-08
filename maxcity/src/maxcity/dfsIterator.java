package maxcity;

import java.util.*;

public class dfsIterator  implements Iterator<String>{
	private Set<String> visited = new HashSet<String>();
	private Deque<Iterator<String>> stack = new LinkedList<Iterator<String>>();
	private graph gr;
	private String next;
	
	public dfsIterator(graph g, String startingvertex){
		this.stack.push(g.getNeighbors(startingvertex).iterator());
		this.gr = g;
		this.next = startingvertex;
	}
	
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
		}finally{
			this.advance();
		}
	}
	private void advance(){
		Iterator<String> neighbors = this.stack.peek();
		do{
			while(!neighbors.hasNext()){
				this.stack.pop();
				if(this.stack.isEmpty()){
					this.next = null;
					return;
				}
				neighbors = this.stack.peek();
			}
			this.next = neighbors.next();
		}while(this.visited.contains(this.next));
		this.stack.push(this.gr.getNeighbors(this.next).iterator());
	}
}
