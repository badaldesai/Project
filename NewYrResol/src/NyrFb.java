import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class NyrFb {
	
	private static Scanner sc;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		sc = new Scanner(new File("/Users/mac/Desktop/new_years_resolution_example_input.txt"));
		//PrintStream out = new PrintStream(new FileOutputStream("/Users/mac/Desktop/output.txt"));
		//System.setOut(out);
		int N = sc.nextInt();
		int i = 0;
		boolean answer;
		while(i<N){
			System.out.print("Case #" + (i+1) + ": ");
			answer=Solve();
			i++;
			if(answer == true) System.out.print("yes");
			else System.out.print("no");
			System.out.println();
		}
		//System.out.println(N);
	}
	public static boolean Solve(){
		int Gp = sc.nextInt();
		int Gc = sc.nextInt();
		int Gf = sc.nextInt();
		int subcase = sc.nextInt();
		List<Integer> pro= new ArrayList<Integer>();
		List<Integer> carb= new ArrayList<Integer>();
		List<Integer> fat= new ArrayList<Integer>();
		for(int i=0;i<subcase;i++){
			int temp1 = sc.nextInt();
			int temp2 = sc.nextInt();
			int temp3 = sc.nextInt();
			if(temp1<=Gp && temp2<=Gc && temp3<=Gf){
				pro.add(temp1);
				carb.add(temp2);
				fat.add(temp3);
			}
		}
		if(pro.isEmpty()) return false;
		int total1=0, total2=0, total3=0;
		for(int i=0;i<pro.size();i++){
			total1 = total1 + pro.get(i);
			total2 = total2 + carb.get(i);
			total3 = total3 + fat.get(i);
		}
		//System.out.print(total1 + " " + total2 + " " + total3);
		if(total1<Gp || total2<Gc || total3<Gf)	return false;
		int j = pro.size() - 1;
		for(int i=0;i<pro.size();i++){
			if(total1==Gp);
		}
		//System.out.print(pro.get(0) + " " + temp2 + " " + temp3);
		
		
		return false;
		
	}
}
