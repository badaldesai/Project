package stonewall;

import java.util.*;

public class stonewall {

	public static void main(String[] args) {
		int[] input = {8,8,5,7,9,8,7,4,8};
		int result = solution(input);
		System.out.println(result);

	}
	
	public static int solution(int[] H) {
	    
	    Stack<Integer> walls = new Stack<Integer>();
	    int count = 0;
	    
	    for(int i=0; i<H.length; i++){
	        while (!walls.empty() && H[i]<walls.peek()){
	                walls.pop();
	                if (walls.empty())
	                	break;
	            }
	            if(!walls.empty() && H[i]==walls.peek())
	                continue;
	            else{
	                walls.push(H[i]);
	                count++;
	            }
	        }
	    
	   return count;
	 }
}
