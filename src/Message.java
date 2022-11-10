public class Message {

    private User sender;
    private User recipient;
    private String message;

    public Message(User sender, User recipient, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = Cipher.cipherMessage(message, 4);
    }
}
