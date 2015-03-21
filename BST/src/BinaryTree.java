
public class BinaryTree {
	
	Node root;
	
	public void addNode(int key, String name){
		
		Node newNode = new Node(key,name);
		
		if(root == null){
			
			root = newNode;
		
		}else{
			
			Node focusNode = root;
			
			Node parent;
			
			while(true){
				
				parent = focusNode;
				 if(key<focusNode.key){
					 
					 focusNode = focusNode.left;
					 
					 if(focusNode == null){
						 
						 parent.left = newNode;
						 return;
					 }
				 }else{
					 
					 focusNode = focusNode.right;
					 
					 if(focusNode == null){
						 
						 parent.right = newNode;
						 return;
					 }
				 }
			}
		}
	}
	
	public void inOrderTraverse(Node focusNode){
		if(focusNode!=null){
			
			inOrderTraverse(focusNode.left);
			System.out.println(focusNode);
			inOrderTraverse(focusNode.right);
		}
	}
	
	public void preOrderTraverse(Node focusNode){
		if(focusNode!=null){
			
			System.out.println(focusNode);
			preOrderTraverse(focusNode.left);
			preOrderTraverse(focusNode.right);
		}
	}
	
	public void postOrderTraverse(Node focusNode){
		if(focusNode!=null){
			
			postOrderTraverse(focusNode.left);
			postOrderTraverse(focusNode.right);
			System.out.println(focusNode);
		}
	}
	
	public Node findNode(int key){
		
		Node focusNode = root;
		while(focusNode.key!=key){
			if(key<focusNode.key){
				focusNode = focusNode.left;
			}else{
				focusNode = focusNode.right;
			}
			if(focusNode==null) return null;
		}
		return focusNode;
	}

	public boolean remove(int key){
		
		Node focusNode = root;
		Node parent = root;
		boolean isleftch = true;
		while(focusNode.key !=key){
			parent = focusNode;
			if(key<focusNode.key){
				isleftch = true;
				focusNode = focusNode.left;
			}else{
				isleftch = false;
				focusNode = focusNode.right;
			}
			if(focusNode == null) return false;
		}
		if(focusNode.left == null && focusNode.right==null){
			if(focusNode == root) root = null;
			
			else if(isleftch) parent.left = null;
			
			else parent.left = null;
		}
		
		else if(focusNode.right == null){
			if(focusNode == root)	root = focusNode.left;
			
			else if(isleftch)	parent.left = focusNode.left;
			
			else	parent.right = focusNode.left;
		}
		
		else if(focusNode.left == null){
			if(focusNode == root)	root = focusNode.right;
			
			else if(isleftch)	parent.left = focusNode.right;
			
			else	parent.right = focusNode.left;
		}
		
		else{
			Node replacement = getReplacementNode(focusNode);
			
			if(focusNode == root)
				root = replacement;
			
			else if(isleftch)
				parent.left = replacement;
			
			else
				parent.right =replacement;
			
			replacement.left = focusNode.left;
		}
		
		return true;	
	}
	
	public Node getReplacementNode(Node replacedNode){
		
		Node replacementParent = replacedNode;
		Node replacement = replacedNode;
		Node focusNode = replacedNode.right;
		
		while(focusNode !=null){
			replacementParent = replacement;
			replacement = focusNode;
			focusNode = focusNode.left;
		}
		if(replacement!=replacedNode.right){
			replacementParent.left = replacement.right;
			replacement.right =replacedNode.right;
		}
		
		return replacement;
	}
	public static void main(String[] args) {
		
		BinaryTree test = new BinaryTree();
		test.addNode(30, "Steve Jobs");
		test.addNode(12, "Alan");
		test.addNode(50, "Boss");
		test.addNode(29, "Vice President");
		test.addNode(19, "Hello");
		
		//test.inOrderTraverse(test.root);
		//test.postOrderTraverse(test.root);
		test.preOrderTraverse(test.root);
		System.out.println(test.findNode(30));
	}

}
