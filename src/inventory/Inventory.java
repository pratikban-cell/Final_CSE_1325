package inventory;

public class Inventory {
	
	private Ingredient[] ingredients;
	private int count;
	
	// Constructor
	public Inventory() {
		ingredients = new Ingredient[100];
		count = 0;
	}
	
	// Add a new ingredient 
	public void addIngredient(Ingredient ingredient) {
		
		if (ingredient == null || count >= ingredients.length) {
			return;
		}
		
		if (findIngredient(ingredient.getId()) == null) {
			ingredients[count] = ingredient;
			count++;
		}
	}
	
	// Find an ingredient by ID
	public Ingredient findIngredient(int ingredientId) {
		
		for (int i = 0; i < count; i++) {
			if (ingredients[i].getId() == ingredientId) {
				return ingredients[i];
			}
		}
		
		return null;
	}
	
	// Update an existing ingredient 
	public void updateIngredient(Ingredient updatedIngredient) {
		
		if (updatedIngredient == null) {
			return;
		}
		
		for (int i = 0; i < count; i++) {
			if (ingredients[i].getId() == updatedIngredient.getId()) {
				ingredients[i] = updatedIngredient;
				return;
			}
			
		}
	}
	
	// Delete an ingredient
	public void deleteIngredient(int ingredientId) {
		
		for (int i = 0; i < count; i++) {
			if (ingredients[i].getId() == ingredientId) {
				
				for (int j = i; j < count -1; j++) {
					ingredients[j] = ingredients[j + 1];
				}
				
				ingredients[count -1] = null;
				count--;
				return;
			}
		}
	}
	
	// Restock an ingredient
	public void restockIngredient(int ingredientId, int amount) {
		
		Ingredient ingredient = findIngredient(ingredientId);
		
		if (ingredient != null && amount > 0) {
			ingredient.addStock(amount);
		}
	}
	
	// Check ingredients with low stock
	public Ingredient[] checkLowStock() {
		
		int lowCount = 0;
		
		for (int i = 0; i < count; i++) {
			if (ingredients[i].isLowStock()) {
				lowCount++;
			}
		}
		
		Ingredient[] lowStock = new Ingredient[lowCount];
		int index = 0;
		
		for (int i = 0; i < count; i++) {
			if (ingredients[i].isLowStock()) {
				lowStock[index] = ingredients[i];
				index++;
			}
		}
		
		return lowStock;
	}	
}
