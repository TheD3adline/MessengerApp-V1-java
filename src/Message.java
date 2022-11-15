import java.io.File;

public class Message {

    private User sender;
    private User recipient;
    private String message;

    public Message(User sender, User recipient, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        if(WriteFiles.createFile("cache\\mail" + recipient.getUserName().toUpperCase() + ".txt")) {
            if(WriteFiles.writeDataToFile("cache\\mail" + recipient.getUserName().toUpperCase() + ".txt", Cipher.cipherMessage(message, 4)))
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
            this.message = Cipher.decipherMessage(ReadFiles.readFileToString(msgFile), 4);
            System.out.println(message);
            //WriteFiles.deleteFile(path);
        }
    }
}
