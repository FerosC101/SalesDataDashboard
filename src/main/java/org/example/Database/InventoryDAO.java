package org.example.Database;

import org.example.Models.Inventory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    // Add a new product to the inventory
    public void addProduct(Inventory product) {
        String sql = "INSERT INTO Inventory (product_name, stock_quantity, price) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getStockQuantity());
            statement.setDouble(3, product.getPrice());
            statement.executeUpdate();
            System.out.println("Product added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all products from the inventory
    public List<Inventory> getAllProducts() {
        List<Inventory> productList = new ArrayList<>();
        String sql = "SELECT * FROM Inventory";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Inventory product = new Inventory(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getInt("stock_quantity"),
                        resultSet.getDouble("price")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    // Update a product in the inventory
    public void updateProduct(Inventory product) {
        String sql = "UPDATE Inventory SET product_name = ?, stock_quantity = ?, price = ? WHERE product_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getStockQuantity());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getProductId());
            statement.executeUpdate();
            System.out.println("Product updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a product from the inventory
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM Inventory WHERE product_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            statement.executeUpdate();
            System.out.println("Product deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
