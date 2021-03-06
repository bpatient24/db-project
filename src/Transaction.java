import java.sql.*;
import java.util.*;
import java.lang.*;

public class Transaction {
	public static int taxID;

	public static void getTID(int tid){
		taxID = tid;
	}

	public static void deposit(double amount) {
		String type = "deposit";
		String date = Date.date;
		//create transaction table entry for this deposit
		String sql = "insert into Transactions(taxID,amount,newbalance,type,date) values (" +taxID+ ", " +amount+ ", " + MarketAccount.currBalance + ", '" +type+ "', '" +date+ "');";
		try {
	      	JDBC.statement.executeUpdate(sql);
		} catch(SQLException e){e.printStackTrace();}
	}

	public static void withdraw(double amount){
		String type = "withdrawal";
		String date = Date.date;
		//create transaction table for this deposit
		String sql = "insert into Transactions(taxID,amount,newbalance,type,date) values (" +taxID+ ", " +amount+ ", " + MarketAccount.currBalance + ", '" +type+ "', '" +date+ "');";
		try {
	      	JDBC.statement.executeUpdate(sql);
		} catch(SQLException e){e.printStackTrace();}
	}

	public static void printTransactions(){
		System.out.println("Here is your transaction history:");
		String sql = "select * from Transactions where taxID = " + taxID + ";";
		try {
	      	ResultSet rs = JDBC.statement.executeQuery(sql);
	      	while(rs.next()){
	      		int tid = rs.getInt("tid");
	      		String type = rs.getString("type");
	      		double amount = rs.getDouble("amount");
	      		String date = rs.getString("date");
	      		if(type.equals("withdrawal") || type.equals("deposit") || type.equals("accrue interest")){
	      			System.out.println("tid:" + tid + " type=" + type + " amount:" + amount + " date:" + date + " taxID:" + taxID);
	      		}
	      		else{
	      		String stockID = rs.getString("stockID");
	      		int numShares = rs.getInt("numShares");
	      		//double boughtAt = rs.getDouble("boughtAt");
	      		System.out.println("tid:" + tid + " type=" + type + " amount:" + amount + " date:" + date + " taxID:" + taxID + " stockID:" + stockID + " #Shares:" + numShares);
	      		}
	      	}
		} catch(SQLException e){e.printStackTrace();}
	}

	public static void buy(double amount, int shares, String sid){
		String type = "buy";
		String date = Date.date;
		double price = Stocks.getStockPrice(sid);
		String sql = "insert into Transactions(taxID,amount,numshares,newbalance,stockID,type,date,boughtAt) values (" +taxID+ ", " +amount+ ", " +shares+ ", " + MarketAccount.currBalance + ", '" + sid + "', '" +type+ "', '" +date+ "', " +price + ");";
		try {
	      	JDBC.statement.executeUpdate(sql);
		} catch(SQLException e){e.printStackTrace();}
	}
	
	public static void sell(double amount, int shares, String sid){
		String type = "sell";
		String date = Date.date;
		String sql = "insert into Transactions(taxID,amount,numshares,newbalance,stockID,type,date) values (" +taxID+ ", " +amount+ ", " +shares+ ", " + MarketAccount.currBalance + ", '" + sid + "', '" +type+ "', '" +date+ "');";
		try {
	      	JDBC.statement.executeUpdate(sql);
		} catch(SQLException e){e.printStackTrace();}
	}

	public static void accrueInterest(double amount, int tid, double newbalance){
		String type = "interest";
		String date = Date.date;
		String sql = "insert into Transactions(taxID,amount,newbalance,type,date) values (" +tid+ ", " +amount+ ", " + newbalance + ", '"  +type+ "', '" +date+ "');";
		try {
			Statement statement4 = JDBC.connection.createStatement();
	      	statement4.executeUpdate(sql);
	      	if (statement4 != null) {
				statement4.close();
			}
		} catch(SQLException e){e.printStackTrace();}
	}
}