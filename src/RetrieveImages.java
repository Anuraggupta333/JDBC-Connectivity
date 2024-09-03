import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveImages {
    // Database URL, username, and password
//    private static final String URL = "jdbc:mysql://localhost:3306/yourdatabase";
//    private static final String USER = "yourusername";
//    private static final String PASSWORD = "yourpassword";

    public static void main(String[] args) throws SQLException {
        // SQL query to retrieve all images
        String query = "SELECT name, image FROM images";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query);
             ResultSet rs = psmt.executeQuery()) {

            while (rs.next()) {
                String imageName = rs.getString("name");
                byte[] imageBytes = rs.getBytes("image");

                if (imageBytes != null && imageName != null) {
                    saveImageToFile(imageBytes, imageName);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void saveImageToFile(byte[] imageBytes, String imageName) {
        File file = new File(imageName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(imageBytes);
            System.out.println("Saved image: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
