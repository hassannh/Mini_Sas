import java.sql.*;
import java.util.Scanner;
import DB.DatabaseConnection;
import Digital_Library.Book;
import Digital_Library.Menu;

public class Main {
    public static void main(String[] args) {
        try {

            DatabaseConnection DB = DatabaseConnection.getInstance();
            DB.Connect();


            Menu menu = new Menu();
            menu.start_menu();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}









