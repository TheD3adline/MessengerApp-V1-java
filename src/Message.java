/*
    Author: Michael Fessler
    Date: 2022/11/8
    Version: 0.1
    Description:
            Stores and feeds the message data through the cipher, and also to decipher them again.
 */

import java.io.File;

public class Message {

    private User sender;
    private User recipient;
    private String message;

    public Message(User sender, User recipient, String message, int offset) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        if(WriteFiles.createFile("cache\\mail" + recipient.getUserName().toUpperCase() + ".txt")) {
            if(WriteFiles.writeDataToFile("cache\\mail" + recipient.getUserName().toUpperCase() + ".txt", Cipher.cipherMessage(message, offset)))
                System.out.println("Message to " + recipient.getUserName() + " sent successfully.");
            else {
                System.out.println("Unknown Error during message delivery.");
                WriteFiles.deleteFile("cache\\mail" + recipient.getUserName().toUpperCase() + ".txt");
            }
        }
    }

    public Message(String path) {
        File msgFile = new File(path);
        if(ReadFiles.getFileInfo(msgFile)) {
            this.message = Cipher.decipherMessage(ReadFiles.readFileToString(msgFile));
            System.out.println(message);
            //WriteFiles.deleteFile(path);
        }
    }
}
