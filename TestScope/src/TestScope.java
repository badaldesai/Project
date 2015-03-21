import java.util.Arrays;


public class TestScope {
	private static int n;
	public static void main(String[] args) {
		int[] input2 = {1,13,5,10,9,6,7,4,12};
		int input1 = 50;
		int[] out=function(input1,input2);
		for(int i=0;i<out.length;i++) System.out.println(out[i]);
	}
		public static int[] function(int input1,int[] input2){
			n=input2.length-1;
			if(input2.length>0 && input1>0){
				
				int n=input1;
				sort(input2);
				int j=0;
				for(int i=0;i<input2.length;i++){
					if(input2[i]<=input1){
						j++;
						input1 = input1-input2[i];
					}
				}
				int i;
				for(int k=j;k<input2.length;k++){
					
					if(input2[k]>n) break;
					int rep = input2[k];
					int[] temp = Arrays.copyOf(input2, input2.length);
					for(i=0;i<j;i++){
						temp[i] = rep-temp[i]-input1;
						if(temp[i]<0) break;
					}
					//input2[i-1] = rep;
					//System.out.println(rep);
					//for(int l=0;l<input2.length;l++) System.out.println(input2[l]);
					//System.out.println(input2[1]);
					if(temp[i]>0) break;
					input1 = temp[i-1];
					
				}
				//System.out.println(rep);
				int[] out = {j,input1};
				return out;
			}
			
			else{ 
				int out[] = new int[2];
				if(input1>0) {
					out[0]=0;
					out[1]=input1; }
				else{
					out[0]=0;
					out[1]=0;}
				return out;
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
