/*
    Author: Michael Fessler
    Date: 2022/11/8
    Version: 0.1
    Description:
            The cipher to encode and decode the messages.
 */

public class Cipher {

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * to encode messages from given String.
     * @param MSG given String
     * @param offset offset to change the characters against the alphabet
     * @return the encrypted message as String
     */
    public static String cipherMessage(String MSG, int offset) {

        MSG = MSG.replace("[", "(");
        MSG = MSG.replace("]", ")");

        StringBuilder encodedMSG = new StringBuilder();

        //loops through every character of the String
        for(int i = 0; i < MSG.length(); i++) {

            //checks if current character is of valid data, the letters from the alphabet via ascii code.
            if(((MSG.charAt(i) >= 97) && (MSG.charAt(i) <= 122)) || ((MSG.charAt(i) >= 65) && (MSG.charAt(i) <= 90))) {

                //checks if current character is uppercase to apply capital letters.
                if(Character.isUpperCase(MSG.charAt(i))) {
                    int charPos = ALPHABET.indexOf(MSG.charAt(i)); //saves alphabet position of current MSG character as int

                    //creates a "key" by adding the offset to charPos, % 26, so it can't be higher than 26 and switches
                    //over to the letter a after having reached z.
                    int key = (offset + charPos) % 26;

                    //saves the current encoded character by applying the key integer to the alphabet
                    char rep = ALPHABET.charAt(key);

                    //StringBuilder appends the encoded character to the overall message that is to be returned
                    encodedMSG.append(rep);
                } else {
                    //same as above, just with non-capital letters
                    int charPos = alphabet.indexOf(MSG.charAt(i));

                    int key = (offset + charPos) % 26;

                    char rep = alphabet.charAt(key);

                    encodedMSG.append(rep);
                }
            } else
                //if current character is not part of the alphabet, like numbers or special characters or a space
                //appends them without encryption.
                encodedMSG.append(MSG.charAt(i));
        }
        return encodedMSG.toString();
    }

    /**
     * to decrypt the messages, functions the same as the encryption method, just the other way around the alphabet.
     * @param MSG given message
     * @param offset to switch the characters against the alphabet
     * @return decrypted message
     */
    public static String decipherMessage(String MSG, int offset) {

        StringBuilder decodedMSG = new StringBuilder();

        for(int i = 0; i < MSG.length(); i++) {

            if(((MSG.charAt(i) >= 97) && (MSG.charAt(i) <= 122)) || ((MSG.charAt(i) >= 65) && (MSG.charAt(i) <= 90))) {

                if(Character.isUpperCase(MSG.charAt(i))) {
                    int charPos = ALPHABET.indexOf(MSG.charAt(i));

                    //the only real difference to the encryption method is the way the key is calculated and applied
                    int key = (charPos - offset) % 26;

                    if(key < 0)
                        key = ALPHABET.length() + key;

                    char rep = ALPHABET.charAt(key);

                    decodedMSG.append(rep);
                } else {
                    int charPos = alphabet.indexOf(MSG.charAt(i));

                    int key = (charPos - offset) % 26;

                    if(key < 0)
                        key = alphabet.length() + key;

                    char rep = alphabet.charAt(key);

                    decodedMSG.append(rep);
                }
            } else
                decodedMSG.append(MSG.charAt(i));
        }
        return decodedMSG.toString();
    }
}
