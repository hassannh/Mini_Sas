package Digital_Library;
import DB.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;




public class Member implements services.Member {

    private String membership_num;
    private String fullName;

    DatabaseConnection DB = new DatabaseConnection();



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


                String query = "UPDATE mumber SET fullName = '" + newFullName + "' WHERE membership_num = '" + membership_num + "'";


                int rowsAffected = statement.executeUpdate(query);

                if (rowsAffected > 0) {
                    System.out.println("Member updated successfully.");
                } else {
                    System.out.println("Failed to update the member.");
                }

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
