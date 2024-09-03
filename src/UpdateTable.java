import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTable {
    public static void main(String[] args)  {
        String query="update employee set ManagerId = ? where Empid = ? ;";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement psmt = conn.prepareStatement(query)){
            psmt.setInt(1,102);
            psmt.setInt(2,101);

            int rowsAffected = psmt.executeUpdate();
            if(rowsAffected>0){
                System.out.println("Update Successfully");
            }else{
                System.out.println("Update Failure");
            }
        }catch(SQLException exception){
            exception.printStackTrace();

        }
    }
}
