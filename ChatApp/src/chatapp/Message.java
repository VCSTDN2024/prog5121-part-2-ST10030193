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
    
       
     public void addMessage() {
    int numMessage = getMessageNumber();
    String recipient = checkRecipientCell();

    String[] messages = new String[numMessage];
    String[] messageHash = new String[numMessage];
    Long[] messageID = new Long[numMessage];
    boolean[] sentFlags = new boolean[numMessage];  // Track sent status

    for (int i = 0; i < numMessage; i++) {
        messages[i] = getMessage();
        messageID[i] = ThreadLocalRandom.current().nextLong(1_000_000_000L, 10_000_000_000L);
        messageHash[i] = createMessageHash(messageID[i], i, messages[i]);

        if (!checkMessageID(messageID[i])) {
            JOptionPane.showMessageDialog(null, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int action = sentMessages(messages[i]);
        switch (action) {
            case 1: // Sent
                sentFlags[i] = true;
                break;
            case 2: // Disregard
                messages[i] = null;
                messageID[i] = null;
                messageHash[i] = null;
                break;
            case 3: // Store
                storeMessage(messages[i]);
                break;
            case -1: // User canceled
                JOptionPane.showMessageDialog(null, "Exiting message input");
                return;
            default:
                // Shouldn't happen
        }
    }
    
    int totalSent = returnTotalMessages(sentFlags);
    JOptionPane.showMessageDialog(null, "Total messages sent: " + totalSent);
    printMessages(messageID, messageHash, recipient, messages);
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
     
     private int sentMessages(String message) {
    String optionInput = JOptionPane.showInputDialog(
        "Message Menu\n" +
        "Option 1: Send Message\n" +
        "Option 2: Disregard Message\n" +
        "Option 3: Store Message to send later"
    );

    if (optionInput == null) {
        return -1;  // User canceled
    }

    try {
        int option = Integer.parseInt(optionInput);
        switch (option) {
            case 1:
                JOptionPane.showMessageDialog(null, "Message sent");
                return 1;
            case 2:
                int reply = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to disregard the message?",
                    "Disregard?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Message disregarded");
                    return 2;
                } else {
                    JOptionPane.showMessageDialog(null, "Message kept");
                    return 1;  // Treat as sent if they keep it
                }
            case 3:
                storeMessage(message);
                return 3;
            default:
                JOptionPane.showMessageDialog(null, "Invalid option");
                return sentMessages(message);  // Retry
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Please enter a valid number");
        return sentMessages(message);  // Retry
    }
}
     
     private void storeMessage(String message) {
        JOptionPane.showMessageDialog(null,"Messages stored for later usage");
    }
     
     private void printMessages(Long[] messageID, String[] messageHash, String recipient, String[] messages) {
           StringBuilder output = new StringBuilder("=== Messages Summary ===\n\n");
            output.append("Recipient: ").append(recipient).append("\n\n");
    
    for (int i = 0; i < messages.length; i++) {
        if (messages[i] != null && !messages[i].isBlank()) {
            output.append("Message #").append(i + 1).append("\n");
            output.append("ID: ").append(messageID[i]).append("\n");
            output.append("Hash: ").append(messageHash[i]).append("\n");
            output.append("Content: ").append(messages[i]).append("\n");
            output.append("-------------------------\n");
        }
    }
    
    JOptionPane.showMessageDialog(null, output.toString(), "Messages Summary", JOptionPane.INFORMATION_MESSAGE);
    }
     
     private int returnTotalMessages(boolean[] sentFlags) {
    int count = 0;
    for (boolean sent : sentFlags) {
        if (sent) count++;
    }
    return count;
  }
}


