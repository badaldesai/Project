
public class tapequ {

	public static void main(String[] args) {
		int[] a = {3,1,5,23,92,1};
		int ans = soluti(a);
		System.out.println(ans);
	}
	
	public static int soluti(int[] a){
		
		int total = 0;
		int total1 = 0;
		int ans;
		for(int i=0;i<a.length;i++){
			total = total + a[i];
		}
		total1 = a[0];
		ans = Math.abs(total-total1);
		for(int i=1;i<a.length;i++){
			if(Math.abs(total1-total)<ans){
				ans = Math.abs(total1-total);
			}
			total1 = total1 + a[i];
			total = total - a[i];
		}
		return ans;
	}

}
