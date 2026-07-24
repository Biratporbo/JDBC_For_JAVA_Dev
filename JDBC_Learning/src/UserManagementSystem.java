import java.sql.*;
import java.util.Scanner;

public class UserManagementSystem
{
    static Scanner sc = new Scanner(System.in);
    static Connection con = MainDB.getConnection();

    //Insert User
    public static void insertUser()
    {

        try {

            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextInt();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextInt();

            System.out.print("Enter City: ");
            String city = sc.nextLine();

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            System.out.print("Enter Gender (Male/Female/Other): ");
            String gender = sc.nextLine();

            String sql = "INSERT INTO users(ID, Name, Age, City, Salary, Gender) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps =  con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, city);
            ps.setDouble(5, salary);
            ps.setString(6, gender);

            int rows = ps.executeUpdate();

            if(rows > 0)
                System.out.println("\nUser Inserted Successfully!");

            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Display Users
    public static void dispalyUsers()
    {

        try {

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM users");

            System.out.println("\n---------------------------------------------------------------");
            System.out.printf("%-5s %-15s %-5s %-15s %-10s %-10s%n",
                    "ID", "Name", "Age", "City", "Salary", "Gender");
            System.out.println("-----------------------------------------------------------------");

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

            rs.close();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Search User
    public static void searchUser()
    {

        try {

            System.out.print("Enter User ID: ");
            int id = sc.nextInt();

            String sql = "SELECT * FROM users WHERE ID=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {

                System.out.println("\nUser Found");
                System.out.println("----------------------");
                System.out.println("ID     : " + rs.getInt("ID"));
                System.out.println("Name   : " + rs.getString("Name"));
                System.out.println("Age    : " + rs.getInt("Age"));
                System.out.println("City   : " + rs.getString("City"));
                System.out.println("Salary : " + rs.getDouble("Salary"));
                System.out.println("Gender : " + rs.getString("Gender"));

            } else {

                System.out.println("User Not Found!");

            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Update User Salary
    public static void updateUser()
    {

        try {

            System.out.print("Enter User ID: ");
            int id = sc.nextInt();

            System.out.print("Enter New Salary: ");
            double salary = sc.nextDouble();

            String sql = "UPDATE users SET Salary=? WHERE ID=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, salary);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Record Updated Successfully!");
            else
                System.out.println("user Not Found!");

            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Delete User
    public static void deleteUser()
    {

        try {

            System.out.print("Enter User ID: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM users WHERE ID=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Record Deleted Successfully!");
            else
                System.out.println("User Not Found!");

            ps.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args)
    {

        int choice;

        do{

            System.out.println("\n=================================");
            System.out.println("    USER MANAGEMENT SYSTEM");
            System.out.println("1. Insert User");
            System.out.println("2. Display All Users");
            System.out.println("3. Search User");
            System.out.println("4. Update User Salary");
            System.out.println("5. Delete User");
            System.out.println("6. Exit");
            System.out.print("Enter Your Choice: ");

            choice = sc.nextInt();

            switch (choice)
            {

                case 1:
                    insertUser();
                    break;

                case 2:
                    dispalyUsers();
                    break;

                case 3:
                    searchUser();
                    break;

                case 4:
                    updateUser();
                    break;

                case 5:
                    deleteUser();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);

        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();

    }
}
