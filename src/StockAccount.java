import java.sql.*;
import java.util.*;
import java.lang.*;
public class StockAccount{
	public static int currentNumShares;
	public static int taxID;
	public static double boughtAt;
	public static String stockID;
	public static boolean newAcct;

	public static void getTID(int tid){
		taxID = tid; 
	}


	public static void accountInfo(){
		String sql = "select * from stockAccount where taxID = " + taxID+ " and stockID = '" + stockID + "';";
		try{
	      	ResultSet rs = JDBC.statement.executeQuery(sql);
	      	if(rs.next()){
	      		currentNumShares = rs.getInt("numshares");
	      		boughtAt = rs.getDouble("boughtAt");
	      		newAcct = false;
	      	}
	      	else{
	      		currentNumShares = 0;
	      		newAcct = true;
	      	}
	    }catch(SQLException e){e.printStackTrace();}
	}

	public static boolean stockExists(String sid){
		String sql = "select * from Stock where stockID = '" + sid+ "';";
		try{
	      	ResultSet rs = JDBC.statement.executeQuery(sql);
	      	if(rs.next()){
	      		return true;
	      	}
	      	else{
	      		return false;
	      	}
	    }catch(SQLException e){e.printStackTrace();}
	    return false;
	}

	public static void buy(){
		System.out.println("Enter the stock ID you'd like to purchase:");
		String temp = UI.getInput();
		double price;
		int numshares;
		if(!stockExists(temp)){
			System.out.println("Invalid stockID!");
		}
		else{
			stockID = temp;
			price = Stocks.getStockPrice(stockID);
			System.out.println(stockID + " is currently $" + price);
			accountInfo();
			System.out.println("How many would you like to purchase? You currently have " + currentNumShares + " shares.");
			numshares = Integer.parseInt(UI.getInput());
			double cost = numshares * price + 20;
			if(MarketAccount.checkBrokeness(cost)){
				buyHandler(numshares, cost);
			}
			else{
				System.out.println("Insufficient funds.");
			}
		}
	}

	public static void buyHandler(int shares, double totalcost){
		currentNumShares += shares;
		if(newAcct){
			boughtAt = Stocks.getStockPrice(stockID);
			String sql = "insert into stockAccount(taxID,numshares,stockID,boughtAt) values (" + taxID + "," +currentNumShares+  ",'" + stockID + "'," + boughtAt+");";
			try {
		      	JDBC.statement.executeUpdate(sql);
			} catch(SQLException e){e.printStackTrace();}
		}
		else{
			String sql = "update stockAccount set numshares = " + currentNumShares + " where taxID = " + taxID+ " and stockID = '" + stockID + "';";
			try {
		      	JDBC.statement.executeUpdate(sql);
			} catch(SQLException e){e.printStackTrace();}
		}
		MarketAccount.decreaseBalance(totalcost);
		Transaction.buy(totalcost, shares, stockID);
		System.out.println("Success! You pruchased " + shares + " shares of " + stockID + " for $" + totalcost);
	}

	public static void sell(){
		System.out.println("Enter the stock ID you'd like to sell:");
		String temp = UI.getInput();
		double price;
		int numshares;
		if(!stockExists(temp)){
			System.out.println("Invalid stockID!");
		}
		else{
			stockID = temp;
			accountInfo();
			if (newAcct || currentNumShares == 0) {
				System.out.println("Error cannot sell. You don't own any stocks in this account.");
			} else {
				price = Stocks.getStockPrice(stockID);
				System.out.println(stockID + " is currently $" + price);
				System.out.println("How many would you like to sell? You currently have " + currentNumShares + " shares.");
				numshares = Integer.parseInt(UI.getInput());
				if (numshares > currentNumShares) {
					System.out.println("Error cannot sell more shares than you currently own. Try again later.");
				} else {
					double cost = numshares * price;
					sellHandler(numshares, cost);
				}
			}
		}
	}


	public static void sellHandler(int shares, double totalcost){
		currentNumShares -= shares;
		String sql = "update stockAccount set numshares = " + currentNumShares + " where taxID = " + taxID+ " and stockID = '" + stockID + "';";
		try {
		    JDBC.statement.executeUpdate(sql);
		} catch(SQLException e){e.printStackTrace();}
		MarketAccount.incrementBalance(totalcost);
		Transaction.sell(totalcost, shares, stockID);
		System.out.println("Success! You sold " + shares + " shares of " + stockID + " for $" + totalcost);
	}

}
