package DB;


// reading configuration properties.
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class DatabaseConnection {





    public static String driver;
    public static String url;
    public static String username;
    public static String password;
    public static DatabaseConnection DB_instance = null;




    //constracter
    public DatabaseConnection(){

    }

    public static  DatabaseConnection getInstance()
    {
        if (DB_instance == null)
            DB_instance = new DatabaseConnection();

        return DB_instance;
    }



    static {
    // JDBC URL, username, and password of MySQL server

        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("DB.properties")) {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }



        driver = prop.getProperty("db.driver");
        url = prop.getProperty("db.url");
        username = prop.getProperty("db.username");
        password = prop.getProperty("db.password");
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
            System.out.println("connectedd db");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connectiong;
    }


}
