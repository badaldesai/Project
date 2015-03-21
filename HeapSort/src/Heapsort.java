
public class Heapsort {
	private static int n;

	public static void main(String[] args) {
		int a[] = {};
		n=a.length-1;
		sort(a);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		
	}
	public static void sort(int[] a){
		buildheap(a);
		for(int i=n;i>0;i--){
			swap(a,0,i);
			n=n-1;
			maxheap(a,0);
		}
	}
	
	public static void buildheap(int[] a){
		
		for(int i=n/2;i>=0;i--){
			//System.out.println(i);
			maxheap(a,i);
		}
		//for(int i=0;i<n;i++) System.out.println(a[i]);
	}
	public static void maxheap(int[] a, int i){
		int left = 2*i;
		int right = 2*i+1;
		int max = i;
		if(left <= n && a[left]>a[max]) max = left;
		
		if(right <=n && a[right]>a[max]) max = right;
		
		if(max!=i){
			swap(a,i,max);
			maxheap(a,max);
		}
	}
	public static void swap(int[] a, int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] =temp;
	}
}


/*else if(input1<=0 && input2.length>=0){
	int j=0;
	for(int i=0;i<input2.length;i++){
		if(input2[i]<=0){
			j++;
		}
	}
	int[] out
}
*/
