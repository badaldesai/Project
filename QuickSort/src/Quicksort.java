import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Quicksort {
	private static Scanner sc;
	public static void main(String[] args) throws Exception{
		Quicksort q = new Quicksort();
		sc = new Scanner(new File("/Users/mac/Desktop/QuickSort.txt"));
		int[] inp = new int[10000];
		int i=0;
		while(sc.hasNextInt()){
			inp[i] = sc.nextInt();
			//System.out.println(inp[i]);
			i++;
		}
		long comp =0;
		comp=q.sorti(inp,0,inp.length);
		for(int j=0;j<inp.length;j++){
			//System.out.println(inp[j]);
		}
		System.out.println(comp);
		
	}
	public long sorti(int in[], int f, int l){
		//Median of 3 method
		int m=0;
		m=((l+f-1)/2);
		//System.out.println(m);
		int med = 0;
		if(in[f]>in[m]){
			if(in[m]>in[l-1]){
				med = m;
			}else if(in[f]>in[l-1]) med = l-1;
			else med = f;
		}else{
			if(in[f]>in[l-1]) med =f;
			else if(in[m]>in[l-1]) med = l-1;
			else med = m;
		}
		
		//System.out.println(in[med] + " " +med);
		
		//Randomized pivot selection
		//Random rand = new Random();
		//int num=f+rand.nextInt(l-f);
		//int num = in[l-1];
		swap(in,med,f);
		int pi = in[f];
		//System.out.println(pi);
		int j=f+1;
		for(int i=f+1;i<l;i++){
			if(pi>in[i]){
				swap(in,i,j);
				j++;
			}
		}
		int temp = in[j-1];
		in[j-1] = pi;
		in[f] = temp;
		long comp=0;
		comp = l-f-1;
		//System.out.print(comp);
		if(j-1>f) comp+=sorti(in,f,j-1);
		if(j<l) comp+=sorti(in,j,l);
		return comp;
	}
	
	public void swap(int in[], int dex1, int dex2) {
	    int temp = in[dex1];
	    in[dex1] = in[dex2];
	    in[dex2] = temp;
	  }
}
