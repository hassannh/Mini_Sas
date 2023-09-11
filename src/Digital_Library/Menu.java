package Digital_Library;

import java.util.Scanner;


import static java.awt.SystemColor.menu;

public class Menu {


    public void start_menu() {
        Scanner scanner = new Scanner(System.in);

        String yellowColor = "\u001B[93m";
        String blueColor = "\u001b[94m";
        String blueReset = "\u001b[0m";
        String resetColor = "\u001B[0m";

        Book book;
        Member member;

        while (true) {
            System.out.println(blueColor + "********************************************************************************************************" + blueReset);
            System.out.println(blueColor + "************************************     Welcome to the digital library     ****************************" + blueReset);
            System.out.println(blueColor + "********************************************************************************************************" + blueReset);

            System.out.println(yellowColor + "********************************************* 1 : Show Books *******************************************" + resetColor);
            System.out.println(yellowColor + "********************************************* 2 : Creat New Book ***************************************" + resetColor);
            System.out.println(yellowColor + "********************************************* 3 : Update Book ******************************************" + resetColor);
            System.out.println(yellowColor + "********************************************* 4 : Delete Book ******************************************" + resetColor);
            System.out.println(yellowColor + "********************************************* 5 : Search For A Book by status **************************" + resetColor);
            System.out.println(yellowColor + "********************************************* 6 : searchBooksByAuthor  *********************************" + resetColor);
            System.out.println(yellowColor + "********************************************* 7 : Creat new Member *************************************" + resetColor);
            System.out.println(yellowColor + "********************************************* 8 : Get Member By Membership_num *************************" + resetColor);
            System.out.println(yellowColor + "********************************************* 9 : Update Member ****************************************" + resetColor);
            System.out.println(yellowColor + "********************************************* 10 : Delete Member ***************************************" + resetColor);
            System.out.println(yellowColor + "********************************************* 11 : Borrow Book ***************************************" + resetColor);

            System.out.print("Enter your choice : ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        book = new Book();
                        book.getBooks();
                        break;
                    case 2:
                        book = new Book();
                        book.create_Book(scanner);
                        break;
                    case 3:
                        book = new Book();
                        book.update_Book(scanner);
                        break;
                    case 4:
                        book = new Book();
                        book.deleteBookByISBN(scanner);
                        break;
                    case 5:
                        book = new Book();
                        book.searchBooksByStatus(scanner);
                        break;
                    case 6:
                        book = new Book();
                        book.searchBooksByAuthor(scanner);
                        break;
                    case 7:
                        member = new Member();
                        member.addMember(scanner);
                        break;
                    case 8:
                        member = new Member();
                        member.get_member(scanner);
                        break;
                    case 9:
                        member = new Member();
                        member.updateMember(scanner);
                        break;
                    case 10:
                        member = new Member();
                        member.deleteMember(scanner);
                        break;
                    case 11:
                        Borrow borrow = new Borrow();
                        borrow.borrowBookWithInput(scanner);
                        break;
                    case 12:
                        Borrow borroww = new Borrow();
                        borroww.getBorrowedBooks();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        continue;
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
                continue;
            }

            System.out.println("Do you want to continue? (yes/no): ");
            String continueChoice = scanner.nextLine();
            if (!continueChoice.equalsIgnoreCase("yes")) {
                break;
            }
        }

        scanner.close();
    }


}
