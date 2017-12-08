import java.sql.*;
import java.util.*;
import java.lang.*;

public class Stocks {
	public static double openPrice;
	public static double closingPrice;
	public static double currentPrice;

	public static void setStockPrice(String stockID, double newPrice){
		String sql = "update Stock set currentprice = " +newPrice+ " WHERE stockID= '" +stockID+ "';";
		try {
	      	JDBC.statement.executeUpdate(sql);
	      	System.out.println("Success! Set " + stockID + "'s price to " + newPrice);
	    } catch(SQLException e){e.printStackTrace();}
	}
}