/**
 * @author Badal Desai
 * @email : badaldesai@gmail.com
 * 
 */

package encrpt2;

import java.io.File;
import java.util.Scanner;

public class encry {

	private static Scanner sc;
	// String Used to get position of the encoded character
	static String comp = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

	public static void main(String[] args) throws Exception {
	
		// Input.txt contains encoded string which needed to be decoded
		sc = new Scanner(new File("input.txt"));
		char a;
		String out = "";
		
		while (sc.hasNext()) {
			String arry_str;
			arry_str = sc.next();
			int l = arry_str.length();
			for (int i = 0; i < l; i++) {
				a = arry_str.charAt(i);
				// System.out.println(a);
				out = out + binaryout(a);
				// System.out.print(out);
			}
			//Splits the string of binary into 8 required binary
			String[] ans = out.split("(?<=\\G.{8})");
			answer(ans);
		}
	}
	/**
	 * Binaryout functions returns the binary equivalent of the position of the character.
	 * 
	 * @param m is the character of encoded by the message
	 * 
	 * @return String is the binary equivalent of the each character passed in function
	 */
	
	public static String binaryout(char m) {
		String bin = "";
		for (int i = 0; i < comp.length(); i++) {
			if (comp.charAt(i) == m) {
				
				// function gives the binary equivalent of the number
				bin = Integer.toBinaryString(i);
				
				//Loops for the padding purposes
				while (bin.length() < 6)
					bin = "0" + bin;
			}
		}
		return bin;
	}

	/**
	 * Function answer prints the decoded Ascii message
	 * 	
	 * @param ans is binary equivalent of the decoded message
	 * 
	 */
	public static void answer(String[] ans) {

		int sub = 79;
		for (int i = 0; i < ans.length; i++) {
			// System.out.println(ans[i]);
			//Gets the equivalent integer of binary
			int output = Integer.parseInt(ans[i], 2);
			int output1 = output - sub;
			if (output1 < 1) {
				output1 = 256 + output - sub;
			}
			// Get the equivalent Ascii to decode message
			char decodedmsg = (char) output1;
			sub = sub + 1;
			System.out.print(decodedmsg);
		}
	}
}
