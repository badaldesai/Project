import java.io.File;
import java.util.Scanner;


public class MedMat {
	private static Scanner sc;
	private static int n;
	public static void main(String[] args) throws Exception {
		sc = new Scanner(new File("/Users/mac/Desktop/IntegerArray.txt"));
		int in = 0;
		int i=0;
		while(sc.hasNextInt()){
			in = sc.nextInt();
			n=i+1;
			median(in);
		}
		
	}
	
	public static void median(int a){
		if(n%2==0){
			
		}
		
		
	}

}
