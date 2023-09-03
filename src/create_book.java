import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class create_book {
    public void insertBook(String title, String author) {
        // Create an instance of the databaseConnection class
        databaseConnection DB = new databaseConnection();

        // Use the Connect method to establish a database connection
        Connection connection = DB.Connect();

        if (connection != null) {
            try {
                // Define your SQL INSERT statement
                String insertSQL = "INSERT INTO books (title, author) VALUES (`title`, `author`)";

                // Create a PreparedStatement for the INSERT statement
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);



                // Execute the INSERT statement
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Book inserted successfully.");
                } else {
                    System.out.println("Failed to insert the book.");
                }

                // Close the resources
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}

