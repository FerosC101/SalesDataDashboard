package org.example.services;

import org.example.Database.CustomerDAO;
import org.example.Models.Customer;
import java.util.List;

public class CustomerServices {
    private CustomerDAO customerDAO = new CustomerDAO();

    // Get all customers from the database
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    // Get customer details by ID
    public Customer getCustomerById(int customerId) {
        List<Customer> customerList = customerDAO.getAllCustomers();
        for (Customer customer : customerList) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null; // Customer not found
    }

    // Add a new customer
    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }

    // Update an existing customer
    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    // Delete a customer by ID
    public void deleteCustomer(int customerId) {
        customerDAO.deleteCustomer(customerId);
    }
}
