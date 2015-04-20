package standOvation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class standOvation {
	
	private static Scanner sc;
	public static void main(String[] args) throws FileNotFoundException {
		sc = new Scanner(new File("/Users/mac/Desktop/A-large.in"));
		PrintStream out = new PrintStream(new FileOutputStream("/Users/mac/Desktop/output.txt"));
		System.setOut(out);
		int cases = Integer.parseInt(sc.nextLine());
		for (int i=1;i<=cases; i++){
			int extraPerson = getPersons();
			System.out.println("Case #" + i + ": " + extraPerson);
		}
	}
	
	public static int getPersons(){
		
		String input = sc.nextLine();
		String[] input1 = input.split(" "); 
		int len = Integer.parseInt(input1[0]);
		//System.out.println(input1[1]);
		if (len == 0)
			return 0;
		int extra = 0;
		int persons= 0;
		for (int i=0; i<=len; i++){
			int num = Character.getNumericValue(input1[1].charAt(i));
			if (persons<i){
				extra++;
				persons++;
			}
			persons = persons + num;
		}
		
		return extra;
	}
}
