package org.example;

import org.example.Database.DatabaseConnection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            insertCustomerData(conn);
            insertInventoryData(conn);
            insertSalesData(conn);
        }
    }

    private static void insertCustomerData(Connection conn) {
        String sql = "INSERT INTO customers (name, email, phone, address) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "John Doe");
            stmt.setString(2, "john.doe@example.com");
            stmt.setString(3, "555-1234");
            stmt.setString(4, "123 Elm Street");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertInventoryData(Connection conn) {
        String sql = "INSERT INTO inventory (product_name, stock_quantity, price) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "Laptop");
            stmt.setInt(2, 10);
            stmt.setDouble(3, 1200.00);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertSalesData(Connection conn) {
        String sql = "INSERT INTO sales (product_id, customer_id, quantity, total_amount) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, 1); // product_id
            stmt.setInt(2, 1); // customer_id
            stmt.setInt(3, 1); // quantity
            stmt.setDouble(4, 1200.00); // total_amount
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}