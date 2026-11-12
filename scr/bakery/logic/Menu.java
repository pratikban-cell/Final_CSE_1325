package bakery.logic;

import java.util.ArrayList;
import bakery.model.Product;

public class Menu {
   private ArrayList<Product> products;

   public Menu() {
      products = new ArrayList<Product>();
   }

   // FR-1: Add a product
   public void addProduct(Product product) {
      if (product == null) {
         throw new IllegalArgumentException(
            "Invalid product.");
      }

      if (findProduct(product.getId()) != null) {
         throw new IllegalArgumentException(
            "Product already exists.");
      }

      products.add(product);
   }

   // FR-2: Display all products
   public ArrayList<Product> getProducts() {
      return new ArrayList<Product>(products);
   }

   // FR-3: Search by ID
   public Product findProduct(int id) {
      for (Product product : products) {
         if (product.getId() == id) {
            return product;
         }
      }

      return null;
   }

   // FR-3: Search by name
   public ArrayList<Product> searchByName(String name) {
      ArrayList<Product> results = new ArrayList<Product>();

      if (name == null) {
         return results;
      }

      for (Product product : products) {
         if (product.getName().toLowerCase()
             .contains(name.toLowerCase().trim())) {
            results.add(product);
         }
      }

      return results;
   }

   // FR-4: Modify product
   public boolean modifyProduct(int id, String name,
                                double price) {
      Product product = findProduct(id);

      if (product == null) {
         return false;
      }

      if (name == null || name.trim().isEmpty() ||
          !Double.isFinite(price) || price < 0) {
         throw new IllegalArgumentException(
            "Invalid product information.");
      }

      product.setName(name.trim());
      product.setPrice(price);

      return true;
   }

   // FR-5: Delete product
   public boolean deleteProduct(int id) {
      Product product = findProduct(id);

      if (product == null) {
         return false;
      }

      return products.remove(product);
   }
}
