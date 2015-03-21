import java.util.*;

public class DupNum {

	private static Scanner sc;
	public static void main(String[] args) {
		DupNum Dup= new DupNum();
		
		// Get user input array
		int[] a= new int[10];
		sc = new Scanner(System.in);
		System.out.println("Enter the input numbers");
		for(int j=0;j<10;j++){
			a[j]=sc.nextInt();
		}
		
		//Call Function to find duplicates
		Dup.find(a);
		Dup.remove(a);

	}
	public void find(int[] a){
		int[] b= new int[100];
		int[] c= new int[10];
		int k=0;
		for(int i=0;i<10;i++){
			int j=a[i];
			if(a[i]==b[j]){
				c[k]=a[i];
				k++;
			}
			else{
				b[j]=j;
			}
		}
		System.out.print("The Duplicate numbers in list are:");
		for(int j=0;j<k;j++){
			System.out.print(c[j]+",");
		}
	}
	public void remove(int[] a){
		HashSet<Integer> hs =new HashSet<Integer>();
		for(int i=0;i<a.length;i++){
			hs.add(a[i]);
		}
		System.out.println();
		System.out.println(hs);
	}
}
