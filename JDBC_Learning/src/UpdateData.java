import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UpdateData
{
    public static void main(String[] args)
    {

        Scanner sc = new Scanner(System.in);

        try{

            //Get database connection
            Connection con = MainDB.getConnection();

            //User input
            System.out.print("Enter User ID to Update: ");
            int id = sc.nextInt();

            System.out.print("Enter New Salary: ");
            double salary = sc.nextDouble();

            //SQL Query
            String sql = "UPDATE users SET Salary = ? WHERE ID = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, salary);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0){
                System.out.println("Record Updated Successfully!");
            } else {
                System.out.println("User ID Not Found!");
            }

            //Close resources
            ps.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


