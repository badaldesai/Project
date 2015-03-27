package cookieclick;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class cookieclick {

	private static Scanner sc;
	public static void main(String[] args) throws FileNotFoundException {
		sc = new Scanner(new File("/Users/mac/Desktop/Input.txt"));
		//PrintStream out = new PrintStream(new FileOutputStream("/Users/mac/Desktop/output.txt"));
		//System.setOut(out);
		int cases = Integer.parseInt(sc.nextLine());
		for(int i=0; i<cases; i++){
			double mintime = fastcookiefinder();
			System.out.println("Case #"+ (i+1) + ": " + mintime);
		}
	}
	public static double fastcookiefinder(){
		String line = sc.nextLine();
		String[] numbers = line.split(" ");
		double cost= Double.parseDouble(numbers[0]);
		double fboost = Double.parseDouble(numbers[1]);
		double target = Double.parseDouble(numbers[2]);
		double rate = 2.0;
		double fasttime = cookie(cost, fboost, target, rate);
		return fasttime;
	}
	
	public static double cookie(double cost, double fboost, double target, double rate){
		
		double time = 0;
		if ((target/rate)>(cost/rate + target/(rate+fboost))){
			rate = rate + fboost;
			double timetaken = cookie(cost,fboost,target,rate);
			time = time + timetaken;
		}else{
			return (target/rate);
		}
		
		return time;
	}
}
