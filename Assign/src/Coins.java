//package assignment1;
/**
 * This class contains an object with 5 coin types; nickels, dimes, quarters, loonies and toonies.
 * <p>There are accessor and mutator methods for each type, a toString() and an equals() method and
 * two methods to get the total of all coins and to add more coins to the object.
 * @author Athanasios Babouras
 *
 */
public class Coins {
	
	private int nickels,dimes,quarters,loonies,toonies;
	private static double nickelValue = 0.05,dimeValue = 0.10,quarterValue = 0.25;
	private static int loonieValue = 1,toonieValue = 2;
	/**
	 * The default constructor.
	 */
	public Coins(){
		nickels = 0;
		dimes = 0;
		quarters = 0;
		loonies = 0;
		toonies = 0;
	}
	/**
	 * Constructor with every type of coin as a parameter.
	 * @param nickels
	 * @param dimes
	 * @param quarters
	 * @param loonies
	 * @param toonies
	 */
	public Coins(int nickels, int dimes,int quarters,int loonies,int toonies){
		this.nickels = nickels;
		this.dimes = dimes;
		this.quarters = quarters;
		this.loonies = loonies;
		this.toonies = toonies;
	}
	/**
	 * The copy constructor.
	 * @param someCoin
	 */
	public Coins(Coins someCoin){
		if(someCoin == null){
			System.out.println("Error. Coin not copied.");
		}
		else{
			nickels = someCoin.nickels;
			dimes = someCoin.dimes;
			quarters = someCoin.quarters;
			loonies = someCoin.loonies;
			toonies = someCoin.toonies;
		}
	}
	/**
	 * @return The amount of nickels.
	 */
	public int getNickels(){
		return nickels;
	}
	/**
	 * Sets the amount of nickels of the object.
	 * @param nickels
	 */
	public void setNickels(int nickels){
		this.nickels = nickels;
	}
	/**
	 * @return The amount of dimes.
	 */
	public int getDimes(){
		return dimes;
	}
	/**
	 * Sets the amount of dimes of the object.
	 * @param dimes
	 */
	public void setDimes(int dimes){
		this.dimes = dimes;
	}
	/**
	 * @return The amount of quarters.
	 */
	public int getQuarters(){
		return quarters;
	}
	/**
	 * Sets the amount of quarters of the object.
	 * @param quarters
	 */
	public void setQuarters(int quarters){
		this.quarters = quarters;
	}
	/**
	 * 
	 * @return The amount of loonies.
	 */
	public int getLoonies(){
		return loonies;
	}
	/**
	 * Sets the amount of loonies of the object.
	 * @param loonies
	 */
	public void setLoonies(int loonies){
		this.loonies = loonies;
	}
	/**
	 * @return The amount of toonies.
	 */
	public int getToonies(){
		return toonies;
	}
	/**
	 * Sets the amount of toonies of the object.
	 * @param toonies
	 */
	public void setToonies(int toonies){
		this.toonies = toonies;
	}
	/**
	 * Adds a given amount of coins to an object of the class.
	 * @param nickels
	 * @param dimes
	 * @param quarters
	 * @param loonies
	 * @param toonies
	 */
	public void addCoins(int nickels, int dimes, int quarters,int loonies,int toonies){
		this.nickels+= nickels;
		this.dimes+= dimes;
		this.quarters+= quarters;
		this.loonies+= loonies;
		this.toonies+= toonies;
	}
	/**
	 * This method uses the static variables of the class to create a String.
	 * @return A string with the breakdown of the coins 
	 */
	public String toString(){
		return nickels+" x "+(int)(nickelValue*100)+"� "+"+ "+dimes+" x "+(int)(dimeValue*100)+"� "+"+ "+quarters+" x "
	           +(int)(quarterValue*100)+"� "+"+ "+loonies+" x $"+(int)loonieValue+" + "+toonies+" x $"+(int)toonieValue;
	}
	/**
	 * This method uses the static variables of the class to calculate the total value of the coins of the object.
	 * @return A double with the total value
	 */
	public double coinsTotal(){
		return (nickels*nickelValue)+(dimes*dimeValue)+(quarters*quarterValue)+(loonies*loonieValue)+(toonies*toonieValue);
	}
	/**
	 * This method determines whether two objects of the class are equal.
	 * @param someCoins
	 * @return A boolean which is true if two objects have the same breakdown of coins.
	 */
	public boolean equals(Coins someCoins){
		return(nickels==someCoins.nickels&&dimes==someCoins.dimes&&quarters==someCoins.quarters&&
			   loonies==someCoins.loonies&&toonies==someCoins.toonies);
	}
	
}
