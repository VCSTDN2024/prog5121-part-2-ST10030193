package chatapp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Owethu
 */
public class Register {
    Scanner scan = new Scanner(System.in);
    
    private String  userName;
    private String passWord ;
    private String cellPhone;
    
    //Getters that give other classes the right to only view the data
    public String getUsername(){
    return userName;
    }
    
    public void setUsername(String user){this.userName = user;}
    
    public String getPassword(){
    return passWord;
    }
    
    public void setPassword(String pass){this.passWord = pass;}
    
    public String getCellPhone(){
    return cellPhone;
    }
    
    public void setCellphone(String cell){this.cellPhone= cell;}
    
    //Validates Username returns a boolean
    private boolean checkUserName(String username) {
    if (username.length() <= 5 && username.contains("_")) {
        this.userName = username; 
        return true;
    } else {
        return false;
    }
}
    
    //OpenAI. (2025). ChatGPT [Large language model]. Available at: https://chat.openai.com/ (Accessed: 20 April 2025).
    private boolean checkCellPhoneNumber(String phoneNumber) {
    // Starts with +27, followed by 6, 7, or 8, then 8 digits
    String regex = "^\\+27[678]\\d{8}$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(phoneNumber);
    return matcher.matches();
}

      //Validates passwords  Check it out
    private boolean checkPasswordComplexity(String password) {
            String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\\$%\\^&\\*]).{8,}$";
            return password.matches(regex);
        }
    
    //returns register status
    public boolean registerUser(){
    //Validates Registration
      boolean registerStatus = true;
      String user;
      String pass;
      String cell;
        
    System.out.println("*********************************************");
    System.out.println("REGISTER");
    System.out.println("*********************************************");
    
    //prompt for username
      System.out.println("Enter Username: (Username MUST be LESS than 5 characters and MUST contain an underscore)");
      user = scan.nextLine();
      setUsername(user);
      System.out.println();
      
      
     //Prompt for password
      System.out.println("Enter Password: (Password must be at least 8 chracters long , MUST contain a Capital letter, a Number and a Special character)");
      pass = scan.nextLine();
      setPassword(pass);
      System.out.println();
      
      
      //Prompt for cellPhone
      System.out.println("Enter Cellphone: ");
      cell = scan.nextLine();
      setCellphone(cell);
      System.out.println();
    

    System.out.println("*********************************************");
    if (checkUserName(user)) {
        System.out.println("Username successfully captured.");
    } 
    else {
        System.out.println("Username is incorrectly formatted. It should contain an underscore and be no more than 5 characters long.");
        registerStatus  = false;
    }

    if (checkPasswordComplexity(pass)) {
        System.out.println("Password successfully captured.");
    } 
    else {
        System.out.println("Password is incorrectly formatted. It should be at least 8 characters long, with a capital letter, number, and special character.");
        registerStatus  = false;
    }

    if (checkCellPhoneNumber(cell)) {
        System.out.println("Cell phone number successfully added.");
    } 
    else {
        System.out.println("Cell phone number is incorrectly formatted or missing an international code.");
        registerStatus = false;
    }
    System.out.println();
    
    //Returns register Status then returns it to the main class
    System.out.println("*********************************************");
    System.out.println("Registration Status:");
    if (registerStatus) {
        System.out.println("Registration successful!");
    }
    else {
        System.out.println("Registration failed. Please correct the above errors and try again.");
        
    }
    System.out.println("*********************************************\n");
    return registerStatus;
     
  }
    
    
}
