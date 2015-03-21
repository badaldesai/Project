import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Undetect {
	
	private static Scanner sc;
	public static void main(String[] args) throws Exception {
		FileReader fr = new FileReader("/Users/mac/Desktop/JudgeInput.txt");
		BufferedReader br = new BufferedReader (fr);
		Undetect un = new Undetect();
		String line;
		ArrayList<String> in = new ArrayList<String>();
		while((line=br.readLine())!=null){
			String[] input = line.trim().split("\n");
			for(int i=0;i<input.length;i++){
				in.add(input[i]);
			}
		}
		String[] inp =new String[in.size()];
		inp = in.toArray(inp);
		check(inp);
		
	}
	public static void check(String[] input){
		int[] a = new int[input.length];
		int j=0;
		while(j<=input.length){
			if(a[j]==1) break;
			
			if(input[j].contains("NEXT")){
				a[j] = 1;
				j++;
			}
			
			else if(input[j].contains("GOTO")){
				a[j] = 1;
				String[] b = input[j].split("\\s+");
				int i = Integer.parseInt(b[1]);
				j=i-1;
				//System.out.println(j);
			}
			
		}
		
		for(int i=0;i<a.length;i++){
			if(a[i]!=1) System.out.println(i+1);
			
		}
	}
}
