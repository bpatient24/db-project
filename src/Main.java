import java.util.*;

public class Main {
	static UI ui; 
	static JDBC db;
    public static void main(String[] args) {
    	db = new JDBC();
    	ui = new UI();
    	ui.begin();

    	db.endSession();
    }
}