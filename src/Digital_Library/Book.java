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
                    System.out.println("status: " + status);
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



    public void update_Book(Scanner scanner) {
        // Prompt the user for the name of the book
        System.out.println("Enter the title of the book you want to update:");
        String titleToUpdate = scanner.nextLine();

        DatabaseConnection DB = new DatabaseConnection();

        // Connect to the database
        Connection connection = DB.Connect();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();

                // Check if the book with the given title exists
                String checkQuery = "SELECT * FROM book WHERE title = '" + titleToUpdate + "'";
                ResultSet resultSet = statement.executeQuery(checkQuery);

                if (resultSet.next()) {
                    // The book exists, allow the user to update its attributes
                    System.out.println("Enter new status:");
                    String newStatus = scanner.nextLine();

                    System.out.println("Enter new title:");
                    String newTitle = scanner.nextLine();

                    System.out.println("Enter new author:");
                    String newAuthor = scanner.nextLine();


                    String updateQuery = "UPDATE book SET status = '" + newStatus + "', " +
                            "title = '" + newTitle + "', author = '" + newAuthor + "' " +
                            "WHERE title = '" + titleToUpdate + "'";

                    int rowsAffected = statement.executeUpdate(updateQuery);

                    if (rowsAffected > 0) {
                        System.out.println("Book updated successfully.");
                    } else {
                        System.out.println("Failed to update the book.");
                    }
                } else {
                    System.out.println("Book with title " + titleToUpdate + " does not exist.");
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public void deleteBookByISBN(Scanner scanner) {

        System.out.println("Enter the ISBN of the book you want to delete:");
        String isbnToDelete = scanner.nextLine();

        DatabaseConnection DB = new DatabaseConnection();


        Connection connection = DB.Connect();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();

                // Check if the book with the given ISBN exists
                String checkQuery = "SELECT * FROM book WHERE isbn = '" + isbnToDelete + "'";
                ResultSet resultSet = statement.executeQuery(checkQuery);

                if (resultSet.next()) {

                    String deleteQuery = "DELETE FROM book WHERE isbn = '" + isbnToDelete + "'";

                    int rowsAffected = statement.executeUpdate(deleteQuery);

                    if (rowsAffected > 0) {
                        System.out.println("Book with ISBN " + isbnToDelete + " deleted successfully.");
                    } else {
                        System.out.println("Failed to delete the book with ISBN " + isbnToDelete + ".");
                    }
                } else {
                    System.out.println("Book with ISBN " + isbnToDelete + " does not exist.");
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public void searchBooksByStatus(Scanner scanner) {
        // Prompt the user for the status to search for
        System.out.println("Enter the status to search for:");
        String statusToSearch = scanner.nextLine();

        DatabaseConnection DB = new DatabaseConnection();

        // Connect to the database
        Connection connection = DB.Connect();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();


                String searchQuery = "SELECT * FROM book WHERE status = '" + statusToSearch + "'";
                ResultSet resultSet = statement.executeQuery(searchQuery);

                if (resultSet.next()) {

                    System.out.println("Books with status '" + statusToSearch + "':");

                    do {
                        String isbn = resultSet.getString("isbn");
                        String title = resultSet.getString("title");
                        String author = resultSet.getString("author");

                        System.out.println("ISBN: " + isbn);
                        System.out.println("Title: " + title);
                        System.out.println("Author: " + author);
                        System.out.println();
                    } while (resultSet.next());
                } else {
                    System.out.println("No books found with status '" + statusToSearch + "'.");
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
