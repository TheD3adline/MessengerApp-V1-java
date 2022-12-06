/*
    Author: Michael Fessler
    Date: 2022/11/8
    Version: 0.1
    Description:
            Stores and feeds the message data through the cipher.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    private User sender;
    private User recipient;
    private String message;
    private String path;

    /**
     * Constructor for newly written messages.
     * @param sender user who sent the message
     * @param recipient user to receive the message
     * @param message the actual message String
     * @param offset the offset to be used on the cipher for encryption.
     */
    public Message(User sender, User recipient, String message, int offset) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        //creates file name from recipient name and date/timestamp and writes them into path variable
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        String timestamp = format.format(date);
        this.path  = "cache\\mail_" + recipient.getUserName().toUpperCase() + "_" + timestamp + ".txt";
        //writes the message to file in cache folder with applied encryption.
        if(WriteFiles.createFile(this.path)) {
            if(WriteFiles.writeDataToFile(this.path, Cipher.cipherMessage(message, offset)  + "[" + sender.getUserName() + "][" + recipient.getUserName() + "]"))
                System.out.println("Message to " + recipient.getUserName() + " sent successfully.");
            else {
                System.out.println("Unknown Error during message delivery.");
                WriteFiles.deleteFile(this.path);
            }
        }
    }

    /**
     * Constructor for messages to be loaded from cache folder into inbox
     * @param sender user who sent the message
     * @param recipient user to receive the message
     * @param message the actual message String
     * @param path given path where file is stored
     */
    public Message(User sender, User recipient, String message, String path) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
