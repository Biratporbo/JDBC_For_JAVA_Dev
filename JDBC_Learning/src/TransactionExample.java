import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionExample {

    public static void main(String[] args) {

        try (Connection con = MainDB.getConnection()) {

            if (con == null) {
                System.out.println("Database connection failed!");
                return;
            }

            con.setAutoCommit(false);

            String sql = "INSERT INTO users(ID, Name, Age, City, Salary, Gender) VALUES(?,?,?,?,?,?)";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, 200);
                ps.setString(2, "Sarthak");
                ps.setInt(3, 29);
                ps.setString(4, "Jaipur");
                ps.setDouble(5, 61000.0);
                ps.setString(6, "Male");
                ps.executeUpdate();

                ps.setInt(1, 200);
                ps.setString(2, "Duplicate");
                ps.setInt(3, 31);
                ps.setString(4, "Hyderabad");
                ps.setDouble(5, 70000.0);
                ps.setString(6, "Other");
                ps.executeUpdate();
            }

            con.commit();
            System.out.println("Transaction committed successfully!");

        } catch (Exception e) {
            try {
                Connection con = MainDB.getConnection();
                if (con != null) {
                    con.rollback();
                    System.out.println("Transaction rolled back due to an error.");
                }
            } catch (Exception rollbackError) {
                rollbackError.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
