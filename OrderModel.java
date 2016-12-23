import java.util.ArrayList;
//model- no GUI in here
public class OrderModel 
{

	private ArrayList<ItemModel> order;
	private double total;
	private CreditCard card;

	/**
	 * constructor that makes a new arraylist of itemmodels 
	 * and initializes the total to default number
	 */
	public OrderModel() 
	{
		order = new ArrayList<ItemModel>();
		total = 0.00;
	}

	/**
	 * adds an item to order list
	 * @param item the item user wants to order
	 */
	public void add(ItemModel item)
	{
		order.add(item);
		total =total+ item.getPrice();
		total = Math.round(total =total * 100.00)/100.00;

	}

	/**
	 * gets the itemmodels for the list
	 * @return the order model, which is an arraylist of item models
	 */
	public ArrayList<ItemModel> getItems()
	{
		return order;
	}

	/**
	 * sets the card the user wants to use for the transaction 
	 * @param card the credit card the user wants to use
	 */
	public void setCard(CreditCard card)
	{
		this.card = card;
	}

	/**
	 * gets the card the user is using
	 * @return a string representation of teh card being used
	 */
	public String getCard() 
	{
		return card.toString();
	}

	/**
	 * gets the total cost of the bill
	 * @return the end bill
	 */
	public double getTotal()
	{
		return total;
	}
	
	/**
	 * resets the order list and total to default
	 */
	public void clearOrder()
	{
		order = new ArrayList<ItemModel>();
		total = 0.00;
	}

}