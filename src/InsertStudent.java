import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertStudent {
    public static void main(String[] args) {
        String query = "INSERT INTO student (name, age) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement psmt = null;
        Scanner scanner = new Scanner(System.in);

        try {
            conn = DatabaseConnection.getConnection();
            psmt = conn.prepareStatement(query);
            conn.setAutoCommit(false);

            boolean moreRecords = true;
            System.out.println("Insert Students");
            System.out.println();

            while (moreRecords) {
                System.out.println("Enter student name:");
                String name = scanner.next();
                System.out.println("Enter age:");
                int age = scanner.nextInt();

                psmt.setString(1, name);
                psmt.setInt(2, age);
                psmt.addBatch();

                System.out.print("Do you want to enter another record? (yes/no): ");
                String response = scanner.next();
                moreRecords = response.equalsIgnoreCase("yes");
            }

            int[] result = psmt.executeBatch();
            conn.commit();
            System.out.println(result.length + " student(s) inserted successfully");

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            if (psmt != null) {
                try {
                    psmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                DatabaseConnection.closeConnection(conn);
            }
            scanner.close();
        }
    }
}
