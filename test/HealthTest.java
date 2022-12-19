package test;

import org.junit.Assert;
import org.junit.Test;
import backend.Health;
/**
 * This class is to run a unit test on the Health Class.
 * @author jordankrause
 *
 */
public class HealthTest 
{
	/**
	 * This method test the default constructor in the Health class.
	 */
	@Test
	public void testHealth() 
	{
		//Create an instance of Health and test to see if attributes are initialized correctly
		Health health = new Health();
		Assert.assertTrue("", health.getName() == "");
		Assert.assertTrue("", health.getDescription() == "");
		Assert.assertEquals(0.0, health.getPrice(), 0.1);
		Assert.assertEquals(0, health.getQuantity());
		Assert.assertEquals(0, health.getId());
	}
	/**
	 * This method test the non default constructor in the Health class.
	 */
	@Test
	public void testHealthStringStringDoubleIntInt() 
	{
		//Create an instance of Health and test to see if the constructor is getting the correct items.
		Health health = new Health("health boost", "increase health", 10.0, 10, 5);
		Assert.assertTrue("health boost", health.getName() == "health boost");
		Assert.assertTrue("increase health", health.getDescription() == "increase health");
		Assert.assertEquals(10.0, health.getPrice(), 0.1);
		Assert.assertEquals(10, health.getQuantity());
		Assert.assertEquals(5, health.getId());
	}

}
