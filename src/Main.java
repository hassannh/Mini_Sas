import java.sql.*;
import java.util.Scanner;
import DB.DatabaseConnection;
import Digital_Library.Book;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {


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

            if (choice == 1) {
                Book book = new Book();
                book.getBooks();

            }
            if (choice == 2) {

            }
            ;

            scanner.close();
        }

    }
}