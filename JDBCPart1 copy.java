/*This is a sample JDBC program. In order to run it,
   You need to do the following. 

1. edit this file with correct oracle username and password
2. javac JDBCselect.java
3. java JDBCselect

JDBC connected.

student name : John
student name : Mary
student name : Tony

If you don't have this table in your database, you will
get error message displayed. 
*/

import java.sql.*;
import java.io.*;
import oracle.jdbc.*;
import oracle.sql.*;
import java.util.*;

public class JDBCPart1{

    public static void main(String[] args) {

        try{
            String create_table_branch = "CREATE TABLE branch ("
                                        +"bnum char(3) primary key, "
                                        +"address char(35), "
                                        +"isactive char(1), "
                                        + "CONSTRAINT check_isactive check (isactive in ('Y','N')), "
                                        +"CONSTRAINT check_Bnum CHECK (TO_NUMBER(bnum) BETWEEN 0 and 999))";
            
            
            String create_table_customer = "CREATE TABLE customer ("
                                        +"cnum char(5), "
                                        +"name char(20) primary key, "
                                        +"status int, "
                                        +"balance int, "
                                        +"CONSTRAINT check_Status "
                                        +"CHECK (status BETWEEN 0 and 3))";

            String create_table_account = "CREATE TABLE account ("
                                        +"anum char(7) primary key, "
                                        +"localnum char(4), "
                                        +"bnum char(3), "
                                        +"CONSTRAINT branch_num "
                                        +"FOREIGN KEY (bnum) REFERENCES branch(bnum))";

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
            System.out.println("Connecting to JDBC...");

            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","fedora","oracle");
                //param1: the JDBC URL
                //param2: the name you used to log in to the DBMS
                //param3: the password

            System.out.println("JDBC connected.\n");

            // Create a statement
            Statement stmt = conn.createStatement();

            try{
                String sql = "DROP TABLE branch CASCADE CONSTRAINTS";
                stmt.executeUpdate(sql);
                System.out.println("Branch table dropped.\n");

                sql = "DROP TABLE customer CASCADE CONSTRAINTS";
                stmt.executeUpdate(sql);
                System.out.println("Customer table dropped.\n");

                sql = "DROP TABLE account CASCADE CONSTRAINTS";
                stmt.executeUpdate(sql);
                System.out.println("Account table dropped.\n");

            }catch(Exception e){
                System.out.println("SQL exception: ");
                e.printStackTrace();
            }

            System.out.println("********************************************************.\n");
            
            stmt.executeUpdate(create_table_branch);
            System.out.println("Branch table created.\n");
            stmt.executeUpdate(create_table_customer);
            System.out.println("Customer table created.\n");
            stmt.executeUpdate(create_table_account);
            System.out.println("Account table created.\n");
        }
        catch(Exception e){
            System.out.println("SQL exception: ");
            e.printStackTrace();
            System.exit(-1);
	  }
    }
}