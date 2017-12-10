import java.sql.*;
import java.util.*;
import java.lang.*;
public class StockAccount{
	public static int currentNumShares;
	public static int taxID;
	//public static HashMap<Double, Integer> boughtAt= new HashMap();
	public static String stockID;
	public static boolean newAcct;
	public static double initBoughtAt;
	public static int initShares;

	public static void getTID(int tid){
		taxID = tid; 
		//boughtAt.clear();
		//getBA();
	}

	/*public static void getBA(){
		String sql = "select * from Transactions where taxID = " + taxID+ " and stockID = '" + stockID + "' and type = 'buy';";
		try{
	      	ResultSet rs = JDBC.statement.executeQuery(sql);
			while(rs.next()){
				if(rs.next){
					rs.previous();
				}
				else{
					break;
				}
		    	double key = rs.getDouble("boughtAt");
		    	int value = rs.getInt("numshares");
		    	if(boughtAt.containsKey(key)){

		    		boughtAt.put(key, boughtAt.get(key) + value);
		    	}
		    	else{
		    		boughtAt.put(key, value);
		    	}	
		    }
	   	}catch(SQLException e){System.out.println("No previous Transactions");}
	}*/

	/*public static void getBoughtAts(){
		//boughtAt = new HashMap();
		String sql = "select * from Transactions where taxID = " + taxID+ " and stockID = '" + stockID + "' and type = 'buy';";
		String sql2 = "select * from StockAccount where taxID = " + taxID+ " and stockID = '" + stockID + ";";
		try{
	      	ResultSet rs = JDBC.statement.executeQuery(sql);
	      	ResultSet rs2 = JDBC.statement.executeQuery(sql2);
	      	if(rs2.next()){
	      		double iba = rs2.getDouble("boughtAt");
	      		int numshares = rs2.getInt("numshares");
	      	}

	      	if(rs.next()){
	      		rs.last();
	      		rs.previous();
	      		//rs.previous();
				while(rs.next()){
		    		double key = rs.getDouble("boughtAt");
		    		int value = rs.getInt("numshares");
		    		if(boughtAt.containsKey(key)){

		    			//int temp = boughtAt.get(key);
		    			//temp+=value;
		    			boughtAt.put(key, boughtAt.get(key) + value);
		    		}
		    		else{
		    			boughtAt.put(key, value);
		    		}	
		    	}
	      	}
	      	else{
	      		boughtAt.put(initBoughtAt, currentNumShares);		
	      	}


	    }catch(SQLException e){e.printStackTrace();} 
	}*/

	public static void accountInfo(){
		String sql = "select * from stockAccount where taxID = " + taxID+ " and stockID = '" + stockID + "';";
		try{
			
	      	ResultSet rs = JDBC.statement.executeQuery(sql);
	      	if(rs.next()){
	      		currentNumShares = rs.getInt("numshares");
	      		initBoughtAt = rs.getDouble("boughtAt");
	      		//boughtAt.put(initBoughtAt, currentNumShares);
	      		newAcct = false;
	      	}
	      	else{
	      		currentNumShares = 0;
	      		newAcct = true;
	      	}
	      	//getBoughtAts();
	    }catch(SQLException e){e.printStackTrace();}
	    //System.out.println(boughtAt);
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
		//boughtAt.put(initBoughtAt, initShares);
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
			double price = Stocks.getStockPrice(stockID);
			//boughtAt.put(price,shares);
			String sql = "insert into stockAccount(taxID,numshares,stockID,boughtAt) values (" + taxID + "," +currentNumShares+  ",'" + stockID + "'," + price+");";
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
		System.out.println("Success! You purchased " + shares + " shares of " + stockID + " for $" + totalcost);
	}

	public static void sell(){
		//boughtAt.put(initBoughtAt, initShares);
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
			System.out.println(currentNumShares);
			if (newAcct || currentNumShares == 0) {
				System.out.println("Error cannot sell. You don't own any stocks in this account.");
			} else {
				price = Stocks.getStockPrice(stockID);
				System.out.println(stockID + " is currently $" + price);
				System.out.println("How many would you like to sell? You currently have " + currentNumShares + " shares.");
				numshares = Integer.parseInt(UI.getInput());
				System.out.println(currentNumShares);
				if (numshares > currentNumShares) {
					System.out.println("Error cannot sell more shares than you currently own. Try again later.");
				} else {
					double cost = numshares * price - 20;
					sellHandler(numshares, cost);
				}
			}
		}
	}


	public static void sellHandler(int shares, double totalcost){
		currentNumShares -= shares;
		/*Set set = boughtAt.entrySet();
		Iterator i = set.iterator();
		int tempShares = shares;
		while(i.hasNext()){
			Map.Entry me = (Map.Entry)i.next();
			double key = (double) me.getKey();
			int value = (int)me.getValue();
			if(value > tempShares){
				tempShares =value - tempShares;
				boughtAt.put(key, tempShares);
			}
			else{
				tempShares -= value; 
				boughtAt.remove(key);
			}
		}*/

		String sql = "update stockAccount set numshares = " + currentNumShares + " where taxID = " + taxID+ " and stockID = '" + stockID + "';";
		try {
		    JDBC.statement.executeUpdate(sql);
		} catch(SQLException e){e.printStackTrace();}
		MarketAccount.incrementBalance(totalcost);
		Transaction.sell(totalcost, shares, stockID);
		System.out.println("Success! You sold " + shares + " shares of " + stockID + " for $" + totalcost);
	}

}
