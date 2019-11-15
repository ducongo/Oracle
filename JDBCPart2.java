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

            // Statement stmt = conn.createStatement();
            // stmt.executeUpdate("INSERT INTO closedbranch values('619', '3-1565 Heron road')");

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

    public static boolean isNum(String num){
        boolean isNumber = true;

        try{
            Integer.parseInt(num);
            isNumber = true;
        }catch (NumberFormatException e) {
            isNumber = false;
        }
        return isNumber;
    }

    public static void menu(){
        
        displayMenu();
        String consumeNewLine;
        int option = -1;

        loop: while (true){
            try{
                if (option != 0){
                    option = input.nextInt();
                    consumeNewLine = input.nextLine();
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

                        int case2NestedOption = -1;

                        System.out.println("Do you want to close a branch using (1) branch number ot (2) branch address?:");
                        try{
                            case2NestedOption = input.nextInt();
                            consumeNewLine = input.nextLine();

                            switch(case2NestedOption){
                                case 1:
                                    System.out.println("Please enter the branch number of the branch you want to close: ");
                                    String bnum = input.nextLine();
                                    // consumeNewLine = input.nextLine();

                                    if (isNum(bnum) && (bnum.length() < 4)){
                                        close_branch(bnum, true);
                                    }else{
                                        System.out.println("Invalid branch number. Please select option (1) for branch number or (2) for branch address next time!");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Please enter the address of the branch you want to close: ");
                                    String address = input.nextLine();

                                    close_branch(address, true);
                                    break;
                                default:
                                    System.out.println("Invalid option. Please select option (1) for branch number or (2) for branch address next time!");
                            }
                            

                        }catch(Exception e){

                        }
                            
                        break;
                    case 3:
                        System.out.println("You entered " + option);
                        System.out.println("Please enter the customer name: ");
                        String customerName = input.nextLine();
                        double balance = 0;

                        try{
                            System.out.println("Please enter the initial ammount: ");
                            balance = input.nextDouble();  
                        }catch(Exception e){
                            System.out.println("You entered a wront intial amount! Please be careful next time.");
                            break;
                        }

                        int case3NestedOption = -1;

                        System.out.println("Do you want to setup the account using a branch using (1) branch number ot (2) branch address?:");
                        try{
                            case3NestedOption = input.nextInt();
                            consumeNewLine = input.nextLine();

                            switch(case3NestedOption){
                                case 1:
                                    System.out.println("Please enter the branch number of the branch you want to close: ");
                                    String bnum = input.nextLine();
                                    // consumeNewLine = input.nextLine();

                                    if (isNum(bnum) && (bnum.length() < 4)){
                                        System.out.println("(((((BOUT TO CALL FUNC)))))");
                                        setup_account(customerName, bnum, true, balance);
                                    }else{
                                        System.out.println("Something went wrong with your inputs pleasse try all over again!");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Please enter the address of the branch you want to close: ");
                                    String address = input.nextLine();

                                    setup_account(customerName, address, false, balance);
                                    break;
                                default:
                                    System.out.println("Invalid option. Please select option (1) for branch number or (2) for branch address next time!");
                            }
                            

                        }catch(Exception e){

                        }
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
                consumeNewLine = input.nextLine();
            }
            
        }
    }



    public static void open_branch(String address){

        Statement stmt = null;
        try{
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            boolean hasOldBnum = false;
            ResultSet rs;
            
            int next_bnum;
            rs = stmt.executeQuery("SELECT bnum FROM closedbranch");

            while ( rs.next() ) {
                String bnum = rs.getString("bnum");
                System.out.println(bnum);

                if (bnum != null){
                    hasOldBnum = true;
                    // stmt.executeUpdate("UPDATE branch " + "SET address = '" + address + "', " + "isactive = 'Y' " + "WHERE bnum = '" + bnum + "'");
                    stmt.executeUpdate("INSERT INTO branch values('" + bnum + "', '" + address + "')");
                    System.out.println("inserted from closedBranch updated row bnum " + bnum);
                    stmt.executeUpdate("DELETE FROM closedbranch WHERE bnum = '" + bnum + "'");
                }
            }

            if (!hasOldBnum){
                rs = stmt.executeQuery("SELECT MAX(bnum) as bnum FROM branch");
                hasOldBnum = false;
                while ( rs.next() ) {

                    hasOldBnum = true;
                    String bnum = rs.getString("bnum");
                    if (bnum != null){
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

                            stmt.executeUpdate("Insert into branch values('" + new_bnum + "', '" + address + "')");
                            System.out.println("Insert NEW row bnum " + new_bnum);
                        }
                    }else{
                        stmt.executeUpdate("Insert into branch values('000', '" + address + "')");
                        System.out.println("Insert first row bnum 000");
                    }
                }
            }
            
        }catch(Exception e){
            System.out.println("SQL exception: ");
            e.printStackTrace();
            System.exit(-1);
	  }finally{
        try{
            if (stmt != null){
                stmt.close();
                System.out.println("Statement Closed!");
            }
            if (conn != null){
                conn.commit();
                conn.setAutoCommit(true);
                System.out.println("Committed!");
            }
        }catch(Exception e){
            System.out.println("SQL exception: ");
            e.printStackTrace();
            System.exit(-1);
	    }
      }
    }

    public static void close_branch(String identifier, boolean deleteByBnum){

        Statement stmt = null;
        try{
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            ResultSet rs;
            
            if (deleteByBnum){

                switch (identifier.length()){
                    case 1:
                        identifier = "00" + identifier;
                        break;
                    case 2:
                        identifier = "0" + identifier;
                        break;
                }
                rs = stmt.executeQuery("SELECT bnum, address FROM branch WHERE bnum = '" + identifier + "'");

                while ( rs.next() ) {
                    String bnum = rs.getString("bnum");
                    String address = rs.getString("address");
                    System.out.println(bnum);

                    if (bnum != null){
                        stmt.executeUpdate("INSERT INTO closedbranch values('"+ identifier + "', '" + address + "')");
                        stmt.executeUpdate("DELETE FROM branch WHERE bnum = '" + identifier + "'");
                    }
                }

            }else{
                rs = stmt.executeQuery("SELECT bnum, address FROM branch WHERE address = '" + identifier + "'");

                while ( rs.next() ) {
                    String bnum = rs.getString("bnum");
                    String address = rs.getString("address");
                    System.out.println(bnum);

                    if (bnum != null){
                        stmt.executeUpdate("INSERT INTO closedbranch values('"+ bnum + "', '" + address + "')");
                        stmt.executeUpdate("DELETE FROM branch WHERE address = '" + identifier + "'");
                    }
                }
            }

        System.out.println("Branch closed");

        }catch(Exception e){
            System.out.println("SQL exception: ");
            e.printStackTrace();
            System.exit(-1);
	  }finally{
        try{
            if (stmt != null){
                stmt.close();
                System.out.println("Statement Closed!");
            }
            if (conn != null){
                conn.commit();
                conn.setAutoCommit(true);
                System.out.println("Committed!");
            }
        }catch(Exception e){
            System.out.println("SQL exception: ");
            e.printStackTrace();
            System.exit(-1);
	    }
      }
        
    }

    public static void setup_account(String customerName, String identifier, boolean setupByBnum, double balance){

        Statement stmt = null;
        try{
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            ResultSet rs;

            boolean customerExists  = false;
            boolean branchExists = false;
            
    
            if (setupByBnum){
                switch (identifier.length()){
                    case 1:
                        identifier = "00" + identifier;
                        break;
                    case 2:
                        identifier = "0" + identifier;
                        break;
                }
            }
            
            
            rs = stmt.executeQuery("SELECT bnum FROM branch WHERE bnum = '" + identifier + "' OR address = '" + identifier + "'");

            boolean hasNext = rs.next();

            if (!hasNext){
                if (setupByBnum){
                    System.out.println("The branch with the branch number: " + identifier + " does not exist!");
                }else{
                    System.out.println("The branch with the address: " + identifier + " does not exist!");
                }
            }

            while (hasNext) {
                String bnum = rs.getString("bnum");
                if (bnum != null){
                    branchExists = true;
                    ResultSet rs2 = stmt.executeQuery("SELECT cnum FROM customer WHERE name = '" + customerName + "'");
                    boolean hasNext2 = rs2.next();

                    if (!hasNext2){
                        System.out.println("Customer with name: " + customerName + " does not exist!");
                        return;
                    }

                    while (hasNext2) {
                        String cnum = rs.getString("cnum");

                        if (cnum != null){
                            String new_anum = get_account_number(stmt, bnum);
                                
                            if(new_anum != null){
                                int status;
                                
                                if (balance >= 2000){
                                    status = 3;
                                }else if(balance >= 1000){
                                    status = 2;
                                }else if(balance > 0){
                                    status = 1;
                                }else{
                                    status = 0;
                                    balance = 0;
                                }
                                stmt.executeUpdate("INSERT INTO customer values('"+ new_anum + "', '" + cnum + "', " + balance + ")");
                                System.out.println("Account created");
                                return;
                            }
                        }
                        hasNext2 = rs2.next();
                    }
                }
                hasNext = rs.next();
            }

        }catch(Exception e){
            System.out.println("SQL exception: ");
            e.printStackTrace();
            System.exit(-1);
	  }finally{
        try{
            if (stmt != null){
                stmt.close();
                System.out.println("Statement Closed!");
            }
            if (conn != null){
                conn.commit();
                conn.setAutoCommit(true);
                System.out.println("Committed!");
            }
        }catch(Exception e){
            System.out.println("SQL exception: ");
            e.printStackTrace();
            System.exit(-1);
	    }
      }
        
    }

    public static String get_account_number(Statement stmt, String bnum){
        
        String localNum = null;
        try{
            boolean hasOldAnum = false;
            ResultSet rs;
            
            int next_bnum;
            rs = stmt.executeQuery("SELECT anum FROM closedaccount");

            while ( rs.next() ) {
                String anum = rs.getString("anum");
                System.out.println(anum);

                if (anum != null){
                    hasOldAnum = true;
                    localNum = anum;
                    stmt.executeUpdate("DELETE FROM closedaccount WHERE anum = '" + anum + "'");
                }
            }

            if (!hasOldAnum){
                rs = stmt.executeQuery("SELECT MAX(anum) as anum FROM account WHERE anum LIKE '" + bnum +"%'");
                hasOldAnum = false;

                while ( rs.next() ) {

                    hasOldAnum = true;
                    String anum = rs.getString("anum");
                    if (anum != null){
                        int next_anum = Integer.parseInt(anum.substring(3,anum.length()));

                        if (next_anum < 9999){
                            next_anum += 1;
                            String new_anum = String.valueOf(next_anum);

                            switch (new_anum.length()){
                                case 1:
                                    new_anum = "000" + new_anum;
                                    break;
                                case 2:
                                    new_anum = "00" + new_anum;
                                    break;
                                case 3:
                                    new_anum = "0" + new_anum;
                                    break;
                            }

                            localNum = anum;
                        }
                    }else{
                        localNum = "0000";
                    }
                }
            }
            
        }catch(Exception e){

        }

        if (localNum != null){
            return (bnum + localNum);
        }
        return null;
    }



}