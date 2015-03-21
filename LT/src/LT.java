import java.util.*;
public class LT {

	private static Scanner sc;

	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		System.out.println("Enter the number string");
		String str = sc.next();
		System.out.println("enter the number you want to remove");
		int input = sc.nextInt();
		String output = Ltlargest(str,input);
		System.out.println("The reuired number is " + output);
	}
	
	public static String Ltlargest(String str, int input){
		
		char[] str1 = str.toCharArray(); 
		if(str.length()<input) return "0";
		char temp1 = str1[0];
		char temp2 = str1[1];
		char[] str2 = new char[str1.length-input];
		if(input>2){
			for(int i=1;i<str1.length;i++){
				if(str1[i]<temp1){
					temp2 = temp1;
					temp1 = str1[i];
				}
			}
		}
		if(input<=2){
			for(int i=1;i<str1.length;i++){
				if(str1[i]<temp1){
					temp2 = temp1;
					temp1 = str1[i];
				}
			}
		}
		
		return "123";
	}

}
