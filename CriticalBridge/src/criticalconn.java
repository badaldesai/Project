import java.util.*;
public class criticalconn {

	public static void main(String[] args) {
		criticalconn cr = new criticalconn();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the string");
		String s = sc.nextLine();
		String[] s1 = s.split("\\s+");
		System.out.println("the resultant string is "+s);
		System.out.println(s1.length);
		for(int i=s1.length-1;i>=0;i--){
			System.out.print(s1[i]+" ");
		}
		
	}
}
