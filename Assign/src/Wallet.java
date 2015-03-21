//package assignment1;
/**
 * This class contains an object with two variables; an object of type Coins and an array of objects of type CreditCard.
 * <p> There are several methods which use methods of the classes Coins and CreditCard to do different operations to the object.
 * @author Athanasios Babouras
 *
 */
public class Wallet {
	
	private Coins coins;
	private CreditCard[] creditCards;
	/**
	 * The default constructor.
	 */
	public Wallet(){
		coins = new Coins();
		creditCards = new CreditCard[0];
	}
	/**
	 * A constructor with two parameters, an object of type Coins and an array of type CreditCard.
	 * @param someCoins
	 * @param creditArray
	 */
	public Wallet(Coins someCoins, CreditCard[] creditArray){
		coins = new Coins(someCoins);
		creditCards = new CreditCard[creditArray.length];
		if(creditArray.length>0){
			for(int i=0; i<creditCards.length; i++){
				creditCards[i] = new CreditCard(creditArray[i]);
			}
		}
	}
	/**
	 * This method checks if the values of the coins in two wallets are equal.
	 * @param someWallet
	 * @return True if the total value of the coins of the two wallets are equal and false otherwise.
	 */
	public boolean coinValueEquals(Wallet someWallet){
		return(coins.coinsTotal() == someWallet.coins.coinsTotal());
	}
	/**
	 * 
	 * @param someWallet
	 * @return True if the number of coins in both wallets are equal and false otherwise.
	 */
	public boolean coinNumberEquals(Wallet someWallet){
		return(coins.equals(someWallet.coins));
	}
	/**
	 * This method calculates the total value of the coins in a wallet, using a method from the Coin class.
	 * @return The total value of the coins in the wallet.
	 */
	public double getTotalCoinValue(){
		return (coins.coinsTotal());
	}
	/**
	 * @return The number of credit cards in a wallet.
	 */
	public int getNumberOfCreditCards(){
		return creditCards.length;
	}
	/**
	 * This method adds a credit card to a wallet.
	 * @param someCreditCard
	 * @return The new length of the array of credit cards in the wallet, after the new credit card has been added.
	 */
	public int addCreditCard(CreditCard someCreditCard){
		CreditCard[] temp = new CreditCard[creditCards.length +1];
		for(int i = 0; i < creditCards.length; i++){
			temp[i] = creditCards[i];
		}
		temp[temp.length-1] = new CreditCard(someCreditCard);
		creditCards = temp;
		return creditCards.length;
	}
	/**
	 * This method removes a chosen credit card from a wallet.
	 * @param cardIndex
	 * @return True if the card has been removed successfully and false if it hasn't.
	 */
	public boolean removeCreditCard(int cardIndex){
		if(creditCards.length == 0)
			return false;
		else{
			CreditCard[] temp = new CreditCard[creditCards.length -1];
			for(int i = 0; i < (creditCards.length); i++){
				if(i == cardIndex){
					continue;
				}
				if(i == creditCards.length-1)
					temp[i-1] = creditCards[i];
				else
					temp[i] = creditCards[i];
				
			}
			creditCards = temp;
			return true;
		}
			
	}
	/**
	 * This method changes the expiry month and year of the chosen card in a wallet.
	 * @param cardIndex
	 * @param month
	 * @param year
	 */
	public void updateExpiryDate(int cardIndex, int month, int year){
		if(cardIndex>=0&&cardIndex<=creditCards.length){
			creditCards[cardIndex].setExpiryMonth(month);
			creditCards[cardIndex].setExpiryYear(year);
		}
	}
	/**
	 * This method adds coins to a wallet and returns the total value of the coins after the addition.
	 * @param nickels
	 * @param dimes
	 * @param quarters
	 * @param loonies
	 * @param toonies
	 * @return The total value of the coins after the addition.
	 */
	public double addCoins(int nickels, int dimes, int quarters, int loonies, int toonies){
		coins.setNickels(coins.getNickels() + nickels);
		coins.setDimes(coins.getDimes() + dimes);
		coins.setQuarters(coins.getQuarters() + quarters);
		coins.setLoonies(coins.getLoonies() + loonies);
		coins.setToonies(coins.getToonies() + toonies);
		
		return coins.coinsTotal();
	}
	/**
	 * This method checks if two objects of type Wallet have the same value of coins and the same number of credit cards.
	 * @param someWallet
	 * @return True if the two wallets have the same coin value and the same amount of credit cards
	 */
	public boolean equals(Wallet someWallet){
		if(coins.coinsTotal()!= someWallet.coins.coinsTotal() || creditCards.length != someWallet.creditCards.length){
			return false;
		}
		return true;
	}
	/**
	 * This method returns a string which indicates the number of each coin and the details of
	 * each credit card in the wallet.
	 * @return String
	 */
	public String toString(){
		if(creditCards.length == 0)
			return coins.toString() + "\nNo credit cards.\n";
		//The string array is used to generate a single string with all the credit card names.
		String[] cardString = new String[creditCards.length];
		cardString[0] = creditCards[0].toString() + "\n";
		if(creditCards.length >1){
			for(int i = 1; i<creditCards.length; i++){
				cardString[i] = cardString[i-1] + creditCards[i].toString() + "\n";
			}
		}
		return coins.toString() + "\n" + cardString[cardString.length -1];
	}
	/**
	 * This method returns a breakdown of the coins in a wallet.
	 * @return String
	 */
	public String showCoins(){
		return coins.toString();
	}
	
}
