import java.util.*;
public class PremTech {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the length of permutation:");
		int len = sc.nextInt();
		int in[] = new int[1];
		System.out.println("Enter the array:");
		
		for(int i=0;i<len;i++){
			in[i] = sc.nextInt();
		}
		
		int out=correctResult(in);
		System.out.println(out);
	}
	public static int correctResult(int[] input)
	{
		int n=input.length;
		int count = 0;
		for(int i=0;i<n;i++){
			int j = input[i];
		}
		
			
		return count;
	}

}
