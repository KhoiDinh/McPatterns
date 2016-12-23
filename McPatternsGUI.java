import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
//view- no models in here, only presenter
//ask if presenter can call method(ex presenter.loadItem) which will create and add itemModel to menuModel and use view to make button
@SuppressWarnings("serial")
public class McPatternsGUI extends JFrame 
{
	McPatternsPresenter presenter;
	JFrame frame;
	JPanel receipt;
	JPanel title;
	JPanel itemPanel;
	JPanel orderPane;
	JPanel order;
	JLabel total;
	JScrollPane orderscroll;
	JScrollPane menuscroll;

	/**
	 * The GUI for McPatterns.
	 * @param presenter the presenter for the GUI.
	 * @throws FileNotFoundException if the file is not found.
	 */
	public McPatternsGUI(McPatternsPresenter presenter) throws FileNotFoundException
	{
		this.presenter = presenter;
		this.presenter.attachView(this);
		initializeUI();
	}
	
	/**
	 * creates all the new frames, panels, adn sets some text for program start up
	 * @throws FileNotFoundException
	 */
	private void initializeUI() throws FileNotFoundException 
	{
		// creates the main frame
		frame = new JFrame("McPatterns");
		frame.setSize(1000, 900);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		// creates frame title
		title = new JPanel(new FlowLayout());
		title.add(new JLabel("Welcome to McPatterns."));
		


		itemPanel = new JPanel();
		itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
		menuscroll = new JScrollPane(itemPanel);
		menuscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		menuscroll.setPreferredSize(new Dimension(300,500));

		try
		{
			presenter.loadItems();

		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

		// makes the order panel
		orderPane = new JPanel();
		orderPane.setLayout(new BoxLayout(orderPane, BoxLayout.PAGE_AXIS));

		// creates the order list pane
		order = new JPanel();
		order.setLayout(new BoxLayout(order, BoxLayout.PAGE_AXIS));
		

		// makes label for order list
		JLabel orderDetails = new JLabel("Your order:");
		
		orderPane.add(orderDetails);
		orderscroll = new JScrollPane(orderPane);
		orderscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		orderscroll.setPreferredSize(new Dimension(300,500));
		

		// makes the total bill label
		total = new JLabel("Total: 0.00");

		// creates credit entry field, confirm and clear buttons
		JTextField cc = new JTextField("Enter CC#");
		JButton confirm = new JButton("Place Order");
		confirm.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				if (!total.getText().equals("Total: 0.00"))
				{
					if (cc.getText().matches("[0-9]+") && !cc.getText().isEmpty())
					{
						if (presenter.validateCard(cc.getText())) 
						{
							orderDetails.setText("Order confirmed.");
							cc.setText("Enter CC#");
							getReceipt();
							order.removeAll();
							presenter.clearTotal();
							frame.pack();
						} 
						else 
						{
							orderDetails.setText("Invalid credit card number.");
							frame.pack();
						}
					}
					else if(cc.getText().matches("[aZ-zA]+")&&!cc.getText().isEmpty())
					{
						orderDetails.setText("No letters allowed.");
						frame.pack();
					}
					else 
					{
						orderDetails.setText("Invalid credit card number.");
						frame.pack();
					}
				}
			}
		});
		JButton clear = new JButton("Clear Order");
		clear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				orderDetails.setText("Your order:");
				cc.setText("Enter CC#");
				order.removeAll();
				presenter.clearTotal();
				frame.pack();
			}
		});

		orderPane.add(order, BorderLayout.WEST);
		orderPane.add(total);
		orderPane.add(cc);
		orderPane.add(confirm);
		orderPane.add(clear);

		// adds all panels to main frame
		frame.add(title, BorderLayout.NORTH);
		frame.add(menuscroll, BorderLayout.CENTER);
		frame.add(orderscroll, BorderLayout.EAST);
		frame.setSize(800,600);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * adds the item the user wants to order in the form of a label
	 * @param item the object the user wants to order
	 */
	public void addToReceipt(String item)
	{
		receipt.add(new JLabel(item));
	}

	/**
	 * adds a button with the object and its price 
	 * @param item the object on the text file
	 */
	public void addChoiceButton(String item)
	{
		JButton button = new JButton(item);
		

		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				order.add(new JLabel(item));
				presenter.addItem(item);
				frame.pack();
			}
		});
		itemPanel.add(button);
	}

	/**
	 * updates your bill as you add items
	 * @param newTotal the total bill after adding the new item
	 */
	public void updateBill(double newTotal) 
	{
		total.setText("Total: " + Double.toString(newTotal));
		frame.pack();
	}

	/**
	 * returns a window with confirmation, items you ordered, and the total bill
	 */
	private void getReceipt()
	{
		JFrame frame = new JFrame("Receipt");

		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		receipt = new JPanel();
		receipt.setSize(1000, 800);
		receipt.setLayout(new BoxLayout(receipt, BoxLayout.PAGE_AXIS));
		presenter.getReceipt();
		receipt.add(new JLabel("---"));
		receipt.add(new JLabel("Total: " + total.getText().substring(7)));
		receipt.add(new JLabel("Payment Method: " + presenter.getCard()));//can i do this?
		frame.add(receipt);
		frame.pack();
		frame.setVisible(true);
	}
}