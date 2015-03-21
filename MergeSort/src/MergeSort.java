import java.io.*;
import java.util.Scanner;

public class MergeSort {

	private static Scanner sc;
	public static void main(String[] args) throws IOException {
		MergeSort m = new MergeSort();
		sc = new Scanner(new File("/Users/mac/Desktop/IntegerArray.txt"));
		int[] inp = new int[100000];
		int i=0;
		while(sc.hasNextInt()){
			inp[i] = sc.nextInt();
			//System.out.println(inp[i]);
			i++;
		}
		long invn=m.sorti(inp, 0, inp.length-1);
		for(int j=0;j<inp.length;j++){
			System.out.println(inp[j]);
		}
		System.out.println(invn);
	}
	public long sorti(int[] a,int f, int n){
		long ninv = 0;
		if(n>f){
			
			int middle = f + (n-f)/2;
			ninv=sorti(a,f,middle);
			ninv+=sorti(a,middle+1,n);
			ninv+=merge(a,f,middle,n);
		}
		return ninv;
	}
	public long merge(int a[],int l,int mid, int r){
		int aux[] = new int[a.length];
		for(int k=l; k<=r;k++){
			aux[k]= a[k];
		}
		int i=l, j=mid+1;
		int count =0;
		long inv=0;
		for(int k=l; k<=r; k++){
			if(i>mid) a[k]=aux[j++];
			else if(j>r) a[k]=aux[i++]; 
			else if(aux[j]<aux[i])
			{
				a[k]=aux[j++];
				count = mid-i+1;
				inv = inv+count;
			}
			else a[k] = aux[i++];
		}
		return inv;
	}

}
