package frontend;

import java.util.Scanner;
import backend.AdminService;

/**
 * This class represents the administration of a Store.
 * @author jordankrause
 *
 */
public class Administration 
{
	/**
	 * This method starts the administration side of the store.
	 * @param args Arguments.
	 */
	public static void main(String[] args)
	{	
		//Create instance of adminService
		AdminService adminService = new AdminService();
		
		//Start AdminService
		adminService.start("127.0.0.1", 6666);
		
		//Create Scanner
		Scanner myObj = new Scanner(System.in); 
		System.out.println("To return the inventory press 'R'. To update the inventory press 'U'.");
		
		//Store user input
		String choice = myObj.nextLine();
	
		//Invoke getCommands()
		adminService.getCommands(choice);
		
		//Display inventory
		System.out.println(adminService.getOutput());
		
		//Clean Up
		myObj.close();	
	}
}
