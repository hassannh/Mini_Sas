import java.sql.*;
import java.util.ResourceBundle;

public class databaseConnection {
    public static String driver;
    public static String url;
    public static String username;
    public static String password;



    static {
    // JDBC URL, username, and password of MySQL server
        ResourceBundle get_database_varibales = ResourceBundle.getBundle("DB");

        driver = get_database_varibales.getString("db.driver");
        url = get_database_varibales.getString("db.url");
        username = get_database_varibales.getString("db.username");
        password = get_database_varibales.getString("db.password");

}

    public Connection Connect(){
        Connection connectiong = null;
        try {
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            connectiong = DriverManager.getConnection(url , username , password);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connectiong;
    }


}
