import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertEmployees {


    public static void main(String[] args) {
        // SQL query for inserting employee data
        String insertQuery = "INSERT INTO Employee (Empid, EmpName, ManagerId) VALUES (?, ?, ?)";

        // Create a Scanner object to read input from user
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            // Disable auto-commit mode
            conn.setAutoCommit(false);

            boolean moreRecords = true;

            while (moreRecords) {
                // Take user input for employee details
                System.out.print("Enter Employee ID: ");
                int empId = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
                System.out.print("Enter Employee Name: ");
                String empName = scanner.nextLine();
                System.out.print("Enter Manager ID: ");
                int managerId = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                // Set parameters and add to batch
                pstmt.setInt(1, empId);
                pstmt.setString(2, empName);
                pstmt.setInt(3, managerId);
                pstmt.addBatch();

                // Ask if the user wants to enter more records
                System.out.print("Do you want to enter another record? (yes/no): ");
                String response = scanner.nextLine();
                moreRecords = response.equalsIgnoreCase("yes");
            }

            // Execute the batch of insert operations
            pstmt.executeBatch();
            conn.commit(); // Commit the transaction

            System.out.println("Employees inserted successfully");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error inserting employees");
            // Handle rollback in case of exception
            try {
                Connection conn = DatabaseConnection.getConnection();
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            scanner.close(); // Close the scanner
        }
    }
}
