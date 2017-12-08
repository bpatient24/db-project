import java.util.*;
import java.lang.*;
import java.sql.*;

public class UI{
	static Scanner scanner;
 	static final ArrayList<String> validLoginInputs = new ArrayList<>(Arrays.asList("login", "register", "back", "help"));
 	static final ArrayList<String> validUsers = new ArrayList<>(Arrays.asList("trader", "operator", "manager", "quit", "help"));
    static final ArrayList<String> validManagerInputs = new ArrayList<>(Arrays.asList("add interest", "generate monthly statement", "list active customers", "generate DTER", "customer report", "delete transactions", "back", "help"));
    static final ArrayList<String> validOperatorInputs = new ArrayList<>(Arrays.asList("open market", "close market", "set stock price", "set date", "back", "help"));
    
 	public UI(){
 		 scanner = new Scanner(System.in);
 	}

    public static void help(ArrayList<String> inputSet){
        System.out.println("Input options: " + Arrays.toString(inputSet.toArray()));
    }

 	public static void begin(){
 		System.out.println("Welcome to Stars R Us");
        Date.printDate();
    	String input ="";
    	while(!input.equals("quit")){
            System.out.println("Are you a trader, manager or operator?");
    		input = getInput();
    		if(validUsers.contains(input)){
    			switch(input){
    				case "trader":
    				accountCheck();
    				break;

    				case "operator":
    				operatorInterface();
    				break;

    				case "manager":
    				if(Manager.login()){
    					managerInterface(Manager.name);
    				}
    				else{
    					System.out.println("Login failed");
    					begin();
    				}
    				break; 

    				case "quit":
    				//System.exit(0);
    				System.out.println("*quit*");
    				input = "quit";
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

 	public static void accountCheck(){
 		System.out.println("Welcome to Stars R Us");
 		System.out.println("Would you like to login or create an account? (Type login or register)");
 		String input = "";
 		while(!input.equals("back")){
    		input = getInput();
    		if(validLoginInputs.contains(input)){
    			switch(input){
    				case "login":
    				Trader.login();
                    input = "back";
    				break;

    				case "register":
    				Trader.openAccount();
                    input = "back";
    				break;

    				case "back":
                    
    				break;

    				case "help":
    				help(validLoginInputs);
                    break;
    			}
    		}
            else{
                System.out.println("Invalid input, type 'help' for input options");
            }
    	}
 	}

    public static void managerInterface(String name){
        System.out.println("Hello " + name +".");
        System.out.println("Welcome to Stars R Us Manager Interface");
        String input = "";
        while(!input.equals("back")){
            System.out.println("\nWhat would you like to do? Type 'help' for options");
            input = getInput();
            if(validManagerInputs.contains(input)){
                switch(input){
                    case "add interest":
                    //add interest
                    break;

                    case "generate monthly statement":
                    Manager.generateStatement();
                    break;

                    case "list active customers":
                    Manager.listActiveCustomers();
                    break;

                    case "generate DTER":
                    //do this
                    break;

                    case "customer report":
                    Manager.showCustomerReport();
                    break;

                    case "delete transactions":
                    Manager.deleteTransactions();
                    break;

                    case "back":
                    
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

    public static void operatorInterface(){
        System.out.println("Welcome to Stars R Us Operator Interface");
        String input = "";
        while(!input.equals("back")){
            System.out.println("\nWhat would you like to do? Type 'help' for options");
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
                    Operator.setStockPrice();
                    break;

                    case "set date":
                    Operator.setDate();
                    break;

                    case "back":
                    
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

 	public static String getInput() {
        System.out.print(">>>");
		return(scanner.nextLine());
  	}
 }