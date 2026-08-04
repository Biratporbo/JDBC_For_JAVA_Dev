import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchUpdateExample {

    public static void main(String[] args) {
        updateSalariesInBatch();
    }

    public static void updateSalariesInBatch() {
        try (Connection con = MainDB.getConnection()) {

            if (con == null) {
                System.out.println("Database connection failed!");
                return;
            }

            String sql = "UPDATE users SET Salary = ? WHERE ID = ?";

            Object[][] updates = {
                    {50000.0, 131},
                    {55000.0, 132},
                    {41000.0, 133}
            };

            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                for (Object[] row : updates) {
                    ps.setDouble(1, (Double) row[0]);
                    ps.setInt(2, (Integer) row[1]);
                    ps.addBatch();
                }

                int[] rows = ps.executeBatch();
                con.commit();
                System.out.println("Batch update completed: " + rows.length + " rows updated");
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Batch update failed. Transaction rolled back.");
                e.printStackTrace();
            } finally {
                con.setAutoCommit(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
