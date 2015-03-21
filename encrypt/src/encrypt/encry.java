package encrypt;

import java.io.File;
import java.util.Scanner;


public class encry {
	private static Scanner sc;
	public static void main(String[] args) throws Exception{
		String[] arr = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String[] arr1 = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String[] brr = {"4B","48","49","4E","4F","4C","4D","42","43","40","41","46","47","44","45","5A","5B","58","59","5E","5F","5C","5D","52","53","50"};
		String[] brr1 = {"6B","68","69","6E","6F","6C","6D","62","63","60","61","66","67","64","65","7A","7B","78","79","7E","7F","7C","7D","72","73","70"};
		sc = new Scanner(new File("/Users/mac/Desktop/input.txt"));
		//char [] in = new char[100];
		char a,b;
		int i=0;
		while(sc.hasNext()){
			String arry_str;
			arry_str=sc.next();
			int l=arry_str.length();
			System.out.println(l);
			while(l>0) {
				//System.out.println(sc.next());
					a=arry_str.charAt(i);
					i++;
					b=arry_str.charAt(i);
					i++;
					l=l-2;
					String ans;
					StringBuilder cn = new StringBuilder().append(a).append(b);
					String c = cn.toString();
					//System.out.println(c);
					if(a=='4'|| a=='5'){
						for(int j=0;j<brr.length;j++){
							if(c.equals(brr[j])){
								ans = arr[j];
								System.out.print(ans);
							}		
						}
					}
					else if(a=='6' || a =='7'){
						for(int j=0;j<brr1.length;j++){
							if(c.equals(brr1[j])){
								ans = arr1[j];
								System.out.print(ans);
							}		
						}
					}
					else if(c.equalsIgnoreCase("0A")) System.out.print(" ");
					
					else if(c.equalsIgnoreCase("27")) System.out.println();
					
					else System.out.print(c);
			}
			
		}
		/*for(int j=0;j<in.length;j++){
			System.out.println(in[i]);
		}*/
	}

}
