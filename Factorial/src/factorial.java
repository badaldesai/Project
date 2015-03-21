
public class factorial{
	
	int fact(int x){
		int i=x;
		int y=1;
	while(i!=1){
			y=y*i;
			i--;
			//System.out.println(y);
		}
		return y;
	}
}
