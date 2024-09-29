package org.example.services;

import org.example.Database.SalesDAO;
import org.example.Models.Sales;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesServices {
    private static final SalesDAO salesDAO = new SalesDAO();

    public static Map<String, Double> getSalesByCategory() {
        return Map.of();
    }

    public Map<String, Double> getSalesByProductName() {
        return salesDAO.getSalesByProductName();
    }

    // Get all sales from the database
    public List<Sales> getAllSales() {
        return salesDAO.getAllSales();
    }

    // Get total sales amount
    public double getTotalSales() {
        List<Sales> salesList = salesDAO.getAllSales();
        double totalSales = 0.0;
        for (Sales sale : salesList) {
            totalSales += sale.getTotalAmount();
        }
        return totalSales;
    }

    // Get total sales by customer
    public double getSalesByCustomer(int customerId) {
        List<Sales> salesList = salesDAO.getAllSales();
        double totalSales = 0.0;
        for (Sales sale : salesList) {
            if (sale.getCustomerId() == customerId) {
                totalSales += sale.getTotalAmount();
            }
        }
        return totalSales;
    }

    // Get total sales by product
    // Get total sales by product (returns a Map)
    public static Map<Integer, Double> getSalesByProduct() {
        List<Sales> salesList = salesDAO.getAllSales();
        Map<Integer, Double> salesByProduct = new HashMap<>();

        // Group sales by product
        for (Sales sale : salesList) {
            int productId = sale.getProductId();
            double totalAmount = sale.getTotalAmount();

            // If the product already exists in the map, add to its total
            salesByProduct.put(productId, salesByProduct.getOrDefault(productId, 0.0) + totalAmount);
        }

        return salesByProduct;
    }


    public static Map<Timestamp, Double> getSalesTrends() {
        List<Sales> salesList = salesDAO.getAllSales();
        Map<Timestamp, Double> salesTrends = new HashMap<>();

        for (Sales sale : salesList) {
            Timestamp saleDate = sale.getDate();
            double totalAmount = sale.getTotalAmount();

            // If the date already exists in the map, add the sale to the existing total
            salesTrends.put(saleDate, salesTrends.getOrDefault(saleDate, 0.0) + totalAmount);
        }

        return salesTrends;
    }

    // Add a new sale
    public void addSale(Sales sale) {
        salesDAO.addSale(sale);
    }

    // Update an existing sale
    public void updateSale(Sales sale) {
        salesDAO.updateSale(sale);
    }

    // Delete a sale by ID
    public void deleteSale(int saleId) {
        salesDAO.deleteSale(saleId);
    }
}
