import java.util.Scanner;

public class substrtest {

	public static void main(String[] args) {
		String s, sub;
		Scanner a = new Scanner(System.in);
		System.out.println("Enter main string:");
		s = a.nextLine();
		// System.out.println(s);
		System.out.println("Enter a string to check for substring:");
		sub = a.nextLine();
		boolean result = strcheck(s, sub);
		if (result == true) {
			System.out.println("Is substring");
		} else {
			System.out.println("not substring");
		}
	}

	static boolean strcheck(String s, String sub) {
		int n = s.length();
		int l = sub.length();
		int i = 0, j = 0;
		if(l>n){return false;}
		while (i != n) {
			if (sub.charAt(j) == s.charAt(i)) {
				if (j == l - 1) {
					return true;
				}
				
				else if ((i+1)<n && sub.charAt(j + 1) != s.charAt(i + 1)) {

					System.out.print(j);
					j = -1;
				}
				j++;
			}
			i++;
		}
		return false;
	}

}
