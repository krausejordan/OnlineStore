package backend;

/**
 * This class is to represent armor.
 * @author jordankrause
 *
 */
public class Armor extends Salable
{
	/**
	 * Default constructor to initialize variables.
	 */
	public Armor()
	{
		super();
	}
	/**
	 * Instantiate member variables.
	 * @param name: Name of Armor.
	 * @param description: Description of Armor.
	 * @param price: Price of Armor.
	 * @param quantity: Number of Armor.
	 * @param id: ID of Armor.
	 */
	public Armor(String name, String description, double price, int quantity, int id) 
	{
		super(name, description, price, quantity, id);
	}
}