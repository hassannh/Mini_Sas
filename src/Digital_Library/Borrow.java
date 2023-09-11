package Digital_Library;

import DB.DatabaseConnection;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Borrow {
    private String date_E;
    private String date_R;
    private Member Member_membership_num;
    private Book Book_ISBN;
    private DatabaseConnection DB = new DatabaseConnection();

    public Borrow(String Date_Emprunt, String Date_Retour, int Mumber_membership_num, String Book_ISBN) {
    }

    public Borrow() {

    }

    public String getdate_E() {
        return date_E;
    }

    public void setDate_E(String date_E) {
        this.date_E = date_E;
    }

    public String getDate_R() {
        return date_R;
    }

    public void setDate_R(String date_R) {
        this.date_R = date_R;
    }

    public Member getMember_membership_num() {
        return Member_membership_num;
    }

    public void setMember_membership_num(Member Member_membership_num) {
        this.Member_membership_num = Member_membership_num;
    }

    public Book getBook_ISBN() {
        return Book_ISBN;
    }

    public void setBook_ISBN(Book Book_ISBN) {
        this.Book_ISBN = Book_ISBN;
    }

    Member member;





    public void borrowBookWithInput(Scanner scanner) {



        // Ask the user to select a book to borrow
        System.out.print("Enter the book ISBN : ");
        String bookChoice = scanner.next();
        scanner.nextLine();


        System.out.print("Enter the number the membership number: ");
        String membership_num = scanner.next();
        scanner.nextLine();



        LocalDate currentDate = LocalDate.now();
        String Date_Emprunt = currentDate.toString();


        LocalDate returnDate = currentDate.plusDays(14);
        String Date_Retour = returnDate.toString();

        // Insert the borrowing information into the database
        Connection connection = DB.Connect();

        if (connection != null) {
            try {
                //Statement statement = connection.createStatement();

                String query = "INSERT INTO Emprunt (Date_Emprunt, Date_Retour, Book_ISBN, Mumber_membership_num) VALUES (?,?,?,?)";

                PreparedStatement preparedStatement = connection.prepareStatement(query);


                preparedStatement.setDate(1, Date.valueOf(currentDate));
                preparedStatement.setDate(2, Date.valueOf(returnDate));
                preparedStatement.setString(3, bookChoice);
                preparedStatement.setString(4, membership_num);

                // Execute query
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Book was borrowed successfully.");
                } else {
                    System.out.println("Failed to borrow the book.");
                }

                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.getMessage();

                System.out.println(e.getMessage());
            }
        }
    }


    String blueColor = "\u001b[94m";
    String blueReset = "\u001b[0m";

    public List<Borrow> getBorrowedBooks() {
        Connection connection = DB.Connect();
        List<Borrow> borrowedBooks = new ArrayList<>();

        if (connection != null) {
            try {
                // Create a SQL statement
                Statement statement = connection.createStatement();

                String query = "SELECT * FROM Emprunt";

                // Execute the query and retrieve the result set
                ResultSet resultSet = statement.executeQuery(query);

                // Process the result set (iterate through the rows)
                while (resultSet.next()) {
                    String dateE = resultSet.getString("Date_Emprunt");
                    String dateR = resultSet.getString("Date_Retour");
                    int memberId = resultSet.getInt("Mumber_membership_num");
                    String ISBN = resultSet.getString("Book_ISBN");

                    System.out.println(blueColor + "===================================" + blueReset);
                    System.out.println(blueColor + "========== Borrowed Books =========" + blueReset);

                    System.out.println(blueColor + "===================================" + blueReset);
                    System.out.println("Date_Emprunt: " + dateE);
                    System.out.println(blueColor + "===================================" + blueReset);
                    System.out.println("Date_Retour: " + dateR);
                    System.out.println(blueColor + "===================================" + blueReset);
                    System.out.println("Mumber_membership_num: " + memberId);
                    System.out.println(blueColor + "===================================" + blueReset);
                    System.out.println("Book_ISBN: " + ISBN);
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
        return borrowedBooks;
    }




}
