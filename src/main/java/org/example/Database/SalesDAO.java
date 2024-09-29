package org.example.Database;

import org.example.Models.Sales;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesDAO {

    // Add a new sale to the database
    public void addSale(Sales sale) {
        String sql = "INSERT INTO Sales (product_id, customer_id, quantity, total_amount) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sale.getProductId());
            statement.setInt(2, sale.getCustomerId());
            statement.setInt(3, sale.getQuantity());
            statement.setDouble(4, sale.getTotalAmount());
            statement.executeUpdate();
            System.out.println("Sale added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all sales from the database
    public List<Sales> getAllSales() {
        List<Sales> salesList = new ArrayList<>();
        String sql = "SELECT * FROM Sales";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Sales sale = new Sales(
                        resultSet.getInt("sale_id"),
                        resultSet.getInt("product_id"),
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getTimestamp("date"),
                        resultSet.getDouble("total_amount")
                );
                salesList.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesList;
    }

    // Update a sale in the database
    public void updateSale(Sales sale) {
        String sql = "UPDATE Sales SET product_id = ?, customer_id = ?, quantity = ?, total_amount = ? WHERE sale_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sale.getProductId());
            statement.setInt(2, sale.getCustomerId());
            statement.setInt(3, sale.getQuantity());
            statement.setDouble(4, sale.getTotalAmount());
            statement.setInt(5, sale.getSaleId());
            statement.executeUpdate();
            System.out.println("Sale updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a sale from the database
    public void deleteSale(int saleId) {
        String sql = "DELETE FROM Sales WHERE sale_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, saleId);
            statement.executeUpdate();
            System.out.println("Sale deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // Method to fetch sales by product name
    public Map<String, Double> getSalesByProductName() {
        Map<String, Double> salesByProduct = new HashMap<>();

        String GET_SALES_BY_PRODUCT_NAME_QUERY = "SELECT i.product_name, SUM(s.total_amount) as total_sales " +
                "FROM sales s " +
                "JOIN inventory i ON s.product_id = i.product_id " +
                "GROUP BY i.product_name";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_SALES_BY_PRODUCT_NAME_QUERY)) {

            while (resultSet.next()) {
                String productName = resultSet.getString("product_name");
                double totalSales = resultSet.getDouble("total_sales");

                // Add logging to check if data is being retrieved
                System.out.println("Product: " + productName + ", Sales: " + totalSales);

                salesByProduct.put(productName, totalSales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salesByProduct;
    }



}
