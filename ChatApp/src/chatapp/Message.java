package chatapp;

import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Owethu
 */
public class Message {
    
       
     public void addMessage(){
         
        int numMessage = getMessageNumber();
         String recipient = checkRecipientCell();
         String[] messages = new String[numMessage];
         
        for(int i = 0; i < messages.length; i++){
            //assigns message
           messages[i] = getMessage();
        
           long messageID = ThreadLocalRandom.current().nextLong(1_000_000_000L, 10_000_000_000L);
            String messageHash = createMessageHash(messageID,i,messages[i]);
         
           if(!checkMessageID(messageID)){
            JOptionPane.showMessageDialog(null, "An error occured","Error",JOptionPane.ERROR_MESSAGE);
             }
           SentMessages(messages[i]);
        }
        printMessages(messages);
    }
    
     private int getMessageNumber(){
        boolean valid = false;
        String input;
        int nMessage = 0;
       
        while(!valid){
        //prompt that returns a string
         input = JOptionPane.showInputDialog("Enter the total number of messages you would like to send:");
        
        //Checks if String is empty
        if(input == null){
         JOptionPane.showMessageDialog(null,"Exiting");
         return -1;
        }
        
        try{
         //Converts string to int
         nMessage = Integer.parseInt(input);
        
        //Checks if int is empty
        if(nMessage == 0){
         JOptionPane.showMessageDialog(null,"Exiting");
         return -1;
        }
        
        //If everything looks good the valid will be true then return
        valid = true;
        
       } catch (NumberFormatException e) {
         JOptionPane.showMessageDialog(null, "That's not a valid number. ","Error",JOptionPane.ERROR_MESSAGE);
       }
       
     }
        return nMessage;
   }
     
     private String checkRecipientCell(){
      boolean numValid = false;
      String recipient = "";
      
     while(!numValid){
       recipient = JOptionPane.showInputDialog(null,"Enter Number of the recipient");
       
       //Checks to see if Recipient cell number is null 
       if(recipient == null){
       return null;
       }
       
       String regex = "^\\+27[678]\\d{8}$";
       Pattern pattern = Pattern.compile(regex);
       Matcher matcher = pattern.matcher(recipient);
       
       //Validates format
       if(matcher.matches()){
         numValid = true;
       }
       else{
         JOptionPane.showMessageDialog(null,"Cell phone number is incorrectly formatted or missing an international code.", "ERROR", JOptionPane.ERROR_MESSAGE);
       }
     }
     return recipient;
    }
     
     private String getMessage(){
       boolean Valid = false;
       String messAge = "";
       
       while(!Valid){
          messAge = JOptionPane.showInputDialog(null,"Enter your message:\n Should be less than 50 characters","Message",JOptionPane.PLAIN_MESSAGE);
       
          if(messAge == null){
            JOptionPane.showMessageDialog(null,"Exiting");
            return null;
            }
       
          if(messAge.length() <= 250){
            Valid = true;
            }
          else{
             JOptionPane.showMessageDialog(null,"Please enter a message of less than 50 characters");
         }
       }
       
       return messAge;
   }
     
     private boolean checkMessageID(long num){
       return num >= 1_000_000_000L && num <= 9_999_999_999L;
    }
     
     private String createMessageHash(long messageId, int messageNumber, String message) {
        // Extract first 2 digits of the message ID
        String messageIdStr = String.valueOf(messageId);
        String firstTwoDigits = messageIdStr.length() >= 2 ? messageIdStr.substring(0, 2) : messageIdStr;

        // Get first and last words
        String trimmedMessage = message.trim();
        String[] words = trimmedMessage.split("\\s+");

        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;

        // Return the formatted hash
        return firstTwoDigits + ":" + messageNumber + ":" + firstWord + " " + lastWord;
    }
     
     private void SentMessages(String message){
     int opt;
     String option;
     
     option = JOptionPane.showInputDialog("Message Menu\n Option 1: Send Message\n Option 2: Disregard Message\n Option 3: Store Message to send later");
     
     if(option == null){
       JOptionPane.showMessageDialog(null,"Exiting");
       return;
     }
     try{
     opt = Integer.parseInt(option);
     
     if(opt == 0){
       JOptionPane.showMessageDialog(null,"Exiting");
       return;
     }
     
     switch(opt){
         case 1:
              JOptionPane.showMessageDialog(null,"Message sent");
             break;
         case 2:
             int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to diregard the message?", "Disregard ?", JOptionPane.YES_NO_OPTION);
             if (reply == JOptionPane.YES_OPTION) {
                   message = null;
                  JOptionPane.showMessageDialog(null, "Deleted");
             } else {
             JOptionPane.showMessageDialog(null, "Mesaage intacked");
             }
             break;
         case 3: 
             //Stores the messages in a JSON File
             storeMessage(message);
             break;
         default:
          JOptionPane.showMessageDialog(null, "Error: Enter Correct Option" );
            }
  
    } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Please Enter Correct Option .","Error",JOptionPane.ERROR_MESSAGE);
         }
      }
     
     private void storeMessage(String message) {
        JOptionPane.showMessageDialog(null,"Messages stored for later usage");
    }
     
     private void printMessages(String[] messages){
      StringBuilder messageToDisplay = new StringBuilder();

        for(String message : messages) {
            messageToDisplay.append(message);
            messageToDisplay.append("\n"); // Add a newline after each message
        }

        // Display the message using JOptionPane
        JOptionPane.showMessageDialog(null, messageToDisplay.toString(), "Messages", JOptionPane.INFORMATION_MESSAGE);
       }
     
     private void returnTotalMessages(){}
     }


