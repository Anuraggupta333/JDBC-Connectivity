import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) {
        // SQL query to create the Employee table
        String query = "CREATE TABLE Employee ("
                + "Empid INT PRIMARY KEY, "
                + "EmpName VARCHAR(100), "
                + "ManagerId INT);";

        // Use try-with-resources for automatic resource management
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Execute the query
            stmt.execute(query);
            System.out.println("Table created successfully");

        } catch (SQLException s) {
            // Handle SQL exceptions
            s.printStackTrace();
            System.out.println("Error creating table");
        }
    }
}
