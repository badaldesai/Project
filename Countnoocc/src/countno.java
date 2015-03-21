import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class countno {
	
	//To calculate the occurences of the given words
	static int nt=0;
	static int n=0;
	static int mt=0;
	static int m=0;
	
	static String word1=null;
	static String word2=null;
	static String word3=null;

	public static void main(String[] fname) {
		if(fname.length >0){
			try{
				countno.findocc(new FileInputStream(fname[0])); //to Read binary files
				System.out.println("Number of case sensitive direct matches of Nexsan :" + n);
				System.out.println("Number of case sensitive direct matches of Nexsan Technologies :"+ nt);
				System.out.println("Number of case insensitive of Nexsan :" + m);
				System.out.println("Number of case insensitive of Nexsan Technologies :"+ mt);
			}catch(FileNotFoundException e){
				System.err.println(e);
			}
		}else{
			System.out.println("No file found on command line");
		}

	}
	
	//This function reads the files and stores the string in the string buffer 
	private static void findocc(InputStream fnam){
		try{
			int i;
			char ch;
			
			BufferedInputStream bs = new BufferedInputStream(fnam);
			StringBuffer sb = new StringBuffer();
			//Read a byte 
			while((i=bs.read())!=-1){
				//Cast to character
				ch = (char) i;
				if(isValidch(ch) || (sb.length()>0)){
					//if valid character build the required string
					sb.append(ch);
				}
				else{
					if(sb.length()==0)
						continue;
					
						report(sb);
						
					sb.setLength(0);
				}
			}
			bs.close();
		}catch(IOException e){
			System.out.println("IOException :" + e);
		}
		
	}
	//Checks whether the character is valid ASCII character
	private static boolean isValidch(char ch){
		if (ch >= 'a' && ch <='z')
			return true;
		if (ch >='A' && ch <='Z')
			return true;
		if (ch >='0' && ch <='9')
			return true;
		
		return false;
	}
	//Check the required the condition and increment the value accordingly
	private static void report(StringBuffer Stri){
		
		String str1 = "Technologies";
		String str2 = "Nexsan";
		//System.out.println(Stri);
		if(!Stri.toString().contains(str1)){
			word1=Stri.toString().toString();
			if(word1.contains(str2)){
				n++;
				//System.out.println(word1);
			}
		}
		else if(word1.contains(str2)){
			
				nt++;
				n--;
				//System.out.println(word1 + " "+ Stri);
			}
		 
		
		else{
			word2=Stri.toString().toLowerCase();
			//System.out.println(word2);
			if(!word2.contains("technologies")){
				word3=word2;
				if(word3.contains("nexsan")){
					m++;
				//System.out.println(word3);
				}
			}
			else{
				if(word3.contains("nexsan")){
					mt++;
					m--;
					//System.out.println(word3 + " "+ word2);
				}
			}
		}
	}
		
}

