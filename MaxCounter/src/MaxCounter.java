
public class MaxCounter {

	public static void main(String[] args) {
		int N = 5;
		int[] A = {1,2,4,2,6,3,2,4};
		int[] ans = Solution(N,A);
		for(int i=0;i<ans.length;i++){
			System.out.println(ans[i]);
		}
	}
	public static int[] Solution(int N, int[] A){
		final int cond = N+1;
		int[] solu = new int[N];
		int curmax = 0;
		int lastup = 0;
		for(int i=0;i<A.length;i++){
			
			if(A[i]==cond){
				lastup = curmax;
			}
			else{
				if(solu[A[i]-1]<lastup)	solu[A[i]-1] = lastup+1;
				else	solu[A[i]-1]++;
				if(solu[A[i]-1]>curmax)	curmax=solu[A[i]-1];	
			}
		}
		for(int i=0;i<solu.length;i++){
			if(solu[i]<lastup)
				solu[i] = lastup;
			
		}
		
		return solu;
	}
}
