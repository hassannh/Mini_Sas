import java.sql.*;
import java.util.Scanner;
import DB.DatabaseConnection;
import Digital_Library.Book;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in); // Define the 'scanner' variable here

            DatabaseConnection DB = new DatabaseConnection();
            DB.Connect();

            //colors
            String yellowColor = "\u001B[93m";
            String resetColor = "\u001B[0m";


            System.out.println(yellowColor + "**********************************Welcome to the digital library***************************************" + resetColor);
            System.out.println(yellowColor + "*******************************************************************************************************" + resetColor);
            System.out.println(yellowColor + "*********************************************1 : Show Books *******************************************" + resetColor);
            System.out.println(yellowColor + "*********************************************2 : creat New Book***************************************" + resetColor);
            System.out.println(yellowColor + "*******************************************************************************************************" + resetColor);

            System.out.println("Enter your choice (1 or 2): ");
            int choice = scanner.nextInt();
            String add_book = scanner.nextLine();


            if (choice == 1) {
                Book book = new Book();
                book.getBooks();
            }
            if (choice == 2) {
                Book book = new Book();
                book.create_Book(scanner);
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}









