
public class Palin {

	int checker(String h) {
		int x=h.length();
		System.out.println(x);
		int mid = 0;
		if(x%2==0){
			mid = x/2;
		}else{
			mid = (x+1)/2;
		}
		
		int i = 0;
		while(i!=mid){
			
			if(h.charAt(i)==' '){
				i++;
			}
			else if(h.charAt(x-1)==' '){
				x--;
			}
			else if(h.charAt(i)==h.charAt(x-1)){
				i++;
				x--;
			}
			else{return 0;}
		}
		return 1;
	}
}
