import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectData {
    public static void main(String[] args) {
        // Define the SQL query to select all records from the student table
        String query = "SELECT * FROM student;";

        // Establish a connection to the database, and prepare to execute the query
        try (Connection conn = DatabaseConnection.getConnection();
             // Create a PreparedStatement to execute the SQL query
             PreparedStatement psmt = conn.prepareStatement(query);
             // Execute the query and get the result set
             ResultSet rs = psmt.executeQuery()) {

            // Iterate over the result set
            while (rs.next()) {
                // Retrieve data from the current row
                int id = rs.getInt("student_id");  // Get the student ID
                String name = rs.getString("name"); // Get the student's name
                int age = rs.getInt("age");         // Get the student's age

                // Print the data to the console
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
                // Alternatively, use printf for formatted output
                System.out.printf("ID: %d, Name: %s, Age: %d%n", id, name, age);
            }

        } catch (SQLException exception) {
            // Handle SQL exceptions by printing the stack trace
            exception.printStackTrace();
        }
    }
}
