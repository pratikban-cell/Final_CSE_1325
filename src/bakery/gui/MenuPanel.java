package bakery.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bakery.logic.Menu;
import bakery.model.Product;

public class MenuPanel extends JPanel {
   private Menu menu;
   private JTextField idField;
   private JTextField nameField;
   private JTextField priceField;
   private DefaultTableModel tableModel;
   private JTable table;

   public MenuPanel(Menu menu) {
      this.menu = menu;
      setLayout(new BorderLayout(8, 8));

      JPanel fields = new JPanel(
         new GridLayout(3, 2, 5, 5));

      idField = new JTextField();
      nameField = new JTextField();
      priceField = new JTextField();

      fields.add(new JLabel("Product ID:"));
      fields.add(idField);
      fields.add(new JLabel("Product Name:"));
      fields.add(nameField);
      fields.add(new JLabel("Price:"));
      fields.add(priceField);

      add(fields, BorderLayout.NORTH);

      tableModel = new DefaultTableModel(
         new String[] {"ID", "Name", "Price"}, 0) {
         public boolean isCellEditable(int row,
                                      int column) {
            return false;
         }
      };

      table = new JTable(tableModel);
      add(new JScrollPane(table), BorderLayout.CENTER);

      table.getSelectionModel().addListSelectionListener(
         event -> {
            if (!event.getValueIsAdjusting() &&
                table.getSelectedRow() >= 0) {

               int row = table.convertRowIndexToModel(
                  table.getSelectedRow());

               idField.setText(
                  tableModel.getValueAt(row, 0).toString());
               nameField.setText(
                  tableModel.getValueAt(row, 1).toString());
               priceField.setText(
                  tableModel.getValueAt(row, 2).toString());
            }
         });

      JPanel buttons = new JPanel(new FlowLayout());

      JButton addButton = new JButton("Add");
      JButton displayButton = new JButton("Display All");
      JButton searchButton = new JButton("Search");
      JButton modifyButton = new JButton("Modify");
      JButton deleteButton = new JButton("Delete");

      buttons.add(addButton);
      buttons.add(displayButton);
      buttons.add(searchButton);
      buttons.add(modifyButton);
      buttons.add(deleteButton);

      add(buttons, BorderLayout.SOUTH);

      addButton.addActionListener(event -> runAction(() -> {
         menu.addProduct(new Product(
            readId(), nameField.getText().trim(),
            readPrice()));
         refresh();
      }));

      displayButton.addActionListener(event -> refresh());

      searchButton.addActionListener(event -> runAction(() -> {
         if (!idField.getText().trim().isEmpty()) {
            Product product = menu.findProduct(readId());
            ArrayList<Product> results =
               new ArrayList<Product>();

            if (product != null) {
               results.add(product);
            }

            showProducts(results);
         } else {
            showProducts(
               menu.searchByName(nameField.getText()));
         }
      }));

      modifyButton.addActionListener(event -> runAction(() -> {
         if (!menu.modifyProduct(
             readId(), nameField.getText(), readPrice())) {
            throw new IllegalArgumentException(
               "Product not found.");
         }
         refresh();
      }));

      deleteButton.addActionListener(event -> runAction(() -> {
         if (!menu.deleteProduct(readId())) {
            throw new IllegalArgumentException(
               "Product not found.");
         }
         refresh();
      }));

      refresh();
   }

   private int readId() {
      return Integer.parseInt(idField.getText().trim());
   }

   private double readPrice() {
      return Double.parseDouble(
         priceField.getText().trim());
   }

   private void runAction(Runnable action) {
      try {
         action.run();
      } catch (IllegalArgumentException exception) {
         JOptionPane.showMessageDialog(
            this, exception.getMessage(),
            "Menu Error", JOptionPane.ERROR_MESSAGE);
      }
   }

   private void showProducts(ArrayList<Product> products) {
      tableModel.setRowCount(0);

      for (Product product : products) {
         tableModel.addRow(new Object[] {
            product.getId(),
            product.getName(),
            product.getPrice()
         });
      }
   }

   public void refresh() {
      showProducts(menu.getProducts());
   }
}
