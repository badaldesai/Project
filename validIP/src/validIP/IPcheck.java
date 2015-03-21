package validIP;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.*;
import org.apache.commons.validator.routines.InetAddressValidator;

public class IPcheck {
	private static BufferedReader br;
	public static void main(String[] args) throws Exception{
		br = new BufferedReader (new FileReader("/Users/mac/Desktop/PracticeInput.txt"));
		IPcheck ip =new IPcheck();
		String line;
		while((line=br.readLine())!=null){
			String[] input = line.trim().split("\n");
			for(int i=0;i<input.length;i++){
				System.out.println(input[i]);
				ip.checker(input[i]);
			}	
		}
	}
	public void checker(String ip){
		String[] ips = ip.split("\\s+");
			if(InetAddressValidator.getInstance().isValidInet4Address(ips[2])){
				try{
					InetAddress ip3 = InetAddress.getByName(ips[2]);
					InetAddress ipstart = InetAddress.getByName(ips[0]);
					InetAddress ipend = InetAddress.getByName(ips[1]);
					if(ip3.hashCode()>=ipstart.hashCode() && ip3.hashCode()<=ipend.hashCode()){
						System.out.println("InRange");
					}else System.out.println("OutofRange");
			
				}catch (Exception ex){
					System.err.println("Invalid IP Address");
				}
			}else System.out.println("Invalid IP Address");
	}
}
