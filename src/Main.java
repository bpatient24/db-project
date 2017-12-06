import java.util.*;

public class Main {
	static UI ui; 
	static JDBC db;
    public static void main(String[] args) {
    	if(args.length == 1){
    		DataParser parser;
    		parser = new DataParser(args[0]);
    		//System.out.println(parser.numArgs);
    		for(int i=0; i < parser.queries.size(); i++){
    			String tmp = parser.queries.get(i);
    			System.out.println(tmp);
    		}
    	}
    	else{
	    	db = new JDBC();
	    	ui = new UI();
	    	ui.begin();

	    	db.endSession();
    	}
    }
}