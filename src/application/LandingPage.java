package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class LandingPage extends Application {

    public static void showMenu(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            
            
            Button insertButton = new Button("Insert");
            styleButton(insertButton);
            Button viewButton = new Button("View");
            styleButton(viewButton);
            Button updateButton = new Button("Update");
            styleButton(updateButton);
            Button deleteButton = new Button("Delete");
            styleButton(deleteButton);
            
            VBox buttonBox = new VBox(10);
            buttonBox.setPadding(new Insets(20, 50, 20, 50));
            buttonBox.getChildren().addAll(insertButton, viewButton, updateButton, deleteButton);
            
            
            insertButton.setOnAction(e -> InsertWindow.display());
            viewButton.setOnAction(e -> ViewWindow.display());
            updateButton.setOnAction(e -> UpdateWindow.display());
            deleteButton.setOnAction(e -> DeleteWindow.display());
            
            
            Label header = new Label("Welcome to PT.Pudding Database");
            header.setFont(new Font("Arial", 24));
            root.setTop(header);
            BorderPane.setMargin(header, new Insets(20, 20, 20, 20));
            root.setCenter(buttonBox);
            
            Label footer = new Label("Select an option to proceed");
            footer.setFont(new Font("Arial", 14));
            root.setBottom(footer);
            BorderPane.setMargin(footer, new Insets(10, 20, 20, 20));
            
            Scene scene = new Scene(root, 400, 400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Database PT.Pudding");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void styleButton(Button button) {
        button.setFont(new Font("Arial", 16));
        button.setPadding(new Insets(10, 20, 10, 20));
        button.setStyle("-fx-background-color: #1e90ff; -fx-text-fill: white;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #6495ed; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #1e90ff; -fx-text-fill: white;"));
    }

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
