import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class getBooks {
    public void retrieveBooks() {
        // Create an instance of the databaseConnection class
        databaseConnection DB = new databaseConnection();

        // Use the Connect method to establish a database connection
        Connection connection = DB.Connect();

        if (connection != null) {
            try {
                // Create a SQL statement
                Statement statement = connection.createStatement();

                // Define your SQL query
                String query = "SELECT * FROM books"; // Replace 'books' with your table name

                // Execute the query and retrieve the result set
                ResultSet resultSet = statement.executeQuery(query);

                // Process the result set (iterate through the rows)
                while (resultSet.next()) {
                    int bookId; // Replace with your actual column names
                    bookId = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");

                    // Process the data as needed (e.g., print it)
                    System.out.println("Book ID: " + bookId);
                    System.out.println("Title: " + title);
                    System.out.println("Author: " + author);
                    System.out.println();
                }

                // Close the resources
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
