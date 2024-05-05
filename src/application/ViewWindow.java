package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewWindow {

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("View Items");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        // Create a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPadding(new Insets(10));
        scrollPane.setFitToWidth(true);  // Ensure the content width fits the scroll pane
        scrollPane.setContent(layout);  // Set VBox as the content of ScrollPane

        Connection connection = DatabaseConnection.connect();
        if (connection != null) {
            try {
                String sql = "SELECT * FROM items";
                
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                
                ResultSet resultSet = preparedStatement.executeQuery();
                
                while (resultSet.next()) {
                    String code = resultSet.getString("code");
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    int stock = resultSet.getInt("stock");
                    
                    Label itemLabel = new Label("Code: " + code + ", Name: " + name + ", Price: $" + price + ", Stock: " + stock);
                    layout.getChildren().add(itemLabel);
                }
                
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DatabaseConnection.close(connection);
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
        
        Scene scene = new Scene(scrollPane, 400, 300);  // Set the scene with ScrollPane as the root
        window.setScene(scene);
        window.showAndWait();
    }
}
