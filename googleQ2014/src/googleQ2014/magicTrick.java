package googleQ2014;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class magicTrick {

	private static Scanner sc;
	public static void main(String[] args) throws FileNotFoundException {
		sc = new Scanner(new File("/Users/mac/Desktop/Input.txt"));
		PrintStream out = new PrintStream(new FileOutputStream("/Users/mac/Desktop/output.txt"));
		System.setOut(out);
		int cases = sc.nextInt();
		for(int i=0;i<cases;i++){
			int solved = magicsolved();
			String ans;
			switch (solved){
			case 0 : 
				ans = "Bad magician!";
				break;
			case 17 : 
				ans = "Volunteer cheated!";
				break;
			default : 
				ans = String.valueOf(solved);
				break;
			}
			System.out.println("Case #" + (i+1) + ": " + ans);
		}
		
	}
	
	public static int magicsolved(){
		String S1 = null;
		String S2 = null;
		for(int i=1; i<3; i++){
			int pick = sc.nextInt();
			sc.nextLine();
			for(int j=1; j<5; j++){
				String temp=sc.nextLine();
				if(pick==j && i==1)
					S1 = temp;
				else if(pick==j && i==2)
					S2 = temp;
			}
			
		}
		//System.out.println(S1);
		//System.out.println(S2);
		String[] picked1 = S1.split(" ");
		String[] picked2 = S2.split(" ");
		int[] prow1 = new int[4];
		int[] prow2 = new int[4];
		for(int i=0;i<4;i++){
			prow1[i] = Integer.parseInt(picked1[i]);
			prow2[i] = Integer.parseInt(picked2[i]);
		}
		int matched = 0;
		for (int i=0; i<4;i++){
			for(int j=0;j<4;j++){
				if(prow1[i] == prow2[j] && matched==0){
					matched = prow1[i];
				}
				else if(prow1[i] == prow2[j] && matched!=0){
					return 0;
				}
			}
		}
		if(matched == 0)
			return 17;
		
		return matched;
	}

}
