package backend;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	include = JsonTypeInfo.As.PROPERTY,
	property = "type")
@JsonSubTypes({
	@Type(value = Weapon.class, name = "Weapon"),
	@Type(value = Armor.class, name = "Armor"),
	@Type(value = Health.class, name = "Health")
})

/**
 * This class is to represent a salable product.
 * @author jordankrause
 *
 */
public class Salable implements Comparable<Salable>
{
	private String name;
	private String description;
	private double price;
	private int quantity;
	private int id;
	
	/**
	 * Default constructor to initialize variables.
	 */
	public Salable() 
	{
		super();
		this.name = "";
		this.description = "";
		this.price = 0;
		this.quantity = 0;
	}
	
	/**
	 * Non default constructor that initializes all class member variables.
	 * @param name: Name of Salable.
	 * @param description: Description of Salable.
	 * @param price: Price of Salable.
	 * @param quantity: Quantity of Salable.
	 * @param id: ID of Salable.
	 */
	public Salable(String name, String description, double price, int quantity, int id) 
	{
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.id = id;
	}
	
	/**
	 * Copy constructor to allow salable to be cloned. 
	 * @param salable Passing in a salable to the copy constructor. 
	 */
	public Salable(Salable salable)
	{
		this.name = salable.getName();
		this.description = salable.getDescription();
		this.price = salable.getPrice();
		this.quantity = salable.getQuantity();
		this.id = salable.getId();
	}
	/**
	 * Gets the name of the product.
	 * @return Returns the name of the product.
	 */
	public String getName() 
	{
		return name;
	}
	/**
	 * Gets the description of the product.
	 * @return Returns the description of the product.
	 */
	public String getDescription() 
	{
		return description;
	}
	/**
	 * Get the price of the product.
	 * @return Returns the price of the product.
	 */
	public double getPrice() 
	{
		return price;
	}
	/**
	 * Gets the quantity of the product.
	 * @return Returns the quantity of the product.
	 */
	public int getQuantity() 
	{
		return quantity;
	}
	/**
	 * Sets the quantity of the product allowing the value to be updated. 
	 * @param quantity Passes in an integer call quantity. 
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * Gets the ID of the product
	 * @return Returns the id of the product
	 */
	public int getId()
	{
		return id;
	}
	/**
	 * Utilizing the compare to method to find the object the user inputed. 
	 */
	@Override
	public int compareTo(Salable o) 
	{
		//Compare the name of the strings
		int itemName = this.name.compareTo(o.name);
		if(itemName == 0)
		{
			//Compare the name to the price
			String itemPrice = String.valueOf(this.price);
			int value = itemPrice.compareTo(String.valueOf(o.price));
			return value;
		}
		else
		{
			return itemName;
		}	
	}
}