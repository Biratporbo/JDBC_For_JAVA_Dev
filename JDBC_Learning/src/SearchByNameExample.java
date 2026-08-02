import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SearchByNameExample {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (Connection con = MainDB.getConnection()) {

            if (con == null) {
                System.out.println("Database connection failed!");
                return;
            }

            System.out.print("Enter name keyword: ");
            String keyword = sc.nextLine();

            String sql = "SELECT * FROM users WHERE Name LIKE ? ORDER BY ID";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, "%" + keyword + "%");

                try (ResultSet rs = ps.executeQuery()) {
                    boolean found = false;

                    while (rs.next()) {
                        found = true;
                        System.out.println("ID: " + rs.getInt("ID") +
                                " | Name: " + rs.getString("Name") +
                                " | City: " + rs.getString("City") +
                                " | Salary: " + rs.getDouble("Salary"));
                    }

                    if (!found) {
                        System.out.println("No matching users found.");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
