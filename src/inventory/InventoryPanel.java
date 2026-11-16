package inventory;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel {
	
	private Inventory inventory;
	
	private JTextField idField;
	private JTextField nameField;
	private JTextField costField;
	private JTextField quantityField;
	private JTextField reorderField;
	
	private JButton addButton;
	private JButton findButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JButton restockButton;
	private JButton lowStockButton;
	
	private JTextArea outputArea;
	
	// Constructor
	public InventoryPanel(Inventory inventory) {
		
		this.inventory = inventory;
		
		setLayout(new BorderLayout());
		
		// Panel for ingredient information
		JPanel inputPanel = new JPanel(new GridLayout(5, 2));
		
		idField = new JTextField();
		nameField = new JTextField();
		costField = new JTextField();
		quantityField = new JTextField();
		reorderField = new JTextField();
		
		inputPanel.add(new JLabel("Ingredient ID:"));
		inputPanel.add(idField);
		
		inputPanel.add(new JLabel("Ingredient name:"));
		inputPanel.add(nameField);
		
		inputPanel.add(new JLabel("Unit Cost:"));
		inputPanel.add(costField);
		
		inputPanel.add(new JLabel("Quantity:"));
		inputPanel.add(quantityField);
		
		inputPanel.add(new JLabel("Reorder Level:"));
		inputPanel.add(reorderField);
		
		add(inputPanel, BorderLayout.NORTH);
		
		// Panel for buttons
		JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
		
		addButton = new JButton("Add");
		findButton = new JButton("Find");
		updateButton = new JButton("Update");
		deleteButton = new JButton("Delete");
		restockButton = new JButton("Restock");
		lowStockButton = new JButton("Low Stock");
		
		buttonPanel.add(addButton);
		buttonPanel.add(findButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(restockButton);
		buttonPanel.add(lowStockButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		// Area for displaying results
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		
		add(new JScrollPane(outputArea), BorderLayout.CENTER);			
	}
}
