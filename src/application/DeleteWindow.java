package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeleteWindow {

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Delete Item");
        
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        
        // Create a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true); // Make sure the scroll pane uses the full width of the VBox
        scrollPane.setPadding(new Insets(10));

        for (Item item : Item.getAllItems()) {
            GridPane itemGrid = new GridPane();
            itemGrid.setPadding(new Insets(10));
            itemGrid.setVgap(5);
            itemGrid.setHgap(10);
            
            Label codeLabel = new Label("Code:");
            Label codeValue = new Label(item.getCode());
            
            Label nameLabel = new Label("Name:");
            Label nameValue = new Label(item.getName());
            
            Label priceLabel = new Label("Price:");
            Label priceValue = new Label(String.valueOf(item.getPrice()));
            
            Label stockLabel = new Label("Stock:");
            Label stockValue = new Label(String.valueOf(item.getStock()));
            
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(e -> {
                Item.deleteItem(item.getCode());
                root.getChildren().remove(itemGrid); // Remove the grid from the UI after deletion
                System.out.println("Item deleted successfully.");
            });
            
            itemGrid.addRow(0, codeLabel, codeValue);
            itemGrid.addRow(1, nameLabel, nameValue);
            itemGrid.addRow(2, priceLabel, priceValue);
            itemGrid.addRow(3, stockLabel, stockValue);
            itemGrid.add(deleteButton, 1, 4);
            
            root.getChildren().add(itemGrid);
        }
        
        scrollPane.setContent(root); // Set the VBox inside the scroll pane

        Scene scene = new Scene(scrollPane, 400, 300); // Use scrollPane as the root for the scene
        window.setScene(scene);
        window.showAndWait();
    }

}
