import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteRecords {
    public static void main(String[] args) {
        String query = "DELETE FROM student WHERE age >=21 ;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)){
             int rowsAffected = psmt.executeUpdate();
             System.out.println(rowsAffected + " rows deleted.");


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
