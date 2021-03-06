package pancake;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Pancake {
	private static Scanner sc;
	public static void main(String[] args) throws FileNotFoundException {
		sc = new Scanner(new File("/Users/mac/Desktop/Input.txt"));
		//PrintStream out = new PrintStream(new FileOutputStream("/Users/mac/Desktop/output.txt"));
		//System.setOut(out);
		int cases = Integer.parseInt(sc.nextLine());
		for (int i=1;i<=cases; i++){
			int time = minTime(); 
			System.out.println("Case #" + i + ": " + time);
		}
	}
	
	public static int minTime(){
		
		int numofPerson = sc.nextInt();
		int[] persons = new int[numofPerson];
		int[] counts = new int[1001];
		for (int i=0; i<numofPerson; i++){
			persons[i] = sc.nextInt();
		}
		for (int i:persons){
			counts[i]++;
			//System.out.println(i + " "+counts[i]);
		}
	//	System.out.println(total)
		int min =10000;
		for(int j = 1;j<=counts.length;j++){
			int moves=0;
			for(int i=0; i<counts.length;i++)
				moves=moves+((i-1)/j)*counts[i];
			if(moves+j<min)
				min=moves+j;
		}
		return min;
	}
	
	public static int shareCookie(int t, int numberOfPerson, ArrayList<Integer> cookie, int totalCokie){
		
		int sub= totalCokie/numberOfPerson; 
		int max = 0;
		int index=0;
		if(totalCokie%numberOfPerson!=0)
			sub=sub+1;
		if (numberOfPerson == 1 && totalCokie>3){
			int temp = cookie.get(index) - cookie.get(index)/2;
			cookie.set(index, cookie.get(index)/2);
			cookie.add(temp);
			t = t+1;
			numberOfPerson=numberOfPerson+1;
			return shareCookie(t,numberOfPerson,cookie,totalCokie);
		}
		
		for (int i=0; i<numberOfPerson; i++){
			int temp = cookie.get(i) - sub;
			
			//System.out.print(cookie.get(i)+ " ");
			if ( temp > max){
				max = temp;
				index = i;
			}
		}
		System.out.println();
		//System.out.println(max);
		if (max < 1 || cookie.get(index)<4){
			return cookie.get(index) + t;
		}
		else{
			int temp = cookie.get(index) - cookie.get(index)/2;
			cookie.set(index, cookie.get(index)/2);
			cookie.add(temp);
			t = t+1;
			numberOfPerson=numberOfPerson+1;
			return shareCookie(t,numberOfPerson,cookie,totalCokie);
		}
	}
}
