

public class machineOp {

	public static void main(String[] args) {
		int[] input = {1,5,9,2,3,5,6};
		int[] output= machinegen(input);
		String out=Integer.toString(output[0]);
		System.out.println(out);
	}
	
	public static int[] machinegen(int[] input1){
		
		int len = input1.length;
		if(len==1)
			return input1;
		
		int [] temp = new int[len-1];
		for(int i=0; i<input1.length-1;i++){
			temp[i] = input1[i+1] - input1[i];
		}
		
		return machinegen(temp);
	}

}
