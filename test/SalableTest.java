package test;

import org.junit.Assert;
import org.junit.Test;
import backend.Salable;
import backend.Weapon;

/**
 * This class is to run unit test on the Salable class.
 * @author jordankrause
 *
 */
public class SalableTest
{
	/**
	 * This method tests the default constructor in the Salable class.
	 */
	@Test
	public void testSalable()
	{
		//Create an instance of Weapon and test to see if attributes are initialized correctly
		Salable salable = new Salable();
		Assert.assertTrue("", salable.getName() == "");
		Assert.assertTrue("", salable.getDescription() == "");
		Assert.assertEquals(0.0, salable.getPrice(), 0.1);
		Assert.assertEquals(0, salable.getQuantity());
		Assert.assertEquals(0, salable.getId());
	}
	/**
	 * This method tests the non default constructor in the Salable class.
	 */
	@Test
	public void testSalableStringStringDoubleIntInt() 
	{
		//Create an instance of Weapon and test to see if the constructor is getting the correct items.
		Weapon weapon = new Weapon("knife", "stabs", 10.0, 10, 5);
		Assert.assertTrue("knife", weapon.getName() == "knife");
		Assert.assertTrue("stabs", weapon.getDescription() == "stabs");
		Assert.assertEquals(10.0, weapon.getPrice(), 0.1);
		Assert.assertEquals(10, weapon.getQuantity());
		Assert.assertEquals(5, weapon.getId());
	}
	/**
	 * This method tests the copy constructor in Salable class.
	 */
	@Test
	public void testSalableSalable() 
	{
		//Create an instance of Weapon and test to see if the copy constructor is getting the correct items.
		Weapon weapon = new Weapon("knife", "stabs", 10.0, 10, 5);
		Assert.assertTrue("knife", weapon.getName() == "knife");
		Assert.assertTrue("stabs", weapon.getDescription() == "stabs");
		Assert.assertEquals(10.0, weapon.getPrice(), 0.1);
		Assert.assertEquals(10, weapon.getQuantity());
		Assert.assertEquals(5, weapon.getId());
	}
	/**
	 * This method test the getName method in the Salable class.
	 */
	@Test
	public void testGetName() 
	{
		//Create an instance of Weapon and test to see if the getter is getting the correct name.
		Weapon weapon = new Weapon("knife", "stabs", 10.0, 10, 5);
		Assert.assertTrue("knife", weapon.getName() == "knife");
	}
	/**
	 * This method test the getDescription method in the Salable class.
	 */
	@Test
	public void testGetDescription() 
	{
		//Create an instance of Weapon and test to see if the getter is getting the correct description.
		Weapon weapon = new Weapon("knife", "stabs", 10.0, 10, 5);
		Assert.assertTrue("stabs", weapon.getDescription() == "stabs");
	}
	/**
	 * This method test the getPrice method in the Salable class.
	 */
	@Test
	public void testGetPrice()
	{
		//Create an instance of Weapon and test to see if the getter is getting the correct price.
		Weapon weapon = new Weapon("knife", "stabs", 10.0, 10, 5);
		Assert.assertEquals(10.0, weapon.getPrice(), 0.1);
	}
	/**
	 * This method test the getQuantity method in the Salable class.
	 */
	@Test
	public void testGetQuantity()
	{
		//Create an instance of Weapon and test to see if the getter is getting the correct quantity.
		Weapon weapon = new Weapon("knife", "stabs", 10.0, 10, 5);
		Assert.assertEquals(10, weapon.getQuantity());
	}
	/**
	 * This method test the setQuantity method in the Salable class.
	 */
	@Test
	public void testSetQuantity() 
	{
		//Create an instance of Weapon and test to see if the getter is setting the item correctly.
		Weapon weapon = new Weapon("knife", "stabs", 10.0, 10, 5);
		weapon.setQuantity(5);
		Assert.assertEquals(5, weapon.getQuantity());
	}
	/**
	 * This method test the getId method in the Salable class.
	 */
	@Test
	public void testGetId()
	{
		//Create an instance of Weapon and test to see if the getter is getting the correct ID.
		Weapon weapon = new Weapon("knife", "stabs", 10.0, 10, 5);
		Assert.assertEquals(5, weapon.getId());
	}
	/**
	 * This method test the compareTo method in the Salable class.
	 */
	@Test
	public void testCompareTo() 
	{
		//Create two new weapons and test to see if the method is comparing the items correctly
		Weapon firstWeapon = new Weapon("knife", "stabs 3in", 10.0, 10, 5);
		Weapon secondWeapon = new Weapon("knife", "stabs 6in", 20.0, 10, 5);
		Assert.assertEquals(-1, firstWeapon.compareTo(secondWeapon));
		
		firstWeapon = new Weapon("knife", "stabs 3in", 20.0, 10, 5);
		secondWeapon = new Weapon("knife", "stabs 6in", 20.0, 10, 5);
		Assert.assertEquals(0, firstWeapon.compareTo(secondWeapon));
		
		firstWeapon = new Weapon("knife", "stabs 3in", 20.0, 10, 5);
		secondWeapon = new Weapon("knife", "stabs 6in", 10.0, 10, 5);
		Assert.assertEquals(1, firstWeapon.compareTo(secondWeapon));
	}

}
