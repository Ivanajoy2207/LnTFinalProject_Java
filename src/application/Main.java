package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	LandingPage lp = new LandingPage();
	
	public void start(Stage primaryStage) {
		DatabaseConnection.connect();
		
		insertDummyData();
		
		try {
			lp.showMenu(primaryStage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
//		database name = javafxcrud
//		table name = items
//		table contain = code, name, price, stock
	}
	
	private void insertDummyData() {
		insertData("ABC123", "Item1", 10.99, 100);
		insertData("DEF456", "Item2", 20.50, 50);
	}
	
	public void insertData(String code, String name, double price, int stock) {
		if(!itemExists(code)) {
			Connection connection = DatabaseConnection.connect();
			if(connection != null) {
				try {
					String sql = "INSERT INTO items (code, name, price, stock) VALUES (?, ?, ?, ?)";
					
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, code);
					preparedStatement.setString(2, name);
					preparedStatement.setDouble(3, price);
					preparedStatement.setInt(4, stock);
					
					int rowsAffected = preparedStatement.executeUpdate();
					
					if(rowsAffected > 0) {
						System.out.println("Data inserted succesfully");
					}else {
						System.out.println("Failed to insert data.");
					}
					
					preparedStatement.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}finally {
					DatabaseConnection.close(connection);
				}
			}else {
				System.out.println("Failed to connect to the database.");
			}
		}
	}
	
    private boolean itemExists(String code) {
        String sql = "SELECT 1 FROM items WHERE code = ?";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, code);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();  // Return true if there is at least one row, meaning the item exists
            }
        } catch (SQLException e) {
            System.out.println("Error checking item existence: " + e.getMessage());
            return false;
        }
    }
}
