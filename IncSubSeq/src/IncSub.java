public class IncSub {

	public static void main(String[] args) {
		int[] A = {3,2,6,4,5,1};
		int sol = subsequ(A);
		System.out.println(sol);

	}
	public static int subsequ(int[] A){
		int[] B= new int[A.length];
		B[0] = 1;
		for(int i=1;i<A.length;i++){
			for(int j=0;j<i;j++){
				if(A[i]>A[j] && B[i]<=B[j])	B[i]=B[j]+1;
			}
			
		}
		int max = B[0];
		for(int i=1;i<B.length;i++){
			if(max<B[i])	max=B[i];
			//System.out.println(B[i]);
		}
		return max;
	}

}
