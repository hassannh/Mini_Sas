package Digital_Library;

import DB.DatabaseConnection;
import java.util.Scanner;

import java.sql.*;

public class Book {

    private String ISBN;
    private String status;
    private String title;
    private String author;


    public void getBooks(){



        DatabaseConnection DB = new DatabaseConnection();

        Connection connection = DB.Connect();

        if (connection != null) {
            try {
                // Create a SQL statement
                Statement statement = connection.createStatement();


                String query = "SELECT * FROM book ";

                // Execute the query and retrieve the result set
                ResultSet resultSet = statement.executeQuery(query);

                // Process the result set (iterate through the rows)
                while (resultSet.next()) {
                    String ISBN;
                    ISBN = resultSet.getString("ISBN");
                    String status = resultSet.getString("status");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");

                    // Process the data as needed (e.g., print it)
                    System.out.println("ISBN: " + ISBN);
                    System.out.println("status" + status);
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
    }

}





    public void create_Book(Scanner scanner) {
        // Prompt the user for book details
        System.out.println("Enter ISBN:");
        String ISBN = scanner.nextLine();

        System.out.println("Enter status:");
        String status = scanner.nextLine();

        System.out.println("Enter title:");
        String title = scanner.nextLine();

        System.out.println("Enter author:");
        String author = scanner.nextLine();


        DatabaseConnection DB = new DatabaseConnection();

        // Connect to the database
        Connection connection = DB.Connect();

        if (connection != null) {
            try {

                Statement statement = connection.createStatement();


                String query = "INSERT INTO book (ISBN, status, title, author) " +
                        "VALUES ('" + ISBN + "', '" + status + "', '" + title + "', '" + author + "')";

                // Execute query
                int rowsAffected = statement.executeUpdate(query);

                if (rowsAffected > 0) {
                    System.out.println("Book created successfully.");
                } else {
                    System.out.println("Failed to create the book.");
                }


                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
