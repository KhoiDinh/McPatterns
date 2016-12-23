import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
//presenter- models can be in here
public class McPatternsPresenter
{
	// This is the class that will handle the model <-> UI communication.
	OrderModel model;
	McPatternsGUI view;
	String filename;
	MenuModel menuModel;

	/**
	 * constructor that takes in file name as stirng parameter
	 * @param filename
	 */
	public McPatternsPresenter(String filename) 
	{
		model = new OrderModel();
		this.filename = filename;
		menuModel = new MenuModel();
	}
	
	/**
	 * another constructor that takes in no parameters
	 */
	public McPatternsPresenter() 
	{
		model = new OrderModel();
		menuModel = new MenuModel();
	}

	/**
	 * 
	 * @param view
	 */
	public void attachView(McPatternsGUI view) 
	{
		this.view = view;
	}

	/**
	 * loads the file in order to create itemmodel and adds to menumodel object to call method 
	 * from view to make buttons 
	 * @throws FileNotFoundException
	 * @throws NumberFormatException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public void loadItems() throws FileNotFoundException, NumberFormatException, ArrayIndexOutOfBoundsException
	{
		try (Scanner in = new Scanner(new File(filename)))
		{
			while (in.hasNextLine()) 
			{
				String line = in.nextLine();
				double price = 0.00;
				if(line.length()>0)
				{
					String itemName="";
					try
					{
						itemName = line.substring(0, line.indexOf("|")).trim();
						
					}
					catch (Exception e)
					{
						
						System.out.println("Entry does not contain a pipe: " + line );
						continue;
					}
					
					
					
					try
					{
						price = Double.parseDouble(line.substring(line.indexOf("|") + 1).trim());
					}
					catch(Exception e)
					{
						System.out.println("Price in this line not a double: " + line);
						continue;
					}
					menuModel.add(itemName, price);
					
				}
			}
		} 
		catch (Exception e) 
		{
			throw new FileNotFoundException();
		}
		
		Iterator<ItemModel> iter = menuModel.iterator();
		while(iter.hasNext())
		{
			view.addChoiceButton(iter.next().toString());
		}

	}

	/**
	 * adds items to order object and updates bill
	 * @param item the item the user wants
	 */
	public void addItem(String item) 
	{
		String itemName = item.substring(0, item.indexOf(":")).trim();
		double price = Double.parseDouble(item.substring(item.indexOf(":") + 1).trim());
		ItemModel adding = new ItemModel(itemName, price);
		model.add(adding);
		view.updateBill(model.getTotal());
		
	}
	
	/**
	 * clears order model value back to 0.00 and clears its list of items
	 */
	public void clearTotal() 
	{
		model.clearOrder();
		view.updateBill(0.0);
	}
	
	/**
	 * gets card type back
	 * @return the model of the credit card used in string format
	 */
	public String getCard()
	{
		return model.getCard();
	}
	
	/**
	 * prints out the list of item models in model to console
	 */
	public void getReceipt() {
		ArrayList<ItemModel> order = model.getItems();
		if (order.size() == 0)
		{
			return;
		}
		
		System.out.println("Order confirmed");
		
		for(int i=0; i <order.size(); i++ )
		{
			ItemModel item = order.get(i);
			view.addToReceipt(item.toString());
			System.out.println(item.getName() + ": " + item.getPrice());
		}
		
		System.out.println("---");
		System.out.println("Grand total: " + model.getTotal());
		System.out.println("Paid with: " + mask(model.getCard()));
		System.out.println();
	}
	
	/**
	 * when printing out card number to console, masks last digits for security
	 * @param card the string representation of card type and card number
	 * @return a new string with the card type and masked card number
	 */
	private String mask(String card)
	{
		String asterisk = "*";
		String part = card.substring(0, card.length() - 9);
		for(int i = card.length() - 9; i < card.length(); i++)		//Makes a string of asterisks to 'mask' the rest of the digits with asterisks.
		{
			part += "*";
		}
	
	return part;	
	}

	/**
	 * checks whether card number entered is valid
	 * @param ccNumber the card number the user enters
	 * @return true if card exists and false if it doesn't exist
	 */
	public boolean validateCard(String ccNumber)
	{
		CreditCard cc = CreditCardGenerate.makeCard(ccNumber);
		if (cc == null) 
		{
			return false;
		} 
		else 
		{
			model.setCard(cc);
			return true;
			
		}
	}
}