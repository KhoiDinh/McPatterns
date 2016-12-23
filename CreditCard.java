public class CreditCard
{
	private String ccNumber;
	private String type;
	
	/**
	 * empty constructor 
	 */
	public CreditCard() 
	{
		
	}
	
	/**
	 * sets the credit card number, trimming off the white spaces
	 * @param number the credit card number
	 */
	public void setNumber(String number)
	{
		ccNumber = number.trim();
	}
	
	/**
	 * gets the credit card number
	 * @return the credit card number
	 */
	public String getNumber() 
	{
		return ccNumber;
	}
	
	/**
	 * sets the type of credit card used
	 * @param type the credit card type
	 */
	public void setType(String type) 
	{
		this.type = type;
	}
	
	/**
	 * gets the credit card type
	 * @return the type of credit card used
	 */
	public String getType() 
	{
		return type;
	}
	
	/**
	 * @return string representation of credit card
	 */
	public String toString() 
	{
		return "[" + getType() + "] " + getNumber();
	}
}

/**
 * all subclasses of credit card which uses credit card as super class and sets 
 * to appropriate values
 * @author Owner
 *
 */
class AmericanExpress extends CreditCard 
{
	public AmericanExpress(String number) 
	{
		super.setNumber(number);
		super.setType("AmericanExpress");
	}
}


class Discover extends CreditCard 
{
	public Discover(String number) 
	{
		super.setNumber(number);
		super.setType("Discover");
	}
}

class MasterCard extends CreditCard
{
	public MasterCard(String number) 
	{
		super.setNumber(number);
		super.setType("MasterCard");
	}
}

class Visa extends CreditCard 
{
	public Visa(String number) 
	{
		super.setNumber(number);
		super.setType("Visa");
	}
}
