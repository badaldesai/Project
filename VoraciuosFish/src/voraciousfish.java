import java.util.*;
public class voraciousfish {

	public static void main(String[] args) {
		int[] size = {4,3,2,1,5};
		int[] movdir = {0,1,0,0,0};
		int alive=numofalive(size,movdir);
		System.out.println(alive);
	}
	public static int numofalive(int[] A,int[] B){
		Stack<Integer> size = new Stack<Integer>();
		int alive = 0;
		for(int i=0; i<A.length;i++){
			if (B[i] == 0){
				
				if (size.size()==0)
					alive++;
				else{
					while (!size.empty()){
						if(A[i]>size.peek())
							size.pop();
						else
							break;
					
				}
				if (size.empty()){
					alive++;
				}
			}
			}else{
				size.push(A[i]);
			}
				
			System.out.println(alive);
		}
		alive = alive + size.size();	
		return alive;
	}
}
