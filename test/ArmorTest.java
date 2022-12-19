package test;

import org.junit.Assert;
import org.junit.Test;
import backend.Armor;
/**
 * This class is to run a unit test on the Armor Class.
 * @author jordankrause
 *
 */
public class ArmorTest 
{
	/**
	 * This method test the default constructor in the Armor Class.
	 */
	@Test
	public void testArmor() 
	{
		//Create an instance of Armor and test to see if attributes are initialized correctly
		Armor armor = new Armor();
		Assert.assertTrue("", armor.getName() == "");
		Assert.assertTrue("", armor.getDescription() == "");
		Assert.assertEquals(0.0, armor.getPrice(), 0.1);
		Assert.assertEquals(0, armor.getQuantity());
		Assert.assertEquals(0, armor.getId());
	}
	/**
	 * This class test the non default constructor in the Armor Class.
	 */
	@Test
	public void testArmorStringStringDoubleIntInt() 
	{
		//Create an instance of Armor and test to see if the constructor is getting the correct items.
		Armor armor = new Armor("shield", "blocks", 10.0, 10, 5);
		Assert.assertTrue("shield", armor.getName() == "shield");
		Assert.assertTrue("blocks", armor.getDescription() == "blocks");
		Assert.assertEquals(10.0, armor.getPrice(), 0.1);
		Assert.assertEquals(10, armor.getQuantity());
		Assert.assertEquals(5, armor.getId());
	}

}
