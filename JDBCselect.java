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

public class JDBCselect
{
    public static void main(String[] args) 
    {
      try
	  {
         DriverManager.registerDriver
	     (new oracle.jdbc.driver.OracleDriver());
         
         System.out.println("Connecting to JDBC...");

         Connection conn = DriverManager.getConnection
	     ("jdbc:oracle:thin:@localhost:1521:xe","fedora","oracle");
	 //param1: the JDBC URL
	 //param2: the name you used to log in to the DBMS
	 //param3: the password

         System.out.println("JDBC connected.\n");

         // Create a statement
         Statement stmt = conn.createStatement();
	 ResultSet rs = stmt.executeQuery("SELECT sname FROM student");

         while(rs.next())
	     {
		 System.out.println("student name : "+rs.getString(1));
		 //or use
		 //System.out.println("student name : "+rs.getString(sname));
	     }

	 // close the result set, the statement and connect
	 rs.close();   
	 stmt.close();
	 conn.close();
	  }
      catch(Exception e)
	  {
	      System.out.println("SQL exception: ");
	      e.printStackTrace();
	      System.exit(-1);
	  }
    }
}