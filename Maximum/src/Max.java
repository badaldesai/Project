import java.util.*;
public class Max {

	private static Scanner sc;

	public static void main(String args[]){
		Max m=new Max();
		
		int[] a= new int[10];
		sc = new Scanner(System.in);
		System.out.println("Enter the array of number:");
		for(int i=0; i<10; i++){
			a[i]=sc.nextInt();
		}
		m.maximum(a);
	}
	
	private void  maximum(int[] a){
		Stack<Integer> maxi = new Stack<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		maxi.push(a[0]);
		for(int j=0; j<10;j++){
			if(a[j]>maxi.firstElement()){
				maxi.pop();
				maxi.push(a[j]);
			}
			stack.push(a[j]);
		}
		System.out.println("The largest number is" + maxi);
	}
}
