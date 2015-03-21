//package assignment1;
/**
 * This class contains an object with 4 variables; cardType, holderName, expiryMonth and expiryYear
 * <p>There are accessor and mutator methods for all variables and also, a toString() and an equals() method.
 * @author Athanasios Babouras
 *
 */
public class CreditCard {

	
	private String cardType,holderName;
	private int expiryMonth,expiryYear;
	/**
	 * The default constructor.
	 */
	public CreditCard(){
		cardType = "";
		holderName = "";
		expiryMonth = 0;
		expiryYear = 0;
	}
	/**
	 * A constructor with the 4 variables of the class as parameters.
	 * @param type
	 * @param name
	 * @param month
	 * @param year
	 */
	public CreditCard(String type,String name,int month,int year){
		cardType = type;
		holderName = name;
		if(month>=1&&month<=12)
			expiryMonth = month;
		else
			expiryMonth = 0;
		expiryYear = year;
		
	}
	/**
	 * The copy constructor.
	 * @param someCreditCard
	 */
	public CreditCard(CreditCard someCreditCard){
		cardType = someCreditCard.cardType;
		holderName = someCreditCard.holderName;
		expiryMonth = someCreditCard.expiryMonth;
		expiryYear = someCreditCard.expiryYear;
	}
	/**
	 * @return The card type of the object.
	 */
	public String getCardType(){
		return cardType;
	}
	/** 
	 * @return The holder name of the object.
	 */
	public String getHolderName(){
		return holderName;
	}
	/** 
	 * @return The expiry month of the object.
	 */
	public int getExpiryMonth(){
		return expiryMonth;
	}
	/** 
	 * @return The expiry year of the object.
	 */
	public int getExpiryYear(){
		return expiryYear;
	}
	/**
	 * This method sets the expiry month of the object, setting it to zero if the entry is invalid.
	 * @param month
	 */
	public void setExpiryMonth(int month){
		if(month>=1&&month<=12)
			expiryMonth = month;
		else
			expiryMonth = 0;
	}
	/**
	 * This method sets the expiry year of the object.
	 * @param year
	 */
	public void setExpiryYear(int year){
		expiryYear = year;
	}
	/**
	 * @return A string with the expiry month and date of the object, showing the expiry month with two digits.
	 */
	public String toString(){
		if(this.expiryMonth>=10)
			return cardType+" "+holderName+" "+expiryMonth+"/"+expiryYear;
		else
			return cardType+" "+holderName+" 0"+expiryMonth+"/"+expiryYear;
	}
	/**
	 * @param someCreditCard
	 * @return A boolean which is true if the two objects are equal.
	 */
	public boolean equals(CreditCard someCreditCard){
		return(cardType.equals(someCreditCard.cardType)&&holderName.equals(someCreditCard.holderName)&&
			   expiryMonth==someCreditCard.expiryMonth&&expiryYear==someCreditCard.expiryYear);
	}
	
}
