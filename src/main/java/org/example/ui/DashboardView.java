package org.example.ui;

import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import org.example.Models.Sales;
import org.example.services.SalesServices;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class DashboardView {

    private SalesServices salesService = new SalesServices();

    public VBox getView() {
        VBox dashboardLayout = new VBox(20); // Layout

        // Add charts to the dashboard
        dashboardLayout.getChildren().addAll(
                createSalesBarChart(),
                createSalesLineChart(),
                createSalesPieChart()
        );

        return dashboardLayout;
    }

    // Create a bar chart for sales data
    private BarChart<String, Number> createSalesBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Product");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Total Sales");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Sales by Product");

        // Data series for the chart
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Sales");

        // ERROR SA PRODUCT ID AYAW MAG REPLACE SA NAME
        List<Sales> salesList = salesService.getAllSales();
        for (Sales sale : salesList) {
            dataSeries.getData().add(new XYChart.Data<>("Product " + sale.getProductId(), sale.getTotalAmount()));
        }

        barChart.getData().add(dataSeries);
        return barChart;
    }

    // line chart for sales trends
    private LineChart<String, Number> createSalesLineChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Total Sales");

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Sales Trends");

        // Data series for the chart
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Sales Over Time");

        // Format timestamp to display date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Fetch real sales data
        Map<Timestamp, Double> salesByDate = SalesServices.getSalesTrends();
        for (Map.Entry<Timestamp, Double> entry : salesByDate.entrySet()) {
            String formattedDate = dateFormat.format(entry.getKey());  // Format the timestamp
            dataSeries.getData().add(new XYChart.Data<>(formattedDate, entry.getValue()));
        }

        lineChart.getData().add(dataSeries);
        return lineChart;
    }

    // pie chart for sales by category or product
    private PieChart createSalesPieChart() {
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Sales Distribution");

        // Example data (replace with real data from SalesService)
        Map<String, Double> salesByProduct = salesService.getSalesByProductName();
        for (Map.Entry<String, Double> entry : salesByProduct.entrySet()) {
            pieChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        return pieChart;
    }

    public SalesServices getSalesService() {
        return salesService;
    }

    public void setSalesService(SalesServices salesService) {
        this.salesService = salesService;
    }
}
