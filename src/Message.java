/*
    Author: Michael Fessler
    Date: 2022/11/8
    Version: 0.1
    Description:
            Stores and feeds the message data through the cipher, and also to decipher them again.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    private User sender;
    private User recipient;
    private String message;
    private String path;

    public Message(User sender, User recipient, String message, int offset) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String timestamp = format.format(date);
        this.path  = "cache\\mail_" + recipient.getUserName().toUpperCase() + "_" + timestamp + ".txt";
        if(WriteFiles.createFile(this.path)) {
            if(WriteFiles.writeDataToFile(this.path, Cipher.cipherMessage(message, offset)  + "[" + sender.getUserName() + "][" + recipient.getUserName() + "]"))
                System.out.println("Message to " + recipient.getUserName() + " sent successfully.");
            else {
                System.out.println("Unknown Error during message delivery.");
                WriteFiles.deleteFile(this.path);
            }
        }
    }

    public Message(User sender, User recipient, String message, String path) {
        
    }
}
