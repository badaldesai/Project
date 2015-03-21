import java.util.Scanner;


public class Fibonacci {

	public static void main(String[] args) {
		int num;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the value of what u want Fibonacc Series:");
		num = sc.nextInt();	
		for(int i=0;i<num;i++){
			System.out.print(Fibonacci.fibo(i) + " ");
		}
		
	}
	static int fibo(int n){
		if(n==1) return 1;
		else if(n==0) return 0;
		
		else{
			int f = fibo(n-1) + fibo (n-2);
			return f;
		}
	}
}
