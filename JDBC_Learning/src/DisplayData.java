import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DisplayData
{
    public static void main(String[] args)
    {
        try{

            //Get Connection
            Connection con = MainDB.getConnection();

            //Create Statement
            Statement stmt = con.createStatement();

            //Execute SELECT query
            String sql = "SELECT * FROM users";

            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("---------------------------------------------------------------");
            System.out.printf("%-5s %-15s %-5s %-15s %-10s %-10s%n",
                    "ID", "Name", "Age", "City", "Salary", "Gender");
            System.out.println("---------------------------------------------------------------");

            //Display records
            while (rs.next())
            {

                System.out.printf("%-5d %-15s %-5d %-15s %-10.2f %-10s%n",
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("City"),
                        rs.getDouble("Salary"),
                        rs.getString("Gender")
                );
            }

            System.out.println("---------------------------------------------------------------");

            //Close resources
            rs.close();
            stmt.close();
            con.getClass();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
