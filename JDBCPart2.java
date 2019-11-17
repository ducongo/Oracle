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
                        open_branch("String address");
                        break;
                    case 2:
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
                                    System.out.println("Please enter the branch address of the branch you want to close: ");
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
                                    System.out.println("Please enter the branch number of the account you want to close: ");
                                    String bnum = input.nextLine();
                                    // consumeNewLine = input.nextLine();

                                    if (isNum(bnum) && (bnum.length() < 4)){
                                        setup_account(customerName, bnum, true, balance);
                                    }else{
                                        System.out.println("Something went wrong with your inputs pleasse try all over again!");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Please enter the branch address of the account you want to close: ");
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
                        
                        System.out.println("Please enter the customer name: ");
                        customerName = input.nextLine();

                        int case4NestedOption = -1;

                        System.out.println("Do you want to setup the customer account using a branch using (1) branch number ot (2) branch address?:");
                        try{
                            case4NestedOption = input.nextInt();
                            consumeNewLine = input.nextLine();

                            switch(case4NestedOption){
                                case 1:
                                    System.out.println("Please enter the branch number of the customer you want to setup: ");
                                    String bnum = input.nextLine();
                                    // consumeNewLine = input.nextLine();

                                    if (isNum(bnum) && (bnum.length() < 4)){
                                        setup_customer(customerName, bnum, true);
                                    }else{
                                        System.out.println("Something went wrong with your inputs pleasse try all over again!");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Please enter the branch address of the customer you want to setup: ");
                                    String address = input.nextLine();

                                    setup_customer(customerName, address, false);
                                    break;
                                default:
                                    System.out.println("Invalid option. Please select option (1) for branch number or (2) for branch address next time!");
                            }
                            

                        }catch(Exception e){

                        }
                        break;
                    case 5:
                        System.out.println("Please enter the customer name: ");
                        customerName = input.nextLine();

                        int case5NestedOption = -1;

                        System.out.println("Do you want to setup the customer account using a branch using (1) branch number ot (2) branch address?:");
                        try{
                            case5NestedOption = input.nextInt();
                            consumeNewLine = input.nextLine();

                            switch(case5NestedOption){
                                case 1:
                                    System.out.println("Please enter the branch number of the accountyou want to close: ");
                                    String bnum = input.nextLine();
                                    // consumeNewLine = input.nextLine();

                                    if (isNum(bnum) && (bnum.length() < 4)){
                                        close_account(customerName, bnum, true);
                                    }else{
                                        System.out.println("Something went wrong with your inputs pleasse try all over again!");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Please enter the branch address of the account you want to close: ");
                                    String address = input.nextLine();

                                    close_account(customerName, address, false);
                                    break;
                                default:
                                    System.out.println("Invalid option. Please select option (1) for branch number or (2) for branch address next time!");
                            }
                            

                        }catch(Exception e){

                        }
                        break;
                    case 6:
                        
                        break;
                    case 7:
                        
                        break;
                    case 8:
                        
                        break;
                    case 9:
                        
                        break;
                    case 10:
                        
                        break;
                    case 11:
                        
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
                        String cnum = rs2.getString("cnum");

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
                                stmt.executeUpdate("INSERT INTO account values('"+ new_anum + "', '" + cnum + "', " + balance + ")");
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

    public static void close_account(String customerName, String identifier, boolean closeByBnum){

        Statement stmt = null;
        try{
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            ResultSet rs;

            boolean customerExists  = false;
            boolean branchExists = false;
            
    
            if (closeByBnum){
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
                if (closeByBnum){
                    System.out.println("The branch with the branch number: " + identifier + " does not exist!");
                }else{
                    System.out.println("The branch with the address: " + identifier + " does not exist!");
                }
            }

            while (hasNext) {
                String bnum = rs.getString("bnum");
                if (bnum != null){
                    branchExists = true;
                    ResultSet rs2 = stmt.executeQuery("SELECT cnum, status FROM customer WHERE name = '" + customerName + "'");
                    boolean hasNext2 = rs2.next();

                    if (!hasNext2){
                        System.out.println("Customer with name: " + customerName + " does not exist!");
                        return;
                    }

                    while (hasNext2) {
                        String cnum = rs2.getString("cnum");
                        int status = rs2.getInt("status");

                        if (cnum != null){

                            if (status == 0){
                                ResultSet rs3 = stmt.executeQuery("SELECT anum, balance FROM account WHERE cnum = '" + cnum + "' and anum LIKE '" + bnum +"%'");
                                boolean hasNext3 = rs3.next();

                                while(hasNext3){
                                    String anum = rs3.getString("anum");
                                    double balance = rs3.getDouble("balance");

                                    if (anum != null){
                                        if (balance <= 0){
                                            ResultSet rs4 = stmt.executeQuery("SELECT count(anum) as total FROM account WHERE cnum = '" + cnum + "'");
                                            boolean hasNext4 = rs4.next();
                                            int total = rs4.getInt("total");

                                            while(hasNext4){
                                                System.out.println("NUMMMMMMMMMMMMBERRRRRRRRRRRRRRRRRR OF " + total);
                                                if (total < 2){
                                                    stmt.executeUpdate("DELETE FROM account WHERE anum = '" + anum + "'");
                                                    stmt.executeUpdate("INSERT INTO closedaccount values('"+ anum + "')");
                                                    stmt.executeUpdate("DELETE FROM customer WHERE cnum = '" + cnum + "'");
                                                    stmt.executeUpdate("INSERT INTO closedcustomer values('"+ cnum + "')");
                                                }else{
                                                    stmt.executeUpdate("DELETE FROM account WHERE anum = '" + anum + "'");
                                                    stmt.executeUpdate("INSERT INTO closedaccount values('"+ anum + "')");
                                                }
                                                
                                                System.out.println("Customer's account with name: " + customerName + " and branch: " + identifier + "has been closed");
                                                hasNext4 = rs4.next();
                                            }
                                        }else{
                                            System.out.println("Customer with name: " + customerName + " has a balance greather than $0");
                                        }
                                    }
                                    hasNext3 = rs3.next();
                                }
                            }else{
                                System.out.println("Customer with name: " + customerName + " has a balance greather than $0");
                            }
                        }else{
                            System.out.println("The customer you provide does not exist!");
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

    public static double withdraw(String customerName, String anum, double amount){
        Statement stmt = null;
        try{
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT cnum from account WHERE anum = '" + anum + "'");

            boolean hasNext = rs.next();

            if (!hasNext){
                System.out.println("The account with the account number: " + anum + " does not exist!");
                return 0;
            }else{
                ResultSet rs2 = stmt.executeQuery("SELECT balance from account WHERE anum = '" + anum + "'");
                boolean hasNext2 = rs2.next();

                while (hasNext2){
                    double balance = rs2.getDouble("balance");


                    if ((balance - amount) < 0){
                        System.out.println("Insufficient funds!");
                        return 0;
                    }else{
                        balance = balance - amount;
                    }

                    stmt.executeUpdate("UPDATE account " + "SET balance = " + balance + " WHERE anum = '" + anum + "'");
                    ResultSet rs3 = stmt.executeQuery("SELECT SUM(balance) as total from account WHERE cnum = (select cnum from customer where name = '" +customerName+"')");
                    boolean hasNext3 = rs3.next();

                    while (hasNext3){
                        double total_balance = rs3.getDouble("total");
                        int status = 0;
                        if (total_balance >= 2000){
                            status = 3;
                        }else if(total_balance >= 1000){
                            status = 2;
                        }else if(total_balance > 0){
                            status = 1;
                        }else{
                            status = 0;
                        }

                        stmt.executeUpdate("UPDATE customer " + "SET status = " + status + " WHERE name = '" + customerName + "'");
                        return balance;
                        
                    }

                    hasNext2 = rs2.next();
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
      return 0;
    }

    public static double deposit(String customerName, String anum, double amount){
        Statement stmt = null;
        try{
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT cnum from account WHERE anum = '" + anum + "'");

            boolean hasNext = rs.next();

            if (!hasNext){
                System.out.println("The account with the account number: " + anum + " does not exist!");
                return 0;
            }else{
                ResultSet rs2 = stmt.executeQuery("SELECT balance from account WHERE anum = '" + anum + "'");
                boolean hasNext2 = rs2.next();

                while (hasNext2){
                    double balance = rs2.getDouble("balance");

                    balance += amount;

                    stmt.executeUpdate("UPDATE account " + "SET balance = " + balance + " WHERE anum = '" + anum + "'");
                    ResultSet rs3 = stmt.executeQuery("SELECT SUM(balance) as total from account WHERE cnum = (select cnum from customer where name = '" +customerName+"')");
                    boolean hasNext3 = rs3.next();

                    while (hasNext3){
                        double total_balance = rs3.getDouble("total");
                        int status = 0;
                        if (total_balance >= 2000){
                            status = 3;
                        }else if(total_balance >= 1000){
                            status = 2;
                        }else if(total_balance > 0){
                            status = 1;
                        }else{
                            status = 0;
                        }

                        stmt.executeUpdate("UPDATE customer " + "SET status = " + status + " WHERE name = '" + customerName + "'");
                        return balance;
                        
                    }

                    hasNext2 = rs2.next();
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
      return 0;
    }

    public static void transfer(String customerName, String anum1, String anum2, double amount){
        Statement stmt = null;
        try{
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            ResultSet rs;

            double balance;

            rs = stmt.executeQuery("SELECT cnum from account WHERE anum = '" + anum1 + "' or anum = '" + anum2 + "'");

            boolean hasNextAnum1 = rs.next();
            boolean hasNextAnum2 = rs.next();

            if (!(hasNextAnum1 && hasNextAnum2)){

                if (hasNextAnum1){
                    System.out.println("The account with the account number: " + anum2 + " does not exist!");
                }else if (hasNextAnum2){
                    System.out.println("The account with the account number: " + anum2 + " does not exist!");
                }else{
                    System.out.println("The accounts with the account number: " + anum1 + " and " + anum2 + " do not exist!");
                }
                
            }else{
                ResultSet rs2 = stmt.executeQuery("SELECT balance from account WHERE anum = '" + anum1 + "'");
                boolean hasNext2 = rs2.next();

                while (hasNext2){
                    balance = rs2.getDouble("balance");


                    if ((balance - amount) < 0){
                        System.out.println("Insufficient funds! for account with the account number" + anum1);
                        return;
                    }else{
                        balance = balance - amount;
                    }

                    stmt.executeUpdate("UPDATE account " + "SET balance = " + balance + " WHERE anum = '" + anum1 + "'");
                    ResultSet rs3 = stmt.executeQuery("SELECT SUM(balance) as total from account WHERE cnum = (select cnum from customer where name = '" +customerName+"')");
                    boolean hasNext3 = rs3.next();

                    while (hasNext3){
                        double total_balance = rs3.getDouble("total");
                        int status = 0;
                        if (total_balance >= 2000){
                            status = 3;
                        }else if(total_balance >= 1000){
                            status = 2;
                        }else if(total_balance > 0){
                            status = 1;
                        }else{
                            status = 0;
                        }

                        stmt.executeUpdate("UPDATE customer " + "SET status = " + status + " WHERE name = '" + customerName + "'");
                        hasNext3 = rs3.next();
                    }

                    ResultSet rs4 = stmt.executeQuery("SELECT balance, cnum from account WHERE anum = '" + anum2 + "'");
                    boolean hasNext4 = rs4.next();

                    while (hasNext4){
                        double balance2 = rs4.getDouble("balance");
                        String cnum = rs4.getString("cnum");
                        
                        stmt.executeUpdate("UPDATE account " + "SET balance = " + amount + " WHERE anum = '" + anum2 + "'");
                        ResultSet rs5 = stmt.executeQuery("SELECT SUM(balance) as total from account WHERE cnum = '" + cnum +"')");
                        boolean hasNext5 = rs5.next();

                        while (hasNext5){
                            double total_balance2 = rs5.getDouble("total");
                            int status2 = 0;
                            if (total_balance2 >= 2000){
                                status2 = 3;
                            }else if(total_balance2 >= 1000){
                                status2 = 2;
                            }else if(total_balance2 > 0){
                                status2 = 1;
                            }else{
                                status2 = 0;
                            }

                            stmt.executeUpdate("UPDATE customer " + "SET status = " + status2 + " WHERE cnum = '" + cnum + "'");
                            hasNext5 = rs5.next();
                        }
                        hasNext4 = rs4.next();
                    }

                    hasNext2 = rs2.next();
                }
                System.out.print("Succesfully tranferred money!");
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

    public static void setup_customer(String customerName, String identifier, boolean setupByBnum){

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
                return;
            }

            while (hasNext) {
                String bnum = rs.getString("bnum");
                if (bnum != null){
                    branchExists = true;
                    ResultSet rs2 = stmt.executeQuery("SELECT cnum FROM customer WHERE name = '" + customerName + "'");
                    boolean hasNext2 = rs2.next();

                    if (hasNext2){
                        System.out.println("Customer with name: " + customerName + " already exist!");
                        return;
                    }else{
                        System.out.println("Customer with name: " + customerName + " DOES NOT EXITSSSSSSS OHHHHalready exist!");
                        String cnum = get_customer_number(stmt);
                        stmt.executeUpdate("INSERT INTO customer values('"+ cnum + "', '" + customerName + "', " + 0 + ")");
                        setup_account(customerName, identifier, setupByBnum, 0);
                        System.out.println("Customer with name: " + customerName + " was creadted successfully!");
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

    public void show_all_branches(){
        Statement stmt = null;
        try{
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT bnum FROM branch");

            String bnum;

            while (rs.next()){
                bnum = rs.getString("bnum");

                if (bnum != null){
                    show_branch(bnum, true, true);
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

    public void show_branch(String identifier, boolean getByBnum, boolean showAllBranch){
        Statement stmt = null;
        try{
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            ResultSet rs;

            boolean customerExists  = false;
            boolean branchExists = false;
            
    
            if (getByBnum){
                switch (identifier.length()){
                    case 1:
                        identifier = "00" + identifier;
                        break;
                    case 2:
                        identifier = "0" + identifier;
                        break;
                }
            }
            
            if (!showAllBranch){
                rs = stmt.executeQuery("SELECT cnum, anum, balance FROM account WHERE anum LIKE '" + bnum +"%'");

                boolean hasNext = rs.next();
                double totalBalanace = 0;

                if (!hasNext){
                    if (!getByBnum){
                        System.out.println("The branch with the branch number: " + identifier + " does not exist!");
                    }else{
                        System.out.println("The branch with the address: " + identifier + " does not exist!");
                    }
                    return;
                }
            }
            

            
            double total_balance;
            double balance;
            String cnum;
            String anum;

            System.out.println("cnum     anum      balance");
            System.out.println("----------------------------------------");
            while (hasNext) {
                balance = rs3.getDouble("balance");
                anum = rs3.getString("anum");
                cnum = rs3.getString("cnum");
                
                if (cnum != null){
                    total_balance += balance;
                    System.out.println(cnum + "   " + anum + "   " + balance);
                }
                
                hasNext = rs.next();
            }

            System.out.println("total balance     " + total_balance);
            System.out.println("----------------------------------------");

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
            rs = stmt.executeQuery("SELECT anum FROM closedaccount WHERE anum LIKE '" + bnum +"%'");

            while ( rs.next() ) {
                String anum = rs.getString("anum");
                System.out.println(anum);

                if (anum != null){
                    hasOldAnum = true;
                    // localNum = anum;
                    System.out.println("UHAHAHHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAH " + anum);
                    stmt.executeUpdate("DELETE FROM closedaccount WHERE anum = '" + anum + "'");
                    return anum;
                }
            }

            if (!hasOldAnum){
                rs = stmt.executeQuery("SELECT MAX(anum) as anum FROM account WHERE anum LIKE '" + bnum +"%'");
                hasOldAnum = false;

                while ( rs.next() ) {

                    hasOldAnum = true;
                    String anum = rs.getString("anum");
                    if (anum != null){
                        int highest_anum = Integer.parseInt(anum.substring(3,anum.length()));

                        if (highest_anum < 9999){
                            highest_anum += 1;
                            String next_anum = String.valueOf(highest_anum);
                            System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY " + next_anum);
                            switch (next_anum.length()){
                                case 1:
                                    next_anum = "000" + next_anum;
                                    break;
                                case 2:
                                    next_anum = "00" + next_anum;
                                    break;
                                case 3:
                                    next_anum = "0" + next_anum;
                                    break;
                            }
                            System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO " + next_anum);

                            localNum = next_anum;
                        }
                    }else{
                        localNum = "0000";
                    }
                }
            }
            
        }catch(Exception e){

        }
        System.out.println("???????????????????????????????????? " + bnum + localNum);
        if (localNum != null){
            return (bnum + localNum);
        }
        return localNum;
    }

    

    public static String get_customer_number(Statement stmt){
        
        String returnValue = null;
        try{
            boolean hasOldAnum = false;
            ResultSet rs;
            
            int next_bnum;
            rs = stmt.executeQuery("SELECT cnum FROM closedcustomer");

            while ( rs.next() ) {
                String cnum = rs.getString("cnum");
                System.out.println(cnum);

                if (cnum != null){
                    hasOldAnum = true;
                    returnValue = cnum;
                    stmt.executeUpdate("DELETE FROM closedcustomer WHERE cnum = '" + cnum + "'");
                }
            }

            if (!hasOldAnum){
                rs = stmt.executeQuery("SELECT MAX(cnum) as cnum FROM customer");
                hasOldAnum = false;

                while ( rs.next() ) {

                    hasOldAnum = true;
                    String cnum = rs.getString("cnum");
                    if (cnum != null){
                        int highest_cnum = Integer.parseInt(cnum);

                        if (highest_cnum < 99999){
                            highest_cnum += 1;
                            String next_cnum = String.valueOf(highest_cnum);

                            switch (next_cnum.length()){
                                case 1:
                                    next_cnum = "0000" + next_cnum;
                                    break;
                                case 2:
                                    next_cnum = "000" + next_cnum;
                                    break;
                                case 3:
                                    next_cnum = "00" + next_cnum;
                                    break;
                                case 4:
                                    next_cnum = "0" + next_cnum;
                                    break;
                            }

                            returnValue = next_cnum;
                        }
                    }else{
                        returnValue = "00000";
                    }
                }
            }
            
        }catch(Exception e){

        }

        return returnValue;
    }

}