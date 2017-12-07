import java.sql.*;
import java.util.*;
import java.lang.*;

public class Transaction {

	public static void deposit(int tID) {
		//add money to market account balance
		System.out.println("How much money would you like to deposit?");
		double money = Double.parseDouble(UI.getInput());
		while (money <= 0.0) {
			System.out.println("Invalid Deposit. Enter a dollar amount greater than 0.");
			money = Double.parseDouble(UI.getInput());
		}

		String sql = "update marketAccount set balance = balance + " +money+ " WHERE taxID=" +tID+ ";";
		try {
	      	JDBC.statement.executeUpdate(sql);
	      	System.out.println("Success! Deposited $" + money + " into your account.");
	    } catch(SQLException e){e.printStackTrace();}
	}

	public static void withdraw(int tID){
		//subtract money from market account balance
		//check current balance so they don't overdraw
		String sql = "select balance from marketAccount where taxID=" +tID+ ";";
		double currBalance = 0.0;
		try {
	      	ResultSet resultSet = JDBC.statement.executeQuery(sql);
			if (resultSet.next()) { //should be 1 result from the query
				currBalance = resultSet.getDouble("balance");
			}
	    } catch(SQLException e){System.out.println("Error: Could not obtain balance for trader withdrawl.*******");}

		System.out.println("How much money would you like to withdraw?");
		double money = Double.parseDouble(UI.getInput());
		while ((money <= 0.0) || (money > currBalance)) {
			System.out.println("Invalid Withdrawl. Enter a dollar amount greater than 0 and less than your balance ($" +currBalance+ ").");
			money = Double.parseDouble(UI.getInput());
		}

		String sql2 = "update marketAccount set balance = balance - " +money+ " WHERE taxID=" +tID+ ";";
		try {
	      	JDBC.statement.executeUpdate(sql2);
	      	currBalance = currBalance - money;
	      	System.out.println("Success! Withdrew $" + money + " from your account. Your balance is now $" + currBalance + ".");
	    } catch(SQLException e){e.printStackTrace();}
		
	}

	public static void buy(){
		// Acquire a specified number of shares of a specified stock at the current price. Each buy transaction costs
		//$20 (commission). The customer must pay the total price for the shares and the commission with the
		//money in the market account. (The transaction fails if there is not enough money in the market account.)
	}
	
	public static void sell(String share, int numShares){

	}

	public static void accrueInterest(String marketAct){
		
	}
}