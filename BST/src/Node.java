
public class Node {

	public int key;
	String name;
	Node left;
	Node right;
	
	Node(int key, String name){
		
		this.key = key;
		this.name = name;
	}
	public String toString(){
		
		return name + " has the key " + key;
	}
}
