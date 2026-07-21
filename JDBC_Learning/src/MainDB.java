import java.sql.Connection;
import java.sql.DriverManager;

public class MainDB {

    // Method to create and return a database connection
    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb",
                    "root",
                    "Birat2026"
            );

            return con;

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }

    public static void main(String[] args) {

        Connection con = getConnection();

        if (con != null) {
            System.out.println("Connected Successfully!");
            System.out.println(con);

            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}