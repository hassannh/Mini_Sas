package Digital_Library;

import DB.DatabaseConnection;

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



    public void create_Book(String ISBN, String status, String title, String author){


        //object
        DatabaseConnection DB = new DatabaseConnection();

        Connection connection = DB.Connect();




    }



}
