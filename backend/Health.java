package backend;

/**
 * This class is to represent health.
 * @author jordankrause
 *
 */
public class Health extends Salable
{
	/**
	 * Default constructor to initialize variables.
	 */
	public Health()
	{
		super();
	}
	/**
	 * Instantiate member variables.
	 * @param name: Name of Health.
	 * @param description: Description of Health.
	 * @param price: Price of Health.
	 * @param quantity: Number of Health.
	 * @param id: ID of Health
	 */
	public Health(String name, String description, double price, int quantity, int id) 
	{
		super(name, description, price, quantity, id);
	}
}