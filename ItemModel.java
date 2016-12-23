public class ItemModel
{
	
	private String itemName;
	private double price;
	
	/**
	 * constructor for item model
	 * @param itemName the name of the item
	 * @param price the cost of the item
	 */
	public ItemModel(String itemName, double price)
	{
		this.itemName = itemName;
		this.price = price;
	}
	
	/**
	 * get the item's name
	 * @return the item name 
	 */
	public String getName() 
	
	{
		return itemName;
	}
	
	/**
	 * gets the item's price
	 * @return the cost of the item
	 */
	public double getPrice() 
	{
		return price;
	}
			
	/**
	 *@return a string representation of the item  
	 */
	public String toString() 
	{
		return getName() + ": " + getPrice();
	}
}