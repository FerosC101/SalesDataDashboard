package org.example.ui;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;

public class FilterPane {

    public HBox getFilterPane() {
        HBox filterPane = new HBox(10); // Horizontal layout with spacing

        // Date picker for start and end date
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Start Date");

        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setPromptText("End Date");

        // Drop-down for product selection
        ComboBox<String> productComboBox = new ComboBox<>();
        productComboBox.getItems().addAll("All Products", "Product A", "Product B", "Product C");

        // Drop-down for customer selection
        ComboBox<String> customerComboBox = new ComboBox<>();
        customerComboBox.getItems().addAll("All Customers", "Customer 1", "Customer 2", "Customer 3");

        // Button to apply filters
        Button applyFilterButton = new Button("Apply Filter");

        // Add all filter controls to the pane
        filterPane.getChildren().addAll(startDatePicker, endDatePicker, productComboBox, customerComboBox, applyFilterButton);

        return filterPane;
    }
}
