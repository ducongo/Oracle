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

import java.util.Scanner;

public class JDBCPart2{

    static Connection conn = null;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        try{

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            
            System.out.println("Connecting to JDBC...");

            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","fedora","oracle");
                //param1: the JDBC URL
                //param2: the name you used to log in to the DBMS
                //param3: the password

            System.out.println("JDBC connected.\n");

            menu();

            
            
            
        }
        catch(Exception e){
            System.out.println("SQL exception: ");
            e.printStackTrace();
            System.exit(-1);
	  }
    }

    public static void displayMenu(){
        System.out.println("Please select one of the options.");
        System.out.println("1. Open a branch\n");
        System.out.println("2. Close a branch\n");
        System.out.println("3. Setup an account\n");
        System.out.println("4. Setup a customer\n");
        System.out.println("5. Close an account\n");
        System.out.println("6. Withdraw\n");
        System.out.println("7. Deposit\n");
        System.out.println("8. Transfer\n");
        System.out.println("9. Display a branch\n");
        System.out.println("10. Display all the branches\n");
        System.out.println("11. Display customer\n");
        System.out.println("0. To exit the program\n");
    }

    public static void menu(){
        
        displayMenu();

        int option = -1;
        loop: while (true){
            try{
                if (option != 0){
                    option = input.nextInt();
                }
                
                switch(option) {
                    case 0:
                        System.out.println("Exiting program...");
                        break loop;
                    case 1:
                        System.out.println("You entered " + option);
                        open_branch("String address");
                        break;
                    case 2:
                        System.out.println("You entered " + option);
                        break;
                    case 3:
                        System.out.println("You entered " + option);
                        break;
                    case 4:
                        System.out.println("You entered " + option);
                        break;
                    case 5:
                        System.out.println("You entered " + option);
                        break;
                    case 6:
                        System.out.println("You entered " + option);
                        break;
                    case 7:
                        System.out.println("You entered " + option);
                        break;
                    case 8:
                        System.out.println("You entered " + option);
                        break;
                    case 9:
                        System.out.println("You entered " + option);
                        break;
                    case 10:
                        System.out.println("You entered " + option);
                        break;
                    case 11:
                        System.out.println("You entered " + option);
                        break;
 
                    default:
                        System.out.println("Please select a valid option listed above");
                    // code block
                }

                displayMenu();
            }catch(InputMismatchException e){
                System.out.println("Please enter a valid number");
            }
            
        }
    }



    public static void open_branch(String address){

        try{
            Statement stmt = conn.createStatement();
            boolean hasOldBnum = false;
            ResultSet rs;
            
            int next_bnum;
            rs = stmt.executeQuery("SELECT bnum FROM branch WHERE isactive='N'");

            while ( rs.next() ) {
                String bnum = rs.getString("bnum");
                System.out.println(bnum);

                if (bnum != null){
                    hasOldBnum = true;
                    stmt.executeUpdate("UPDATE branch " + "SET address = '" + address + "', " + "isactive = 'Y' " + "WHERE bnum = '" + bnum + "'");
                    System.out.println("Insert updated row bnum " + bnum);
                }
            }

            if (!hasOldBnum){
                System.out.println("Doesn't exist");

                rs = stmt.executeQuery("SELECT MAX(bnum) as bnum FROM branch");
                hasOldBnum = false;
                while ( rs.next() ) {

                    hasOldBnum = true;
                    String bnum = rs.getString("bnum");
                    if (bnum != null){
                        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$" +bnum+"$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                        next_bnum = Integer.parseInt(bnum);
                        if (next_bnum < 999){
                            next_bnum += 1;
                            String new_bnum = String.valueOf(next_bnum);

                            switch (new_bnum.length()){
                                case 1:
                                    new_bnum = "00" + new_bnum;
                                    break;
                                case 2:
                                    new_bnum = "0" + new_bnum;
                                    break;
                            }

                            stmt.executeUpdate("Insert into branch values('" + new_bnum + "', '" + address + "', 'Y')");
                            System.out.println("Insert NEW row bnum " + new_bnum);
                        }
                    }else{
                        stmt.executeUpdate("Insert into branch values('000', '" + address + "', 'Y')");
                        System.out.println("Insert firs row bnum 000");
                    }
                }
            }
            
        }catch(Exception e){
            System.out.println("SQL exception: ");
            e.printStackTrace();
            System.exit(-1);
	  }
    }



}