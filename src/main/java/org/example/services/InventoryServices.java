package org.example.services;

import org.example.Database.InventoryDAO;
import org.example.Models.Inventory;
import java.util.List;

public class InventoryServices {
    private InventoryDAO inventoryDAO = new InventoryDAO();

    // Get all products from the inventory
    public List<Inventory> getAllProducts() {
        return inventoryDAO.getAllProducts();
    }

    // Get product details by ID
    public Inventory getProductById(int productId) {
        List<Inventory> productList = inventoryDAO.getAllProducts();
        for (Inventory product : productList) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null; // Product not found
    }

    // Add a new product to the inventory
    public void addProduct(Inventory product) {
        inventoryDAO.addProduct(product);
    }

    // Update an existing product in the inventory
    public void updateProduct(Inventory product) {
        inventoryDAO.updateProduct(product);
    }

    // Delete a product by ID
    public void deleteProduct(int productId) {
        inventoryDAO.deleteProduct(productId);
    }

    // Check if stock is available for a product
    public boolean isStockAvailable(int productId, int quantity) {
        Inventory product = getProductById(productId);
        if (product != null && product.getStockQuantity() >= quantity) {
            return true;
        }
        return false;
    }

    // Reduce stock after a sale
    public void reduceStock(int productId, int quantity) {
        Inventory product = getProductById(productId);
        if (product != null && product.getStockQuantity() >= quantity) {
            product.setStockQuantity(product.getStockQuantity() - quantity);
            inventoryDAO.updateProduct(product);
        }
    }
}
