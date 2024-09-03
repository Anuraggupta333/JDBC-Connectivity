import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageRetrieveOperation {
    public static void main(String[] args) {
        // SQL query to retrieve the image
        String query = "SELECT image FROM images WHERE name = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {

            // Set the name of the image to retrieve
            psmt.setString(1, "appu");

            // Execute the query
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    // Retrieve the image as a byte array
                    byte[] imageBytes = rs.getBytes("image");

                    // Save the byte array to a file
                    String outputPath = "F:\\retrieved_image.jpg";
                    try (OutputStream outputStream = new FileOutputStream(outputPath)) {
                        outputStream.write(imageBytes);
                        System.out.println("Image retrieved and saved successfully");
                    } catch (Exception e) {
                        System.err.println("Error writing the image file: " + e.getMessage());
                    }
                } else {
                    System.out.println("No image found with the given name");
                }
            } catch (SQLException e) {
                System.err.println("SQL error: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
