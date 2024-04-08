import java.sql.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
    
        //1. Arrange & Load the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2. Do the connection with Database using Connection Interface & DriverManager class.
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");
        System.out.println(con!=null?"Successful Connection!":"Failed Connection!");


        Scanner sc = new Scanner(System.in);

        boolean loginSuccessful = false;
        String username = "";

        while(true){
            System.out.println("INDEX");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Play Games");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            if(choice == 4){
                break;
            }

            String usernameLogin, password;

            switch(choice){
                case 1:
                    System.out.println("LOGIN");
                    System.out.print("Enter your username: ");
                    usernameLogin = sc.next();
                    System.out.print("Enter your password: ");
                    password = sc.next();

                    //3. Create Statement using Connection Interface
                    Statement stmt = con.createStatement();

                    //4. Execute the query using Statement Interface
                    ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE username='"+usernameLogin+"' AND password='"+password+"'");

                    //5. Process the ResultSet object
                    if(rs.next()){
                        System.out.println("Login Successful!");
                        loginSuccessful = true;
                        username = rs.getString("Username");
                    }else{
                        System.out.println("Login Failed! Please try again.");
                    }
                    break;

                case 2:
                    System.out.println("REGISTER");
                    System.out.println("Please fill in the details below: ");
                    System.out.println("==================================");
                    System.out.println("Enter your first name: ");
                    String firstname = sc.next();
                    System.out.println("Enter your last name: ");
                    String lastname = sc.next();
                    System.out.print("Enter your username: ");
                    username = sc.next();
                    System.out.print("Enter your password: ");
                    password = sc.next();
                    System.out.println("Enter your email: ");
                    String email = sc.next();
                    System.out.println("Enter your phone number: ");    
                    Long phone = sc.nextLong();
                    System.out.println("Enter your biodata (Optional): ");
                    String biodata = sc.nextLine()+sc.nextLine();
                    String gender = "";
                    System.out.println("Enter your gender : ");
                    System.out.println("1. Male");
                    System.out.println("2. Female");
                    System.out.println("3. Others");
                    System.out.println("4. Rather not say");
                    System.out.println("Enter your choice : ");
                    choice = sc.nextInt();
                    switch(choice){
                        case 1 :
                        gender= "Male";
                        case 2 :
                        gender= "Female";
                        case 3 :
                        gender= "Others";
                        case 4: 
                        gender= "Rather not say";
                        default: 
                        break;
                    }
                    System.out.println("Enter your birthdate (Follow YYYY-MM-DD and add the dashes): ");
                    String dateofbirth = sc.next();

                    //3. Create Statement using Connection Interface
                    Statement stmt1 = con.createStatement();

                    //4. Execute the query using Statement Interface
                    int h = stmt1.executeUpdate("INSERT INTO user(Username, Firstname, Lastname, Password, Email, Gender, DateOfBirth, ContactNumber, Biodata) VALUES('"+username+"','"+firstname+"','"+lastname+"','"+password+"','"+email+"','"+gender+"','"+dateofbirth+"',"+phone+",'"+biodata+"')");

                    //5. Process the ResultSet object
                    if(h>0){
                        System.out.println("Registration Successful!");
                    }else{
                        System.out.println("Registration Failed!");
                    }
                    break;
            
                case 3:

                if(!loginSuccessful){
                    System.out.println("Please login first!");
                    break;
                }

                Games g = new Games();
                g.playGames();
                String winOrLose = g.wining();
                String game = g.gamePlayed();
                
                Statement stmt2 = con.createStatement();
                int j;
                if(winOrLose.contains("Won")){
                    j = stmt2.executeUpdate("INSERT INTO games(Username, Game, Win) VALUES('"+username+"','"+game+"','Won')");

                }
                else{
                    j = stmt2.executeUpdate("INSERT INTO games(Username, Game, Win) VALUES('"+username+"','"+game+"','Lost')");
                }
                
                if(j>0){
                    System.out.println("Entered into leaderboard!");
                }
                

                
                default:
                    System.out.println("Invalid Choice!");
                    break;

            }


        }

        sc.close();



    }


    
}



