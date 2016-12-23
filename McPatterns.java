import java.io.FileNotFoundException;

public class McPatterns
{
	@SuppressWarnings("unused")
	public static void main(String[] args) throws FileNotFoundException
	{
		if (args.length == 0) 
		{
			System.out.println("Enter a valid menu text file.");
			throw new FileNotFoundException();
		}
		McPatternsGUI gui = new McPatternsGUI(new McPatternsPresenter(args[0]));
	}
}