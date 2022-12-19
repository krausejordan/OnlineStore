package test;

import org.junit.Assert;
import org.junit.Test;
import backend.Weapon;
/**
 * This class is to run a unit test on the Weapon Class.
 * @author jordankrause
 *
 */
public class WeaponTest 
{
	/**
	 * This method test the default constructor in the Weapon class.
	 */
	@Test
	public void testWeapon() 
	{
		//Create an instance of Weapon and test to see if attributes are initialized correctly
		Weapon weapon = new Weapon();
		Assert.assertTrue("", weapon.getName() == "");
		Assert.assertTrue("", weapon.getDescription() == "");
		Assert.assertEquals(0.0, weapon.getPrice(), 0.1);
		Assert.assertEquals(0, weapon.getQuantity());
		Assert.assertEquals(0, weapon.getId());
	}
	/**
	 * This method test the non default constructor in the Weapon class.
	 */
	@Test
	public void testWeaponStringStringDoubleIntInt() 
	{
		//Create an instance of Weapon and test to see if the constructor is getting the correct items.
		Weapon weapon = new Weapon("knife", "stabs", 10.0, 10, 5);
		Assert.assertTrue("knife", weapon.getName() == "knife");
		Assert.assertTrue("stabs", weapon.getDescription() == "stabs");
		Assert.assertEquals(10.0, weapon.getPrice(), 0.1);
		Assert.assertEquals(10, weapon.getQuantity());
		Assert.assertEquals(5, weapon.getId());
	}

}
