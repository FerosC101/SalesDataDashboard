package org.example.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SalesDashboardApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sales Data Dashboard");

        // Main layout of the application (can be BorderPane, VBox, etc.)
        BorderPane rootLayout = new BorderPane();

        // Set the dashboard view in the center
        DashboardView dashboardView = new DashboardView();
        rootLayout.setCenter(dashboardView.getView());

        // Create a scene and attach it to the stage
        Scene scene = new Scene(rootLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}