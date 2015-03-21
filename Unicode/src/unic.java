import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.*;

public class unic {

	public static void main(String[] args) throws Exception {
		FileReader fr = new FileReader("/Users/mac/Desktop/PracticeInput.txt");
		BufferedReader br = new BufferedReader (fr);
		unic u =new unic();
		String line;
		while((line=br.readLine())!=null){
			String[] input = line.trim().split("\n");
			for(int i=0;i<input.length;i++){
				//System.out.println(input[i]);
				u.checker(input[i]);
			}	
		}
	}
	public void checker(String s){
		String[] u = s.split("\\@");
		String[] u2 = u[1].split("\\.");
		check(u[0]);
		check(u2[0]);
		check(u2[1]);
	}
	public void check(String u){
		for(int i=0;i<u.length();i++){
			if(u.charAt(i)>32 && u.charAt(i)<127){
				System.out.print(u.toLowerCase().charAt(i));
			}
			else {
				Charset cs = Charset.forName("UTF-8");                              // BBB
				ByteBuffer cb = cs.decode(u); 
				byte[] output = cb.array();
				
			}
		}
	}

}
