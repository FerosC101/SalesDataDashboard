package org.example.Database;

import org.example.Models.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    // Add a new customer to the database
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO Customers (name, email, phone, address) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getAddress());
            statement.executeUpdate();
            System.out.println("Customer added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Retrieve all customers from the database
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM Customers";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("address")
                );
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    // Update a customer in the database
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE Customers SET name = ?, email = ?, phone = ?, address = ? WHERE customer_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getAddress());
            statement.setInt(5, customer.getCustomerId());
            statement.executeUpdate();
            System.out.println("Customer updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a customer from the database
    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM Customers WHERE customer_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            statement.executeUpdate();
            System.out.println("Customer deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
