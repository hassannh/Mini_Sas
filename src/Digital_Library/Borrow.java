package Digital_Library;

import DB.DatabaseConnection;


import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Borrow {
    private String date_E;
    private String date_R;
    private Member Member_membership_num;
    private Book Book_ISBN;
    private DatabaseConnection DB = new DatabaseConnection();

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

    public void borrowBook(Member member, Book book) {
        // Set the borrow date to the current date
        LocalDate currentDate = LocalDate.now();
        this.setDate_E(currentDate.toString());

        // Calculate the return date (e.g., due in 14 days)
        LocalDate returnDate = currentDate.plusDays(14);
        this.setDate_R(returnDate.toString());

        // Set the member and book for this borrow instance
        this.setMember_membership_num(member);
        this.setBook_ISBN(book);

        // Update the book status to "BORROWED"
        book.setStatus(Book.BookStatus.BORROWED);


    }

    public void borrowBookWithInput(Scanner scanner) {
        // Ask the user to select a book to borrow
        System.out.print("Enter the number of the book you want to borrow: ");
        String bookChoice = scanner.next();
        scanner.nextLine();


        System.out.print("Enter the number of the book you want to borrow: ");
        String membership_num = scanner.next();
        scanner.nextLine();



        // Check if the book choice is valid


        // Assuming you have a list of members (members), get the member
        //System.out.print("Enter the membership number of the member: ");
        //String memberMembershipNum = scanner.nextLine();


        Member member = null;


        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        // Set the borrow date to the current date
        LocalDate currentDate = LocalDate.now();
        String Date_Emprunt = currentDate.toString();

        // Calculate the return date (e.g., due in 14 days)
        LocalDate returnDate = currentDate.plusDays(14);
        String Date_Retour = returnDate.toString();

        // Insert the borrowing information into the database
        Connection connection = DB.Connect();

        if (connection != null) {
            try {
                //Statement statement = connection.createStatement();

                String query = "INSERT INTO Emprunt (Date_Emprunt, Date_Retour, Book_ISBN, Mumber_membership_num) VALUES (?,?,?,?)";

                PreparedStatement preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, bookChoice);
                preparedStatement.setString(2, membership_num);
                preparedStatement.setDate(3, Date.valueOf(currentDate));
                preparedStatement.setDate(4, Date.valueOf(returnDate));

                // Execute query
                int rowsAffected = preparedStatement.executeUpdate(query);

                if (rowsAffected > 0) {
                    System.out.println("Book was borrowed successfully.");
                } else {
                    System.out.println("Failed to borrow the book.");
                }

                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
