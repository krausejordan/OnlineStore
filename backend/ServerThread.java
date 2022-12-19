package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

import exception.ConnectionException;
/**
 * This class is to be the thread for the server class.
 * @author jordankrause
 *
 */
public class ServerThread extends Thread
{
	//Declare variables
	private InventoryManager inventory;
	private ServerSocket serverSocket;
	private Socket adminSocket;
	private PrintWriter out;
	private BufferedReader in;
	ServerThread serverThread;
	
	
	/**
	 * Non Default constructor to initialize the thread
	 * @param inventory Holds all the Salable products.
	 */
	public ServerThread(InventoryManager inventory) 
	{
		super();
		this.inventory = inventory;
	}
	/**
	 * Send a message to the server.
	 * @param msg Message to send.
	 * @return Response back from the Server.
	 * @throws IOException Thrown if anything bad happens from any of the networking classes.
	 */
	public String sendMessage(String msg) throws IOException
	{
		//Send/print a message to server with a terminating line feed
		out.println(msg);
		
		//Return the response from the server
		return in.readLine();
	}
	/**
	 * This method creates the thread for the connection. 
	 */
	public void run() 
	{
		try
		{
			//Start the server on port
			start(6666);
			
			//Read input if not null
			String inputLine;
			while ((inputLine = in.readLine()) != null)
			{
				String[] tokens = inputLine.split("\\|");
			
				//If command entered is 'R'
				if("R".equals(inputLine))
				{
					try
					{
						//JSON for the store items
						ObjectMapper objectMapper = new ObjectMapper();
						String json = objectMapper.writeValueAsString(inventory.getInventory().toArray(Salable[]::new));
						out.println(json);
					}
					catch (Exception e)
					{
						//Catch if an error occurs
						throw new ConnectionException(e, "Could not read the inventory from the ServerThread.");
					}
				}
				else if(tokens[0].equals("U"))
				{	
					inventory.updateInventory(tokens[1]);
				}
			}
		}
		catch (Exception e)
		{
			//Catch if an error occurs
			throw new ConnectionException(e, "Could not start the server");
		}
	}
	/**
	 * Start the Server and wait for connections on the specified port
	 * @param port Port to listen on.
	 */
	public void start(int port) 
	{
		try
		{
			//Wait for a Client connection
			serverSocket = new ServerSocket(port);
			adminSocket = serverSocket.accept();
			
			//create input and output network buffers to communicate back and forth
			out = new PrintWriter(adminSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(adminSocket.getInputStream()));
		}
		catch (Exception e)
		{
			//Catch if an error occurs
			throw new ConnectionException(e, "Could not start the server");
		}
	}
	/**
	 * Cleanup logic to close all the network connections
	 * @throws ConnectionException This custom exception is thrown when an error occurs.
	 */
	public void cleanup() 
	{
		try
		{
			//Close all input and output network buffers and sockets
			in.close();
			out.close();
			adminSocket.close();
			serverSocket.close();
		}
		catch (Exception e)
		{
			//Catch if an error occurs
			throw new ConnectionException(e, "Could not start the server");
		}
	}
}
