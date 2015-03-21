import java.util.*;
public class fdrp {
	
	private static Scanner sc;
	private static Scanner sc2;
	public static void main(String[] args){
		sc = new Scanner(System.in);
		System.out.println("Enter the string:");
		String str=sc.nextLine();
		//System.out.print(str);
		String[] stri = str.split(" ");
		System.out.println("Enter the word you want to replace with");
		String strr = sc.next();
		System.out.println("Enter the word you want to replace:");
		String strf = sc.next();
		String rstr = str.replaceAll(strf, strr);
		System.out.println(rstr);
		fdrp.replace(stri,strr,strf);
	}
	static void replace(String[] st, String strr, String strf){
		
		System.out.println("The new replace line is:");
		for(int i=0; i<st.length; i++){
			//System.out.println(strf);
			if(st[i].equals(strf)){
				st[i]=strr;
			}
			System.out.print(st[i] +" ");
		}
	}
	
}
