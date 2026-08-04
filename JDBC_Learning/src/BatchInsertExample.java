import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchInsertExample {

    public static void main(String[] args) {
        insertUsersInBatch();
    }

    public static void insertUsersInBatch() {
        try (Connection con = MainDB.getConnection()) {

            if (con == null) {
                System.out.println("Database connection failed!");
                return;
            }

            String sql = "INSERT INTO users(ID, Name, Age, City, Salary, Gender) VALUES(?,?,?,?,?,?)";

            Object[][] records = {
                    {131, "Riya", 27, "Pune", 45000.0, "Female"},
                    {132, "Nikhil", 30, "Mumbai", 52000.0, "Male"},
                    {133, "Meera", 22, "Chennai", 39000.0, "Female"}
            };

            con.setAutoCommit(false);

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                for (Object[] row : records) {
                    ps.setInt(1, (Integer) row[0]);
                    ps.setString(2, (String) row[1]);
                    ps.setInt(3, (Integer) row[2]);
                    ps.setString(4, (String) row[3]);
                    ps.setDouble(5, (Double) row[4]);
                    ps.setString(6, (String) row[5]);
                    ps.addBatch();
                }

                int[] rows = ps.executeBatch();
                con.commit();
                System.out.println("Batch insert completed: " + rows.length + " rows added");
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Batch insert failed. Transaction rolled back.");
                e.printStackTrace();
            } finally {
                con.setAutoCommit(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
