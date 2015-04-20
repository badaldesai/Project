import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class Ominous {

	private static Scanner sc;
	public static void main(String[] args) throws FileNotFoundException {
		sc = new Scanner(new File("/Users/mac/Desktop/D-large-practice.in"));
		PrintStream out = new PrintStream(new FileOutputStream("/Users/mac/Desktop/output.txt"));
		System.setOut(out);
		int cases = Integer.parseInt(sc.nextLine());
		for (int i=1;i<=cases; i++){
			int winner = solution();
			String answer;
			if (winner == 1)
				answer = "GABRIEL";
			else
				answer = "RICHARD";
			
			System.out.println("Case #" + i + ": " + answer);
		}
	}
	
	public static int solution(){
		
		int ominus = sc.nextInt();
		int gridr = sc.nextInt();
		int gridc = sc.nextInt();
		int grid = gridr * gridc;
		//System.out.println(grid);

		if(grid%ominus!=0)
			return 0;
		
		if (ominus>= 7)
			return 0;
		else if(ominus==6){
			if(Math.min(gridc, gridr) <=3)
				return 0;
		}
		else if(ominus==5){
			if(Math.min(gridr, gridc)<=2 || 
					Math.min(gridc, gridr)==3 && Math.max(gridr, gridc)==5)
				return 0;
		}
		else if(ominus==4){
			if(Math.min(gridc, gridr)<=2)
				return 0;
		}
		else if(ominus==3){
			if(Math.min(gridr, gridc)<=1)
				return 0;
		}
		
			

		
		return 1;
	}

}
