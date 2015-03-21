
public class SimpleDotComTestDrive {

	public static void main(String[] args) {
		
		int numOfGuesses = 0;
		GameHelper help = new GameHelper();
		SimpleDotCom dot = new SimpleDotCom();
		int randomNum = (int) (Math.random() * 5);
		int [] locations = {randomNum,randomNum+1,randomNum+2};
		dot.setLocationCells(locations);
		boolean isAlive = true;
		
		while(isAlive == true){
			String guess = help.getUserInput("enter a number");
			String result = dot.checkYourself(guess);
			numOfGuesses++;

			if(result == "Kill"){
				isAlive = false;
				System.out.println("You took " + numOfGuesses + " guesses");
			}
		}
	}

}
