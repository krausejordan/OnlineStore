package backend;
/**
 * This class is to represent a weapon product.
 * @author jordankrause
 *
 */
public class Weapon extends Salable
{
	/**
	 * Default constructor to initialize variables.
	 */
	public Weapon()
	{
		super();
	}
	/**
	 * Instantiate member variables.
	 * @param name: Name of Weapon.
	 * @param description: Description of Weapon.
	 * @param price: Price of Weapon.
	 * @param quantity: Number of Weapons.
	 * @param id: ID of Weapons.
	 */
	public Weapon(String name, String description, double price, int quantity, int id) 
	{
		super(name, description, price, quantity, id);
	}
}