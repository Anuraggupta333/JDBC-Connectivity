import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Exampledb";
    private static final String USER = "root";
    private static final String PASS = "Anurag@123";

    private static Connection connection;

    static {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Connection is " + connection);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found");
            e.printStackTrace();
        } catch (SQLException s) {
            System.out.println("Connection failed");
            s.printStackTrace();
        }
    }

    // Get the connection
    public static Connection getConnection() {
        return connection;
    }

    // Close the connection
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
