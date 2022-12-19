package backend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;

import exception.ConnectionException;
import exception.ProductException;
/**
 * This class to represent an adminService.
 * @author jordankrause
 *
 */
public class AdminService
{
	//Declare Variables
	private Socket adminSocket;
	private PrintWriter out;
	private BufferedReader in = null;
	ServerThread inventory;
	FileService fileService;
	int lastID = 6;
	private String output;
	
	/**
	 * This method runs the commands inputed.
	 * @param input Holds the value of the message sent.
	 * @return Integer returned to ensure no failure.
	 */
	public int getCommands(String input)
	{
		try
		{
			//Create a message string
			String message = "";
			//If user wants to return the inventory
			if (input.equals("R"))
			{
				//Invoke sendMessage()
				message = sendMessage(input);
				
				//Create an array of Salable products
				List<Salable> salables = new ArrayList<Salable>();
				
				Scanner reader = new Scanner(message);
				
				while(reader.hasNext())
				{
					//Read in input from network
					String json = reader.nextLine();
					ObjectMapper objectMapper = new ObjectMapper();
					Salable[] salable = objectMapper.readValue(json, Salable[].class);
					
					//Convert to list and add to array list
					List<Salable> list = new ArrayList<Salable>(Arrays.asList(salable))	;
					salables.addAll(list);
				}
				
				for(int i = 0; i < salables.size(); i++)
				{
					output += "\nName: " + salables.get(i).getName() + " Description: " + salables.get(i).getDescription() + " Price: " + salables.get(i).getPrice()
							+ " Quantity: " + salables.get(i).getQuantity() + " ID: " + salables.get(i).getId();
				}
				reader.close();
			}
			else if(input.equals("U"))
			{	
				try
				{
					//JSON for the store items
					message = sendMessage(createProduct());
				}
				catch (Exception e) 
				{
					//Catch exception
					throw new ProductException(e, "Something bad happened read the file.");
				}
			}
		}
		catch (Exception e)
		{
			//Catch if an error occurs
			throw new ConnectionException(e, "Something bad happened while reading commands");
		}
		return 0;
	}
	
	/**
	 * This method returns the output of the inventory read over the network.
	 * @return Return the inventory.
	 */
	public String getOutput()
	{
		return this.output;
	}
	
	/**
	 * This method allows the user to create a new Salable.
	 * @return Returns a JSON String.
	 */
	public String createProduct()
	{
		//Create Scanner
		Scanner myObj = new Scanner(System.in); 
		
		//See what user wants to add and store input
		System.out.println("Would you like to add a health, weapon, or armor? ");
		String type = myObj.nextLine();
		
		//See what user wants to add and store input
		System.out.println("Type name of item: ");
		String name = myObj.nextLine();
		
		//See what user wants to add and store input
		System.out.println("Type description of item: ");
		String description = myObj.nextLine();
		
		//See what user wants to add and store input
		System.out.println("Type price of item: ");
		Double price = myObj.nextDouble();
		
		//See what user wants to add and store input
		System.out.println("Type quanity of item");
		int quantity = myObj.nextInt();
		
		lastID = lastID + 1;
		Salable salable = null;
		if(type.equals("Weapon"))
		{
			//Create new Weapon with inputs
			Weapon weapon = new Weapon(name, description, price, quantity, lastID);
			salable = weapon;
		}
		else if (type.equals("Health"))
		{
			//Create new Health with inputs
			Health health = new Health(name, description, price, quantity, lastID);
			salable = health;
		}
		else if (type.equals("Armor"))
		{
			//Create new Armor with inputs
			Armor armor = new Armor(name, description, price, quantity, lastID);
			salable = armor;
		}
		else
		{
			System.out.println("Invalid option");
			
		}
		
		//Create ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "U|";
		try 
		{
			//Write JSON
			json += objectMapper.writeValueAsString(salable);
		} 
		catch (Exception e)
		{
			//Catch if an error occurs
			throw new ConnectionException(e, "Error while writing to JSON.");
		}
		finally
		{
			myObj.close();
		}
		return json;
	}

	/**
	 * This method allows the user to create a new Salable.
	 * @param ip Holds String value of the IP address.
	 * @param port Holds integer value of port.
	 */
	public void start(String ip, int port)
	{
		try 
		{
			//Connect to the remote server on the specified IP Address and Port
			adminSocket = new Socket(ip, port);
			out = new PrintWriter(adminSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(adminSocket.getInputStream()));
		} 
		catch (Exception e) 
		{
			//Catch if an error occurs
			throw new ConnectionException(e, "Something bad happened while reading commands");
		}
	}
	
	/**
	 * Send a message to the server.
	 * @param msg Message to send.
	 * @return Response back from the Server.
	 */
	public String sendMessage(String msg)
	{
		//Send a message to server with a terminating line feed
		out.println(msg);
		
		//Return the response from the server
		try 
		{
			return in.readLine();
		} 
		catch (Exception e) 
		{
			//Catch if an error occurs
			throw new ConnectionException(e, "Something bad happened while reading a line");
		}
	}
	/**
	 * Cleanup logic to close all the network connections.
	 */
	public void cleanup()
	{
		//Close all input and output network buffers and sockets
		try {
			in.close();
			out.close();
			adminSocket.close();
		} 
		catch (Exception e) 
		{
			//Catch if an error occurs
			throw new ConnectionException(e, "Something bad happened while closing the input and output buffers.");
		}
		
	}
}