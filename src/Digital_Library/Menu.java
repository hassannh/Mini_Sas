package Digital_Library;

import java.util.Scanner;

import static java.awt.SystemColor.menu;

public class Menu {


    public void start_menu(){


        Scanner scanner = new Scanner(System.in);

        String yellowColor = "\u001B[93m";
        String resetColor = "\u001B[0m";

        System.out.println(yellowColor + "**********************************Welcome to the digital library***************************************" + resetColor);
        System.out.println(yellowColor + "*******************************************************************************************************" + resetColor);
        System.out.println(yellowColor + "*********************************************1 : Show Books *******************************************" + resetColor);
        System.out.println(yellowColor + "*********************************************2 : Creat New Book ***************************************" + resetColor);
        System.out.println(yellowColor + "*********************************************3 : Update Book ******************************************" + resetColor);
        System.out.println(yellowColor + "*********************************************4 : Delete Book ******************************************" + resetColor);
        System.out.println(yellowColor + "*********************************************5 : Search For A Book *******************************************" + resetColor);
        System.out.println(yellowColor + "*********************************************6 : Update Mumber ****************************************" + resetColor);
        System.out.println(yellowColor + "*********************************************3 : Delete Mumber ****************************************" + resetColor);


        System.out.println("Enter your choice : ");
        int choice = scanner.nextInt();
        String add_book = scanner.nextLine();



        if (choice == 1) {
            Book book = new Book();
            book.getBooks();
            this.start_menu();
        }
        if (choice == 2) {
            Book book = new Book();
            book.create_Book(scanner);
            this.start_menu();
        }
        if (choice == 3){
            Book book = new Book();
            book.update_Book(scanner);
            this.start_menu();

        }  if (choice == 4){

            Book book = new Book();
            book.deleteBookByISBN(scanner);
            this.start_menu();

        }  if (choice == 5){

            Book book = new Book();
            book.searchBooksByStatus(scanner);
            this.start_menu();

        }  if (choice == 6){

            this.start_menu();
        }

        scanner.close();




    }


}
