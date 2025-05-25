package chatapp;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author Owethu
 */
public class ChatApp {
    /**
     * @param args the command line arguments
     */
    
    static Scanner scan = new Scanner(System.in);
    static Register r = new Register();
    static Login l = new Login(r);
    
        
    public static void main(String[] args) {
       
      printWelcome();
      displayOptions();
    }
    public static void printWelcome(){
      JOptionPane.showMessageDialog(null, "********************************************\nWelcome to QuickChat\n********************************************");
    }
    
    private static void displayOptions(){
      boolean exit = false;
      boolean regStatus = false;
      int choice;
        
      while(!exit) {
      System.out.println("=============================================");
      System.out.println("What would you like to do today?");
      System.out.println("Select an option by typing the number next to your choice:");
      System.out.println("1.Register");
      System.out.println("2.Login");
      System.out.println("3.Exit");
      System.out.println("=============================================");
      
      System.out.print("User: ");
      try{
      choice = scan.nextInt();
      
      System.out.println("=============================================");
      System.out.println();
      System.out.println();
      
     
      switch(choice){
        case 1 :
           regStatus = r.registerUser();
           
           break;
        case 2:
            if(regStatus){
             l.loginUser();
            }
            else{
             System.out.println("Could NOT login your registration was not successful;");
            }
            break;
        case 3:
            System.out.println("GOODBYE");
            System.out.println("*********************************************");
            exit = true;
            break;
        default:
            System.out.println("Invalid input. Please enter a valid number");
            System.out.println("*********************************************");
            }
      } catch (Exception e) {
         System.out.println("That's not a valid number. Try agian");
       }
      scan.nextLine();
    }
   }
}
