import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertData {

    public static void main(String[] args) {

        try {

            // Get connection from MainDB
            Connection con = MainDB.getConnection();

            // SQL Insert Query
            String sql = "INSERT INTO users(ID, Name, Age, City, Salary, Gender) VALUES(?,?,?,?,?,?)";

            // Create PreparedStatement
            PreparedStatement ps = con.prepareStatement(sql);

            // Set Values
            ps.setInt(1, 126);
            ps.setString(2, "Ankit");
            ps.setInt(3, 24);
            ps.setString(4, "Kolkata");
            ps.setDouble(5, 38000);
            ps.setString(6, "Male");

            // Execute Query
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Record Inserted Successfully!");
            } else {
                System.out.println("Record Not Inserted!");
            }

            // Close Resources
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}