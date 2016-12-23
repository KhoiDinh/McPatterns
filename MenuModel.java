import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//model- no GUI in here

public class MenuModel implements Iterable<ItemModel>
{
	ArrayList<ItemModel> menuChoices = new ArrayList<>();

	/**
	 * constructor that initializes the choice on a menu
	 */
	public MenuModel()
	{
		this.menuChoices = menuChoices;
	}

	/**
	 * adds item with name and price to arraylist
	 * @param name the items's title
	 * @param price the item's cost
	 */
	public void add(String name, Double price)
	{
		menuChoices.add(new ItemModel(name, price));
	}

	/**
	 * gets the size of the arraylist
	 * @return an integer representing the size of the arraylist
	 */
	public int getSize()
	{
		return menuChoices.size();
	}

	/**
	 * iterator that makes it easier to go through the menumodel arraylist
	 */
	public Iterator<ItemModel> iterator()
	{
		return menuChoices.iterator();
	}
}