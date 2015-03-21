import java.io.File;
import java.util.HashSet;
import java.util.Scanner;


public class Twosum {

	private static Scanner sc;
	public static void main(String[] args) throws Exception {
		sc = new Scanner(new File("/Users/mac/Desktop/algo1-programming_prob-2sum.txt"));
		HashSet<Long> table = new HashSet<Long>();
		long[] a = new long[1000000];
		int i = 0;
		while(sc.hasNextLong()){
			a[i] = sc.nextLong();
			i++;
		}
		for(int k=0;k<a.length;k++)	table.add(a[k]);
		
		System.out.println(table.size());
		
		int count = 0;
		for(long j=-10000;j<10001;j++){
			for(int k=0;k<a.length;k++){
				
				if(j-a[k]!=a[k] && table.contains(j-a[k])){
					count++;
					break;
				}
			}
		}
		System.out.println(count);
	}

}
