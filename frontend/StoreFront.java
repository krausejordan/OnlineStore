package frontend;

import java.util.Scanner;

import backend.ServerThread;
import backend.InventoryManager;
import backend.Salable;
import backend.ShoppingCart;
import exception.ConnectionException;
import exception.ProductException;

/**
 * This class is to represent a Store Front.
 * @author jordankrause
 *
 */
public class StoreFront 
{
	//Declare variables
	InventoryManager inventoryManager;
	ShoppingCart shoppingCart;
	Scanner myObj;
	boolean isRunning = true;
	 
	/**
	 * This method welcomes the customer and invokes the methods to initialize the variables and view the main menu.
	 * @param args Arguments.
	 */
	public static void main(String[] args)
	{
		//Create Store Front object and call main menu
		System.out.println("Welcome to the Store Front!");
		StoreFront storeFront = new StoreFront();
		storeFront.initialize();
		
		//Create a server thread instance and pass in the inventory
		ServerThread thread = new ServerThread(storeFront.inventoryManager);
		thread.start();	
		
		//Loop while thread is running 
		if (thread.isAlive())
		{
			//Put this thread to sleep for 1 second
			try 
			{
				Thread.sleep(1000);
			} 
			catch (Exception e)
			{
				//Catch if an error occurs
				throw new ConnectionException(e, "Could not pause the thread.");
			}		
		}
		storeFront.viewMenu();
		
		//Loop to continue letting the user shop until boolean is false
		while(storeFront.isRunning)
		{
			storeFront.viewMenu();
		}
	}
	/**
	 * This method initializes the inventory manager and shopping cart.
	 */
	public void initialize()
	{
		//Initialize the variables 
		inventoryManager = new InventoryManager();
		shoppingCart = new ShoppingCart();
		shoppingCart.initializeCart();
		myObj = new Scanner(System.in);
		try 
		{
			//initialize inventory
			inventoryManager.initializeStoreInventory();
		} 
		catch (ProductException e) 
		{
			//Show error message
			System.out.println("An error occured while reading the file.");
			System.exit(0);
		}
	}
	/**
	 * This is the main menu of the class to ask the user what they would like to do in the store.
	 * @return Returns 0 to ensure no errors.
	 */
	public int viewMenu()
	{
		//Ask user input on what the would like to do				
		System.out.println("-----------MAIN MENU------------");
		System.out.println("Would you like to: \n1. Purchase a product\n"
				+ "2. Return a product\n3. View you cart\n"
				+ "4. View available Salable Products\n"
				+ "5. Exit Game\n(enter number)"); 	
		
		//Store user input
		int choice = myObj.nextInt();
		
		//Determine what the user wants to do at the store
		if (choice == 1)
		{
			//Call purchase product method
			purchaseProduct();
		}
		else if (choice == 2)
		{
			//Call return product method
			returnProduct();	
		}
		else if (choice == 3)
		{
			//Call view cart method
			viewCart();
		}
		else if (choice == 4)
		{
			//Call available product method
			availableProduct();
		}
		else if (choice == 5)
		{
			//Exit program and save inventory
			isRunning = false;
			try 
			{
				inventoryManager.saveInventory();
			} 
			catch (ProductException e) 
			{
				System.out.println("An error occured while saving the inventory.");
				System.exit(0);
			}
		}
		else
		{
			//Ask the question again of what the user would like to do
			System.out.println("Invalid choice");
			viewMenu();
		}
		return 0;
	}
	/**
	 * Allows the user to purchase whichever item they desire.
	 * @return Returns zero to ensure the program ran correctly.
	 */
	public int purchaseProduct()
	{
		String product = "";
		//Call the inventory manager class to get inventory
		for(int i = 0; i < inventoryManager.getInventory().size(); i++)
		{
			product += "\n" + inventoryManager.getInventory().get(i).getId() + ". " + inventoryManager.getInventory().get(i).getName();
		}
		
		//Ask user what they want to buy
		System.out.println("Which item would you like to purchase: " + product);
		
		//Store user input
		int itemPurchased = myObj.nextInt();
		
		//Remove product from inventory manager
		Salable value = inventoryManager.removeProduct(itemPurchased);
		
		//Check value for null
		if (value == null)
		{
			System.out.println("Invalid Option");
		}
		else 
		{
			//Add item to shopping cart
			shoppingCart.addProduct(value);
		}
		return 0;
	}
	/**
	 * Allows the user to return whichever item they desire.
	 * @return Returns zero to ensure the program ran correctly.
	 */
	public int returnProduct()
	{
		String product = "";
		//Call the inventory manager class to get inventory
		for(int i = 0; i < inventoryManager.getInventory().size(); i++)
		{
			product += "\n" + inventoryManager.getInventory().get(i).getId() + ". " + inventoryManager.getInventory().get(i).getName();
		}
		 
		//Ask user what they want to buy
		System.out.println("Which item would you like to purchase: " + product);
		
		//Store user input
		int itemCanceled = myObj.nextInt();
		
		//Add product to inventory manager
		Salable value = inventoryManager.addProduct(itemCanceled);
		
		//Check value for null
		if (value == null)
		{
			System.out.println("Invalid Option");
		}
		else 
		{
			//Remove item from shopping cart
			shoppingCart.removeProduct(value);
		}
		return 0;
	}
	/**
	 * Calls a method to display the items in the shopping cart
	 */
	public void viewCart()
	{
		//Call the shopping cart class to get users cart
		String salable = "";
		int i = 0;
		while(i < shoppingCart.getCart().size())
		{
			salable += shoppingCart.getCart().get(i).getName() + "\n";
			i++;
		}
		System.out.println(salable);
	}
	/**
	 * Displays all the products in the store.
	 */
	public void availableProduct()
	{
		//Call the inventory manager class to get inventory
		String salable = "";
		for(int i = 0; i < inventoryManager.getInventory().size(); i++)
		{
			salable += inventoryManager.getInventory().get(i).getName() + "\n";
		}
		System.out.println(salable);
	}
}