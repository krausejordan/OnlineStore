package backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * This class represents a shopping cart.
 * @author jordankrause
 *
 */
public class ShoppingCart 
{
	//Create array list to hold the salable products
	List<Salable> cart;
	
	/**
	 * This method initializes the array that will serve as the cart.
	 */
	public void initializeCart()
	{
		//Create an array that will serve as the cart
		cart = new ArrayList<Salable>();
	}
	
	/**
	 * This method adds the product to the cart, that was desired from the user.
	 * @param value The item that the user wanted to buy.
	 * @return Return an integer to ensure the program ran. 
	 */
	public Salable addProduct(Salable value)
	{
		//Add product to cart array
		cart.add(value);
		return value;
	}
	
	/**
	 * This method removes the product from the cart that the user did not want anymore.
	 * @param value The item that the user did not want to buy anymore.
	 * @return Return an integer to ensure the program ran.
	 */
	public Salable removeProduct(Salable value)
	{
			//Remove product from cart array
			cart.remove(value);
			return value;
	}
	/**
	 * This class is to show the items in the cart.
	 * @return Return the shopping cart. 
	 */
	public List<Salable> getCart() 
	{
		//Display cart array list and return to main menu
		Collections.sort(cart);
		return cart;
	}
	/**
	 * This class allows the entire cart to be emptied.
	 * @return Returns the array list of salable products. Also known as the cart.
	 */
	public List<Salable> emptyCart()
	{
		//Clear the array of carts
		cart.clear();
		return cart;
	}
}