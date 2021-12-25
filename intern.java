import java.io.*;
import java.util.*;
import java.sql.*;

class Interndb {
    public void create() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaintern", "root", "");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                    "create table Student (STUDENT_NO INT(3) primary key AUTO_INCREMENT,STUDENT_NAME varchar(30),STUDENT_DOB date,STUDENT_DOJ date)");
            System.out.println("Table created");
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insert() {
        Scanner s = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaintern", "root", "");
            PreparedStatement stmt = con
                    .prepareStatement("insert into Student(STUDENT_NAME,STUDENT_DOB,STUDENT_DOJ) values(?,?,?)");
            System.out.println("Name : ");
            String name = s.next();
            System.out.println("Date of birth :  (of format yyyy/mm/dd)");
            String date = s.next();
            System.out.println("Date of Joining :  (of format yyyy/mm/dd)");
            String datej = s.next();

            stmt.setString(1, name);
            stmt.setString(2, date);
            stmt.setString(3, datej);
            stmt.executeUpdate();
            System.out.print("\nRecord Inserted");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("The table value inserted");
    }

    public void update() {
        Scanner s = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaintern", "root", "");
            PreparedStatement stmt = con.prepareStatement(
                    "update Student set STUDENT_NAME=?,STUDENT_DOB=?,STUDENT_DOJ=? where STUDENT_NO=?");
            System.out.println("Enter the STUDENT_NO : ");
            int id = s.nextInt();
            System.out.println("Updated STUDENT_NAME : ");
            String name = s.next();
            System.out.println("Updated STUDENT_DOB : (of format yyyy/mm/dd)");
            String date = s.next();
            System.out.println("updated STUDENT_DOJ : (of format yyyy/mm/dd)");
            String datej = s.next();

            stmt.setString(1, name);
            stmt.setString(2, date);
            stmt.setString(3, datej);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.print("\nRecord Updated");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void delete() {
        Scanner s = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaintern", "root", "");
            PreparedStatement stmt = con.prepareStatement("delete from Student where STUDENT_NO=?");
            System.out.println("Enter the STUDENT_NO number : ");
            int id = s.nextInt();
            stmt.setInt(1, id);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("The table value deleted");

    }

    public void show() {
        Scanner s = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaintern", "root", "");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from Student");
            System.out.print("STUDENT_NO STUDENT_NAME    STUDENT_DOB  STUDENT_DOJ   \n");
            while (rs.next()) {
                System.out.print(rs.getInt(1) + "         ");
                System.out.print(rs.getString(2) + "  ");
                System.out.print(rs.getString(3) + "   ");
                System.out.print(rs.getString(4) + "\t\n\t");
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void showone() {
        Scanner s = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaintern", "root", "");
            String str="select STUDENT_NAME, STUDENT_DOB,STUDENT_DOJ from Student where STUDENT_NO=?";
            PreparedStatement st = con.prepareStatement(str);
            System.out.println("Enter the STUDENT_NO number : ");
            int id = s.nextInt();
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next())
            {
                
                    System.out.print("STUDENT_NAME    STUDENT_DOB  STUDENT_DOJ   \n");
    
                    System.out.print(rs.getString(1) + "     ");
                    System.out.print(rs.getString(2) + "  ");
                    System.out.print(rs.getString(3) + "   ");
                    System.out.print(rs.getString(4) + "\t\n\t");
    
                
            }
            
           

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

class intern {
    public static void main(String arg[]) {
        Scanner s = new Scanner(System.in);
        Interndb obj = new Interndb();
        obj.create();
        while (true) {
            System.out.println(
                    "\n*****Student DB Table *****\n1-Insert Data\n2-Update data\n3-Delete data\n4-Show All Values\n5-Show particular record\n6-Exit The program\n Enter your Choice:");
            int choice = s.nextInt();
            switch (choice) {
                case 1:
                    obj.insert();
                    break;
                case 2:
                    obj.update();
                    break;
                case 3:
                    obj.delete();
                    break;
                case 4:
                    obj.show();
                    break;
                case 5:
                    obj.showone();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Incorrect input. Please re-enter choice from our menu");
            }
        }

    }
}