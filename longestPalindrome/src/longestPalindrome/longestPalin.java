package longestPalindrome;

public class longestPalin {
	public static void main(String[] args){
		String[] input1 = {"Bha", "Bha", "Akas", "Bha","Cha","Bri","Che","Arv","Bha"};
		int sol = PalindromeLengthPuzzle(input1);
		System.out.println(sol);
	}


    public static int PalindromeLengthPuzzle(String[] input1)
    {
        int len = input1.length;
        int[][] solve = new int[len][len];
        
        for(int i=0;i<len; i++){
        	solve[i][i]=1;
        
        }
        
        for(int i=2; i<=len; i++){
        
        	for(int j=0; j<len-i+1; j++){
        	
        		int temp = j+i-1;
        		//System.out.println(input1[j].charAt(0) + " " +input1[temp].charAt(0));
        		if(input1[j].charAt(0) == input1[temp].charAt(0) && i==2)
        			solve[j][temp]=2;
        		else if(input1[j].charAt(0) == input1[temp].charAt(0))
        			solve[j][temp]=solve[j+1][temp-1] + 2;
        		else
        			solve[j][temp]=Math.max(solve[j][temp-1],solve[j+1][temp]);
        	}
        }
        
        return solve[0][len-1];
    }
}