public class CreditCardGenerate
{
	/**
	 * generates and creates a specific credit card depending on the conditions
	 * @param number the credit card number entered 
	 * @return a specific credit card type of null if none of the conditions are fulfilled
	 */
	public static CreditCard makeCard(String number) 
	{
		if (number.length() < 13 || number.length() >19)
		{
			return null;
		}
		else
		{
			number=number.trim();
			if(number.substring(0, 1).equals("3") && number.length()==15&&number.substring(1,2).equals("4") ||number.substring(1,2).equals("7"))
			{
				return new AmericanExpress(number);
			}
			else if(number.substring(0,1).equals("5") && number.substring(1,2).matches("[1-5]")&& number.length()==16)
			{
				return new MasterCard(number);
			}
			else if (number.substring(0,1).equals("4") && (number.length()==13 || number.length()==16))
			{
				return new Visa(number);
			}
			else if(number.substring(0,4).equals("6011") &&number.length()==16)
			{
				return new Discover(number);
			}
			else 
			{
				return null;
			}
		}
	}
}