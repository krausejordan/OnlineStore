package backend;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.ProductException;

/**
 * 
 * This class is to manage the inventory of the Store. 
 * @author jordankrause
 *
 */
public class InventoryManager 
{
	//Create an array to store the inventory
	List<Salable> inventory= new ArrayList<Salable>();
	FileService fileService;
	
	/**
	 * Default constructor in the class.
	 */
	public InventoryManager()
	{
		super();
	}
	/**
	 * Non default constructor to initialize inventory
	 * @param inventory ArrayList of the inventory of the store.
	 */
	public InventoryManager(List<Salable> inventory) 
	{
		super();
		this.inventory = inventory;
	}

	/**
	 * This method reads from the file.
	 * @throws ProductException Exception thrown to catch an error in the program.
	 */
	public void initializeStoreInventory() throws ProductException
	{
		//Read from JSON File
		FileService fileService = new FileService();
		inventory = fileService.read("inputFile.json");
	}
	
	/**
	 * This method copies the salable product, and increments the quantity of the salable by one. 
	 * @param itemCanceled ItemCanceled is the user input of the product they want to buy.
	 * @return Return 0 to ensure the program ran correctly.
	 */
	public Salable addProduct(int itemCanceled)
	{
		//Copy constructor to clone the product
		Salable item = new Salable(inventory.get(itemCanceled));
		if (itemCanceled <= inventory.size() - 1)
		{
			//Increment quantity of the product
			Salable salable = new Salable(item);
			salable.getQuantity();
			int quantity = salable.getQuantity();
			quantity++;
			salable.setQuantity(quantity);
		}
		else
		{
			return null;
		}
		return item;
	}
	/**
	 * This method removes a product from the inventory manager when an item is purchased.
	 * @param itemPurchased The item that the user wants to buy.
	 * @return Returns an int to ensure the program ran. 
	 */
	public Salable removeProduct(int itemPurchased)
	{		
			//Copy constructor to clone the product
			Salable item = new Salable(inventory.get(itemPurchased));
			
		if (itemPurchased <= inventory.size() - 1)
		{
			//Decrement the quantity of the product
			Salable salable = new Salable(item);
			salable.getQuantity();
			int quantity = salable.getQuantity();
			quantity--;
			salable.setQuantity(quantity);	
		}
		else
		{
			return null;
		}
		return item;
	}
	/**
	 * This method saves the file that is being used.
	 * @throws ProductException File Service exception to catch if the file errors. 
	 */
	public void saveInventory() throws ProductException
	{
		//Create an object of file Service
		FileService fileService = new FileService();
		try
		{
			//Write to file 
			fileService.write("inputFile.json", inventory);
		}
		catch (Exception e) 
		{
			//Catch if an error occurs
			throw new ProductException(e, "Something bad happened read the file.");
		}
	}
	
	/**
	 * This method returns the items in the inventory. 
	 * @return Returns the array of salable products which serves as the inventory.
	 */
	public List<Salable> getInventory()
	{
		//Display all the array of salable products
		return inventory;
	}
	/**
	 * This method adds the new product to the inventory
	 * @throws ProductException Custom exception thrown if an error occurs. 
	 * @param json Passes in the json string from the server thread.
	 */
	public synchronized void updateInventory(String json) throws ProductException
	{
		try
		{
			//Read value from JSON and add to inventory
			Salable salable;
			ObjectMapper objectMapper = new ObjectMapper();
			salable = objectMapper.readValue(json, Salable.class);
			inventory.add(salable);
		} 
		catch (Exception e) 
		{
			//Catch if an error occurs
			throw new ProductException(e, "Something bad happened read the file.");
		}
	}
}