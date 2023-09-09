package Digital_Library;
import DB.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;




public class Member implements services.Member {

    private String membership_num;
    private String fullName;
    private List <Borrow> borrows;

    DatabaseConnection DB = new DatabaseConnection();


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMembership_num() {
        return membership_num;
    }

    public void setMembership_num(String membership_num) {
        this.membership_num = membership_num;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }




    @Override
    public void addMember(Scanner scanner) {

            // Connect to the database
            Connection connection =  DB.Connect();

        System.out.println("Enter membership_num:");
        String membership_num = scanner.nextLine();

        System.out.println("Enter fullName:");
        String fullName = scanner.nextLine();

            if (connection != null) {
                try {
                    Statement statement = connection.createStatement();

                    // SQL query to insert a new member into the database
                    String query = "INSERT INTO mumber (membership_num, fullName) " +
                            "VALUES ('" + membership_num + "', '" + fullName + "')";

                    // Execute query
                    int rowsAffected = statement.executeUpdate(query);

                    if (rowsAffected > 0) {
                        System.out.println("Member added successfully.");
                    } else {
                        System.out.println("Failed to add the member.");
                    }

                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }



    @Override
    public void get_member(Scanner scanner) {
        // Connect to the database
        Connection connection = DB.Connect();

        System.out.println("Enter membership_num:");
        String membership_num = scanner.nextLine();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();

                // SQL query to retrieve member information by membership number
                String query = "SELECT fullName FROM mumber WHERE membership_num = '" + membership_num + "'";

                // Execute query
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    String fullName = ((ResultSet) resultSet).getString("fullName");
                    System.out.println("Full Name: " + fullName);
                } else {
                    System.out.println("Member not found.");
                }

                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void updateMember(Scanner scanner) {
        Connection connection = DB.Connect();

        System.out.println("Enter membership_num:");
        String membership_num = scanner.nextLine();

        System.out.println("Enter new fullName:");
        String newFullName = scanner.nextLine();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();

                // Check if the member with the given membership number exists
                String checkQuery = "SELECT * FROM member WHERE membership_num = '" + membership_num + "'";
                ResultSet resultSet = statement.executeQuery(checkQuery);

                if (resultSet.next()) {
                    // The member exists, allow the user to update the fullName
                    Member member = new Member();
                    member.setMembership_num(resultSet.getString("membership_num"));
                    member.setFullName(newFullName);

                    String updateQuery = "UPDATE member SET fullName = '" + member.getFullName() + "' " +
                            "WHERE membership_num = '" + membership_num + "'";

                    int rowsAffected = statement.executeUpdate(updateQuery);

                    if (rowsAffected > 0) {
                        System.out.println("Member updated successfully.");
                    } else {
                        System.out.println("Failed to update the member.");
                    }
                } else {
                    System.out.println("Member with membership_num " + membership_num + " does not exist.");
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void deleteMember(Scanner scanner) {

        Connection connection = DB.Connect();

        System.out.println("Enter membership_num of the member to delete:");
        String membership_numToDelete = scanner.nextLine();

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();

                // SQL query to delete a member based on membership_num
                String query = "DELETE FROM mumber WHERE membership_num = '" + membership_numToDelete + "'";

                // Execute query
                int rowsAffected = statement.executeUpdate(query);

                if (rowsAffected > 0) {
                    System.out.println("Member deleted successfully.");
                } else {
                    System.out.println("Failed to delete the member.");
                }

                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }



}
