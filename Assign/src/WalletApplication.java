//package assignment1;

import java.util.Scanner;

/**
 * This class contains the driver of the application and a method to print the menu of the driver.
 * @author Athanasios Babouras
 */
public class WalletApplication {

	/**
	 * This method prints the program's menu.
	 */
	public static void printMenu(){
		System.out.println("What would you like to do?\n"
				         + "1. See the content of all wallets\n"
				         + "2. See the content of one wallet\n"
				         + "3. List wallets with same amount of money\n"
				         + "4. List wallets with same coins\n"
				         + "5. List wallets with same amount of money and same number of credit cards\n"
				         + "6. Add a credit card to an existing wallet\n"
				         + "7. Remove an existing credit card from a wallet\n"
				         + "8. Update the expiry date of an existing credit card\n"
				         + "9. Add coins to a wallet\n"
				         + "0. To quit\n"
				         + "Please enter your choice and press <Enter>: ");
	}
	/**
	 * The driver of the application which creates five wallets using the classes Wallet,Coins and Credit Card, and performs\n
	 * different operations on them depending on the user's choice.
	 * @param args
	 */
	public static void main(String[] args) {
		//Here I create an array of wallets and create each wallet individually.
		Wallet[] wallets = new Wallet[5];
		wallets[0] = new Wallet(new Coins(2,4,5,0,2), new CreditCard[]{new CreditCard("Visa","John Snow",11,2018)});
		wallets[1] = new Wallet(new Coins(2,4,5,0,2), new CreditCard[]{new CreditCard("Master Card","Peter Parker",8,2019)});
		wallets[2] = new Wallet(new Coins(5,5,4,2,1), new CreditCard[]{new CreditCard("Visa","Harry Potter",3,2018),
		                                                               new CreditCard("Master Card","Harry Potter",12,2010),
		                                                               new CreditCard("Discover","Harry Potter",5,2015)});
		wallets[3] = new Wallet(new Coins(1,1,4,3,3), new CreditCard[0]);
		wallets[4] = new Wallet(new Coins(1,1,4,3,3), new CreditCard[0]);
		
		Scanner kb = new Scanner(System.in);
		//These two integers will help with user inputs further on.
		int walletChoice;
		int cardChoice;
		
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n\n"
				         + "       Welcome to Athanasios's Wallet Application\n\n"
				         + "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
		
		printMenu();
		
		int choice = kb.nextInt();
		//I make sure the user enters a valid number.
		while(choice<0 || choice>9){
			System.out.println("Invalid entry. Please enter a number between 0 and 9.\n");
			printMenu();
			choice = kb.nextInt();
		}
		//The main loop of the program.
		while(choice !=0){
			
			//I use a switch since the possible numbers chosen are simple. The cases are
			//the possible choices in the menu.
			switch(choice){
			
				case 1 :
					System.out.println("Content of each wallet :\n"
							         + "----------------------");
					for(int i = 0; i<wallets.length;i++){
						System.out.println("Wallet #" + i + ":\n" + wallets[i]);
					}
					break;
					
				case 2 : 
					System.out.println("Which wallet would you like to see the content of? (Enter number 0 to 4): ");
					int choice2 = kb.nextInt();
					//This while loop is used here and further on to make sure the user doesn't enter an
					//invalid number.
					while(choice2<0||choice2>4){
						System.out.println("Sorry but there is no wallet number " + choice2 +"\n"
								         + "--> Try again: (Enter number 0 to 4): ");
						choice2 = kb.nextInt();
					}
					System.out.println(wallets[choice2]);
					break;
				
				case 3 :
					System.out.println("List of wallets with same amount of money: \n");
					//This nested loop checks every single combination in the wallet.
					for(int i = 0; i<(wallets.length); i++){
						for(int j = (i+1); j<wallets.length; j++){
							if(wallets[i].getTotalCoinValue() == wallets[j].getTotalCoinValue())
								System.out.println("Wallets " + i + " and " + (j) + " both have " + wallets[i].getTotalCoinValue()+"\n");
						}
					}
					break;
				
				case 4 :
					System.out.println("List of wallets with same coins: \n");
					for(int i = 0; i<(wallets.length); i++){
						for(int j = (i+1); j<wallets.length; j++){
							if(wallets[i].coinNumberEquals(wallets[j]))
								//I make use of the show.Coins() method too get the breakdown of the coins in the two wallets.
								System.out.println("Wallets " + i + " and " + (j) + " both have " + wallets[i].showCoins() +"\n");
						}
					}
					break;
					
				case 5 :
					System.out.println("List of wallets with same amount of money and same number of credit cards: \n");
					for(int i = 0; i<(wallets.length); i++){
						for(int j = (i+1); j<wallets.length; j++){
							if(wallets[i].equals(wallets[j])){
								System.out.println("Wallets " + i + " and " + j);
							}
						}
					}
					System.out.println();
					break;
					
				case 6 : 
					System.out.println("Which wallet do you want to add a credit card to? (Enter number 0 to 4): ");
					walletChoice = kb.nextInt();
					while(walletChoice<0||walletChoice>4){
						System.out.println("Invalid entry. Please enter a number between 0 and 4: ");
						walletChoice = kb.nextInt();
					}
					System.out.println("Please enter the following information so that we may complete the transaction: \n"
							         + "--> Type of credit card (Mastercard, Visa, American Express, Discover): \n");
					kb.nextLine();
					String cardType = kb.nextLine();
					//I check if the credit card type is valid.
					if(!cardType.equalsIgnoreCase("visa")&&!cardType.equalsIgnoreCase("Mastercard")&&
					   !cardType.equalsIgnoreCase("American Express")&&!cardType.equalsIgnoreCase("Discover")){
						System.out.println("Invalid card type. Card not added.");
						break;
					}
					
					System.out.println("--> Full name on credit card: \n");
					
					String cardName = kb.nextLine();
					System.out.println("--> Expiry month number and year (seperate by a space): ");
					int expMonth = kb.nextInt();
					int expYear = kb.nextInt();
					//The constructor is called with the user entries as parameters.
					wallets[walletChoice].addCreditCard(new CreditCard(cardType, cardName, expMonth, expYear));
					System.out.println("You now have " + wallets[walletChoice].getNumberOfCreditCards() + " credit cards.");
					break;
					
				case 7 : 
					System.out.println("Which wallet do you want to remove a credit card from? (Enter number 0 to 4): ");
					walletChoice = kb.nextInt();
					while(walletChoice<0||walletChoice>4){
						System.out.println("Invalid entry. Please enter a number between 0 and 4: ");
						walletChoice = kb.nextInt();
					}
					//When deleting, i check if the wallet is empty, to avoid going out of bounds.
					if(wallets[walletChoice].getNumberOfCreditCards() == 0){
						System.out.println("Sorry, that wallet has no cards.\n");
						break;
					}
					System.out.println("Which card? (Enter number 0 to " + (wallets[walletChoice].getNumberOfCreditCards() -1)+"): ");
					cardChoice = kb.nextInt();
					if(cardChoice<0||(cardChoice>wallets[walletChoice].getNumberOfCreditCards()-1)){
						System.out.println("Sorry, there is no card number " + cardChoice+". Card removal cancelled.\n");
						break;
					}
					//If the removal is successful, the confirmation is printed along with the number of remaining cards.
					if(wallets[walletChoice].removeCreditCard(cardChoice)){
						System.out.println("Card was removed successfully.\n");
						System.out.println(wallets[walletChoice].getNumberOfCreditCards());
						break;
					}
					System.out.println("Card was not removed.\n");
					
					break;
					
				case 8 : 
					System.out.println("Which wallet do you want to update a credit card from? (Enter number 0 to 4): ");
					walletChoice = kb.nextInt();
					while(walletChoice<0||walletChoice>4){
						System.out.println("Invalid entry. Please enter a number between 0 and 4: ");
						walletChoice = kb.nextInt();
					}
					if(wallets[walletChoice].getNumberOfCreditCards() == 0){
						System.out.println("Sorry, that wallet has no cards.\n");
						break;
					}
					System.out.println("Which card do you want to update? (Enter number 0 to " + (wallets[walletChoice].getNumberOfCreditCards() -1)+ "): ");
					cardChoice = kb.nextInt();
					while(cardChoice<0||(cardChoice>wallets[walletChoice].getNumberOfCreditCards()-1)){
						System.out.println("Sorry, there is no card number " + cardChoice
								          +".\n-->Try again: (Enter number 0 to " + (wallets[walletChoice].getNumberOfCreditCards() -1)+ "): ");
						cardChoice = kb.nextInt();
					}
					System.out.println("-->Enter new expiry month number and year (seperate by a space): ");
					int expMonth2 = kb.nextInt();
					int expYear2 = kb.nextInt();
					
					wallets[walletChoice].updateExpiryDate(cardChoice, expMonth2, expYear2);
					System.out.println("Expiry date updated.\n");
					break;
					
				case 9 : 
					System.out.println("Which wallet do you want to add coins to? (Enter number 0 to 4): ");
					walletChoice = kb.nextInt();
					while(walletChoice<0||walletChoice>4){
						System.out.println("Invalid entry. Please enter a number between 0 and 4: ");
						walletChoice = kb.nextInt();
					}
					System.out.println("How many nickels, dimes, quarters, loonies and toonies do you want to add?\n"
							         + "(Enter 5 numbers separated by a space): ");
					int nickels = kb.nextInt();
					int dimes = kb.nextInt();
					int quarters = kb.nextInt();
					int loonies = kb.nextInt();
					int toonies = kb.nextInt();
					wallets[walletChoice].addCoins(nickels, dimes, quarters, loonies, toonies);
					System.out.println("You now have $" + wallets[walletChoice].getTotalCoinValue() + "\n");
					break;
				}
			
			//This line prints the menu every time the loop runs.
			printMenu();
			choice = kb.nextInt();
			
		}
		//The closing message.
		System.out.println("Thank you for using Athanasios's wallet application.");
		kb.close();
	}

	
}
