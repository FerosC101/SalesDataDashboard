package org.example.Models;

import java.sql.Timestamp;

public class Sales {
    private int saleId;
    private int productId;
    private int customerId;
    private int quantity;
    private Timestamp date;
    private double totalAmount;

    public Sales(int saleId, int productId, int customerId, int quantity, Timestamp date, double totalAmount) {
        this.saleId = saleId;
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }


}
