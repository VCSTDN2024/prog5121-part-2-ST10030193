package chatapp;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Owethu
 */
public class Login {
    Scanner scan = new Scanner(System.in);
    Register r;

    // Constructor that receives the Register object
    public Login(Register reg) {
        this.r = reg;
    }
    
    public void loginUser(){
      
      System.out.println("*********************************************");
      System.out.println("LOGIN");
      System.out.println("*********************************************");
      
     
      boolean loginStatus ;
      
      System.out.print("Enter Username: ");
      String user = scan.nextLine();
      
      System.out.print("Enter Password: ");
      String pass = scan.nextLine();
      
      String UserName = r.getUsername();
      String PassWord = r.getPassword();
      
      loginStatus = user.equals(UserName) && pass.equals(PassWord);
      returnLoginStatus(loginStatus);
  }
    
    private void returnLoginStatus(boolean status){
      System.out.println("*********************************************");
       if(status){
         System.out.println("Login Sucessful");
         //Calls Options Method if the login is successful
         loginOptions();
       }
       else{
         System.out.println("Login failed");
       }
      System.out.println("*********************************************");
    }
    
    private void loginOptions(){
      Message m = new Message();
      boolean exit = false;
      String input;
      int choice;
      
      while(!exit) {
         
           input = JOptionPane.showInputDialog(null, 
           "Select an option by typing the number next to your choice:\nOption 1. Send Messages\nOption 2. Show recent message\nOption 3. Quit", 
           "Menu", 
           JOptionPane.PLAIN_MESSAGE);
           
        if(input == null){
         JOptionPane.showMessageDialog(null,"Exiting");
         return;
        }
        
        try{
            choice = Integer.parseInt(input);
           
      switch(choice){
        case 1:
           m.addMessage();
           break;
        case 2:
            JOptionPane.showMessageDialog(null,"Coming Soon");
            break;
        case 3:
            JOptionPane.showMessageDialog(null,"GoodBye");
            exit = true;
            break;
        default:
           JOptionPane.showMessageDialog(null, "Enter correct option. ","Error",JOptionPane.ERROR_MESSAGE);
            }
        }catch (NumberFormatException e) {
         JOptionPane.showMessageDialog(null, "That's not a valid number. ","Error",JOptionPane.ERROR_MESSAGE);
       }
      }
      
     }
   }
