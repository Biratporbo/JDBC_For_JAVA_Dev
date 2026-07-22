import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteData
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        try{

            //Get Database Connection
            Connection con = MainDB.getConnection();

            //Take User ID from user
            System.out.print("Enter User ID to Delete: ");
            int id = sc.nextInt();

            //SQL Query
            String sql = "DELETE FROM users WHERE ID = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
            {
                System.out.println("Record Deleted Successfully!");
            } else {
                System.out.println("User ID Not Found!");
            }

            //Close Resources
            ps.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
