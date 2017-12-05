import java.util.*;
import java.lang.*;

public class UI{
	static Scanner scanner;
 	static final ArrayList<String> validTraderInputs = new ArrayList<>(Arrays.asList("login", "register", "back", "help"));
 	static final ArrayList<String> validUsers = new ArrayList<>(Arrays.asList("trader", "operator", "manager", "quit", "help"));
    static final ArrayList<String> validManagerInputs = new ArrayList<>(Arrays.asList("add interest", "generate monthly statement", "list active customers", "generate DTER", "customer report", "delete transaction", "back", "help"));
    static final ArrayList<String> validOperatorInputs = new ArrayList<>(Arrays.asList("open market", "close market", "set stock price", "set date", "back", "help"));
    
 	public UI(){
 		 scanner = new Scanner(System.in);
 	}

    public void help(ArrayList<String> inputSet){
        System.out.println("Input options: " + Arrays.toString(inputSet.toArray()));
    }

 	public void begin(){
 		System.out.println("Welcome to Stars R Us");
 		System.out.println("Are you a trader, manager or operator?");
    	String input ="";
    	while(!input.equals("quit")){
    		input = getInput();
    		if(validUsers.contains(input)){
    			switch(input){
    				case "trader":
    				traderInterface();
    				break;

    				case "operator":
    				operatorInterface();
    				break;

    				case "manager":
    				managerInterface();
    				break;

    				case "quit":
                    System.exit(0);
    				break;

    				case "help":
    				help(validUsers);
                    break;
    			}
    		}
    		else{
    			System.out.println("Invalid input, type 'help' for input options");
    		}	
    	}
 	}

 	public void traderInterface(){
 		System.out.println("Welcome to Stars R Us Trader Interface");
 		System.out.println("Would you like to login or create an account? (Type login or register)");
 		String input = "";
 		while(true){
    		input = getInput();
    		if(validTraderInputs.contains(input)){
    			switch(input){
    				case "login":
    				//login user
    				break;

    				case "register":
    				//register user
    				break;

    				case "back":
                    begin();
    				break;

    				case "help":
    				help(validTraderInputs);
                    break;
    			}
    		}
            else{
                System.out.println("Invalid input, type 'help' for input options");
            }
    	}
 	}

    public void managerInterface(){
        System.out.println("Welcome to Stars R Us Manager Interface");
        System.out.println("What would you like to do? Type 'help' for options");
        String input = "";
        while(true){
            input = getInput();
            if(validManagerInputs.contains(input)){
                switch(input){
                    case "add interest":
                    //add interest
                    break;

                    case "generate monthly statement":
                    //do this
                    break;

                    case "list active customers":
                    //do this
                    break;

                    case "generate DTER":
                    //do this
                    break;

                    case "customer report":
                    //do this
                    break;

                    case "delete transaction":
                    //do this
                    break;

                    case "back":
                    begin();
                    break;

                    case "help":
                    help(validManagerInputs);
                    break;
                }
            }
            else{
                System.out.println("Invalid input, type 'help' for input options");
            }
        }
    }

    public void operatorInterface(){
        System.out.println("Welcome to Stars R Us Operator Interface");
        System.out.println("What would you like to do? Type 'help' for options");
        String input = "";
        while(true){
            input = getInput();
            if(validOperatorInputs.contains(input)){
                switch(input){
                    case "open market":
                    Operator.openMarket();
                    break;

                    case "close market":
                    //do this
                    break;

                    case "set stock price":
                    //do this
                    break;

                    case "set date":
                    //do this
                    break;

                    case "back":
                    begin();
                    break;

                    case "help":
                    help(validOperatorInputs);
                    break;
                }
            }
            else{
                System.out.println("Invalid input, type 'help' for input options");
            }
        }
    }

 	private static String getInput() {
        System.out.print(">>>");
		return(scanner.nextLine());
  	}
 }