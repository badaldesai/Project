import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;


public class palin {
	private static Scanner sc;
	public static void main(String[] args) throws Exception {
		
		FileReader fr = new FileReader("/Users/mac/Desktop/PracticeInput.txt");
		BufferedReader br = new BufferedReader (fr);
		String line;
		palin p = new palin();
		while((line=br.readLine())!=null){
			String[] input = line.trim().split("\n");
			for(int i=0;i<input.length;i++){
				System.out.println();
				System.out.println(input[i]);
				p.checker(input[i]);
			}	
		}
		String d = "A ble Elb a";
		String g = d.toLowerCase();
		int z=1;
		if(z == 1){
			System.out.println("The given string is Palindrome");
		}
		else{
			System.out.println("The given string is not Palindrome");
		}

	}
	void checker(String g) {
		String h = g.toLowerCase().replaceAll("[\\W]", "");
		String longest = "";
		for(int i=0;i<h.length();i++){
			for(int j=h.length()-1;j>i;j--){
				if(isPalindrome(h.substring(i,j))){
					if(h.substring(i, j).length()>longest.length()){
						longest = h.substring(i, j);
					}
				}
				
			}
		}
		System.out.println(longest);
	}
	private boolean isPalindrome(String s){
		int end = s.length()-1;
        for(int i=0; i<s.length()/2; i++){
            if(s.charAt(i)!=s.charAt(end)){
                return false;
            }
            end--;
        }
        return true;
    }
}