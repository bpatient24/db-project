import java.util.*;
import java.lang.*;
import java.sql.*;
public class Operator {
	public static boolean open;
	public static void openMarket(){
		Date.nextDay();
		open = true;
	}

	public static void closeMarket(){
		open = false;
	}

	public static void setStockPrice(){
		System.out.println("Enter the stockID:");
		String stockID = UI.getInput();
		System.out.println("What is the new price for " + stockID);
		double newPrice = Double.parseDouble(UI.getInput());
		Stocks.setStockPrice(stockID, newPrice);
	}

	public static void setDate(){
		String newDate;
		System.out.println("What is the date today? (MM/DD/YYYY)");
		newDate = UI.getInput();
		Date.setDate(newDate);
		System.out.println("Date set to:" + Date.date);
	}
}