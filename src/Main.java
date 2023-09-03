import java.sql.Connection;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);



        databaseConnection DB = new databaseConnection();
        DB.Connect();



        String yellowColor = "\u001B[93m";
        // ANSI escape code to reset text color to default
        String resetColor = "\u001B[0m";

        System.out.println(yellowColor + "**********************************Welcome to the digital library***************************************" + resetColor);
        System.out.println(yellowColor + "*******************************************************************************************************" + resetColor);
        System.out.println(yellowColor + "*********************************************1 : Show Books *******************************************" + resetColor);
        System.out.println(yellowColor + "*********************************************2 : creat New Book***************************************" + resetColor);
        System.out.println(yellowColor + "*******************************************************************************************************" + resetColor);


        System.out.println("Enter your choice (1 or 2): ");
        int choice = scanner.nextInt();

        if (choice == 1){
            getBooks booksRetriever = new getBooks();
            booksRetriever.retrieveBooks();
        }if (choice == 2){
            create_book bookCreator = new create_book();
            bookCreator.insertBook("hassanlife", "hassannouhi");
        }

        scanner.close();

    }
}