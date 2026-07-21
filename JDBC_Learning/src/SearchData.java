import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SearchData
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        try{

            //Get database connection
            Connection con = MainDB.getConnection();

            //Ask user for ID
            System.out.print("Enter User ID: ");
            int id = sc.nextInt();

            //SQL Query
            String sql = "SELECT * FROM users WHERE ID = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {

                System.out.println("\nUser Found");
                System.out.println("---------------------------------");
                System.out.println("ID     : " + rs.getInt("ID"));
                System.out.println("Name   : " + rs.getString("Name"));
                System.out.println("Age    : " + rs.getInt("Age"));
                System.out.println("City   : " + rs.getString("City"));
                System.out.println("Salary : " + rs.getDouble("Salary"));
                System.out.println("Gender : " + rs.getString("Gender"));
                System.out.println("---------------------------------");

            } else {

                System.out.println("User Not Found!");

            }

            rs.close();
            ps.close();
            con.close();
            sc.close();
        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
