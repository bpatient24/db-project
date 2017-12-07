import java.util.*;

public class Main {
	static UI ui; 
	static JDBC db;
	static Movies mdb;
    public static void main(String[] args) {
    	db = new JDBC();
    	mdb = new Movies();
    	//System.out.println(args.length);
    	if(args.length == 1){
    		DataParser parser;
    		if(args[0].equals("create")){
    			//System.out.println("test");
    			parser = new DataParser();
    			parser.createDB();
    		}
    		else if(args[0].equals("reset")){
    			parser = new DataParser();
    			parser.resetDB();
    		}
    		else if(args[0].equals("delete")){
    			parser = new DataParser();
    			parser.deleteDB();
    		}
    		else{
    			parser = new DataParser(args[0]);
	    		//System.out.println(parser.numArgs);
	    		for(int i=0; i < parser.queries.size(); i++){
	    			String tmp = parser.queries.get(i);
	    			System.out.println(tmp);
	    		}
	    		//System.out.println(args[0]);
    		}		
    	}
    	else{
	    	ui = new UI();
	    	Date.getDate();
            UI.begin();

	    	db.endSession();
            System.out.println("closed connection");
            System.exit(0);
    	}
    }
}