package backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import exception.ProductException;

/**
 * This class is to Read and Write from a file.
 * @author jordankrause
 *
 */
public class FileService 
{
	/**
	 * This method is to right to the file.
	 * @param filename The name of the file
	 * @param inventory The name of the array list.
	 * @throws ProductException The exception being thrown to catch any errors.
	 */
	public void write(String filename, List<Salable> inventory) throws ProductException
	{
		//Initialize print writer
		PrintWriter pw = null;
		try
		{
			//Create a file to write to
			File file = new File(filename);
			FileWriter fw = new FileWriter(file);
			pw = new PrintWriter(fw);
			
			//Turn array  list into an array
			Salable[] salableArr = new Salable[inventory.size()];
			
			//Add all of the inventory to an array
			inventory.toArray(Salable[]::new);
				
			//Convert inventory array list to JSON
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(salableArr);
			
			//Write inventory as JSON
			pw.println(json);
		}
		catch (Exception e) 
		{
			//Catch exception
			throw new ProductException(e, "Something bad happened read the file.");
		}
		finally
		{
			//Clean up
			pw.close();
		}
	}

	/**
	 * This method read from the file.
	 * @param filename The name of the file.
	 * @return Returns the array of salable products being read.
	 * @throws ProductException Catches the exceptions in the program.
	 */
	public List<Salable> read(String filename) throws ProductException
	{
		//Initialize array list inventory and new array
		List<Salable> inventory = new ArrayList<Salable>();
		Salable[] salable = null;
		
		try
		{
			//Read from JSON file
			ObjectMapper objectMapper = new ObjectMapper();
			salable = objectMapper.readValue(new File(filename), Salable[].class);
			
			//Convert array to array list
			inventory = new ArrayList<Salable>(Arrays.asList(salable));
		}
		catch (IOException e) 
		{
			//Catch exceptions
			throw new ProductException(e, "Something bad happened read the file.");
		}
		return inventory;
	}
}