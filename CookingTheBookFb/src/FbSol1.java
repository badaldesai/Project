import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.*;


@SuppressWarnings("unused")
public class FbSol1 {
	
	private static Scanner sc;

	public static void main(String args[]) throws FileNotFoundException{
		
		sc = new Scanner(new File("/Users/mac/Desktop/College/Facebook/cooking_the_books.txt"));
		//PrintStream out = new PrintStream(new FileOutputStream("/Users/mac/Desktop/output.txt"));
		//System.setOut(out);
		int i = 0;
		int n = sc.nextInt();
		int[] num = new int[n];
		while(sc.hasNextInt()){
			num[i] = sc.nextInt();
			System.out.print("Case #" + (i+1) + ": ");
			Solution(num[i]);
			i++;	
		}
		
		
		//out.close();
		//System.out.println(num[4]);*/
	}
	private static void Solution(int num){
		List<Integer> digi= new ArrayList<Integer>();
		int max = 0;
		int min = num;
		while(num>0){
			digi.add(num%10);
			num/=10;
		}
		for(int i=0;i<digi.size();i++){
			if(digi.get(i)>max)	max=digi.get(i);
			if(digi.get(i)<min && digi.get(i)>0) min = digi.get(i);
		}
		int n = digi.size();
		System.out.print(max);
		for(int i=n-2;i>=0;i--){
			if(digi.get(i)==max)	System.out.print(digi.get(n-1));
			else{
				System.out.print(digi.get(i));
			}
		}
		System.out.print(" " + min);
		for(int i=n-2;i>=0;i--){
			if(digi.get(i)==min)	System.out.print(digi.get(n-1));
			else	System.out.print(digi.get(i));
		}
		System.out.println();
	}
	
}
