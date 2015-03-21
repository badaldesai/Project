
public class Palintest {

	public static void main(String[] args) {
		Palin p = new Palin();
		String d = "A ble Elb a";
		String g = d.toLowerCase();
		int y = p.checker(g);
		if(y == 1){
			System.out.println("The given string is Palindrome");
		}
		else{
			System.out.println("The given string is not Palindrome");
		}

	}

}
