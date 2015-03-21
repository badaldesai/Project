
public class driver {

	public static void main(String[] args) {
		
		int[] input1 = {1,2,3,4,5,3,7,8,10};
		driver d = new driver();
		int len = driver.longestSeq(input1);
		System.out.println(len);
	}
	public static int longestSeq(int[] input1){
		int n= input1.length;
		int j=0;
		int largest =0;
		for(int i=0;i<input1.length;i++){
			if(input1[i]>largest){
				largest = input1[i];
				j++;
			}
		}
		return j;
	}
}
