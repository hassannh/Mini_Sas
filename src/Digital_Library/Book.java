package Digital_Library;

import DB.DatabaseConnection;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import java.sql.*;

public class Book {

    private String ISBN;
    private BookStatus status;
    private String title;
    private String author;
    private List<Book> books;


    DatabaseConnection DB = DatabaseConnection.getInstance();



    public enum BookStatus {
        AVAILABLE,
        BORROWED,
        LOST
    }


    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }


    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }






    public void getBooks(){


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

                    String blueColor = "\u001b[94m";
                    String blueReset = "\u001b[0m";


                    System.out.println(blueColor + "===================================" + blueReset);
                    System.out.println(blueColor + "=======         BOOK          =====" + blueReset);
                    System.out.println(blueColor + "===================================" + blueReset);
                    System.out.println("ISBN: " + ISBN);
                    System.out.println(blueColor + "===================================" + blueReset);
                    System.out.println("status: " + status);
                    System.out.println(blueColor + "===================================" + blueReset);
                    System.out.println("Title: " + title);
                    System.out.println(blueColor + "===================================" + blueReset);
                    System.out.println("Author: " + author);
                    System.out.println(blueColor + "===================================" + blueReset);
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

        String Defultstatus = "AVALIBALE";

        System.out.println("Enter title:");
        String title = scanner.nextLine();

        System.out.println("Enter author:");
        String author = scanner.nextLine();

        BookStatus status = null;

        Connection connection = DB.Connect();

        if (connection != null) {
            try {

                Statement statement = connection.createStatement();


                String query = "INSERT INTO book (ISBN, status, title, author) " +
                        "VALUES ('" + ISBN + "', '" + Defultstatus + "', '" + title + "', '" + author + "')";

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

        System.out.println("Enter the title of the book you want to update:");
        String titleToUpdate = scanner.nextLine();


        Connection connection = DB.Connect();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();

                // Check if the book with the given title exists
                String checkQuery = "SELECT * FROM book WHERE title = '" + titleToUpdate + "'";
                ResultSet resultSet = statement.executeQuery(checkQuery);

                if (resultSet.next()) {
                    // The book exists, allow the user to update its attributes
                    Book book = new Book();
                    book.setTitle(resultSet.getString("title"));
                    book.setStatus(BookStatus.valueOf(resultSet.getString("status")));
                    book.setAuthor(resultSet.getString("author"));

                    System.out.println("Enter new status:");
                    String newStatus = scanner.nextLine();
                    book.setStatus(BookStatus.valueOf(newStatus));

                    System.out.println("Enter new title:");
                    String newTitle = scanner.nextLine();
                    book.setTitle(newTitle);

                    System.out.println("Enter new author:");
                    String newAuthor = scanner.nextLine();
                    book.setAuthor(newAuthor);

                    String updateQuery = "UPDATE book SET status = '" + book.getStatus() + "', " +
                            "title = '" + book.getTitle() + "', author = '" + book.getAuthor() + "' " +
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

        Connection connection = DB.Connect();

        if (connection != null) {
            try {

                Statement statement = connection.createStatement();

                // Check if the book with the given ISBN exists
                String checkQuery = "SELECT * FROM book WHERE ISBN = '" + isbnToDelete + "'";
                ResultSet resultSet = statement.executeQuery(checkQuery);

                if (resultSet.next()) {
                    // Check if there are related records in the Emprunt table
                    String relatedEmpruntQuery = "DELETE FROM Emprunt WHERE Book_ISBN = '" + isbnToDelete + "'";
                    int empruntRowsAffected = statement.executeUpdate(relatedEmpruntQuery);

                    // Delete the book after related Emprunt records have been deleted
                    if (empruntRowsAffected >= 0) {
                        String deleteQuery = "DELETE FROM book WHERE ISBN = '" + isbnToDelete + "'";
                        int bookRowsAffected = statement.executeUpdate(deleteQuery);

                        if (bookRowsAffected > 0) {
                            System.out.println("Book with ISBN " + isbnToDelete + " deleted successfully.");
                            connection.commit();  // Commit the transaction
                        } else {
                            System.out.println("Failed to delete the book with ISBN " + isbnToDelete + ".");
                            connection.rollback();  // Rollback the transaction
                        }
                    } else {
                        System.out.println("Failed to delete related Emprunt records.");
                        connection.rollback();  // Rollback the transaction
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


    public void searchBooksByAuthor(Scanner scanner) {
        // Prompt the user for the author to search for
        System.out.println("Enter the author's name to search for:");
        String authorToSearch = scanner.nextLine();

        // Connect to the database
        Connection connection = DB.Connect();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();

                String searchQuery = "SELECT * FROM book WHERE author LIKE '%" + authorToSearch + "%'";
                ResultSet resultSet = statement.executeQuery(searchQuery);

                if (resultSet.next()) {
                    System.out.println("Books by author '" + authorToSearch + "':");

                    do {
                        String isbn = resultSet.getString("isbn");
                        String title = resultSet.getString("title");
                        String author = resultSet.getString("author");
                        String status = resultSet.getString("status");

                        System.out.println("ISBN: " + isbn);
                        System.out.println("Title: " + title);
                        System.out.println("Author: " + author);
                        System.out.println("status:" + status);
                    } while (resultSet.next());
                } else {
                    System.out.println("No books found by author '" + authorToSearch + "'.");
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public int displayBookStatistics(String status) {
        Connection connection = DB.Connect();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();

                // Count the number of books in each status category
                String borrowedQuery = "SELECT COUNT(*) AS BorrowedCount FROM book WHERE status = ?";




                PreparedStatement preparedStatement = connection.prepareStatement(borrowedQuery);


                preparedStatement.setString(1, status);

                ResultSet resultSet = preparedStatement.executeQuery();


                if (resultSet.next()) {
                    int borrowedCount = resultSet.getInt("BorrowedCount");

            return borrowedCount;

                } else {
                    System.out.println("Failed to retrieve book statistics.");
                }

                // Close the result sets and statements
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }





    public void generateStatisticsFile(String status, String filename) {
        Connection connection = DB.Connect();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();

                // Count the number of books in each status category
                String borrowedQuery = "SELECT COUNT(*) AS BorrowedCount FROM book WHERE status = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(borrowedQuery);
                preparedStatement.setString(1, status);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int borrowedCount = resultSet.getInt("BorrowedCount");

                    // Create a PrintWriter to write to the specified file
                    try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                        writer.println("Status: " + status);
                        writer.println("Borrowed Count: " + borrowedCount);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Failed to retrieve book statistics.");
                }

                // Close the result sets and statements
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }




        }

}
