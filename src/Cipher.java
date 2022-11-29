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

    public static String cipherMessage(String MSG, int offset) {

        MSG = MSG.replace("[", "(");
        MSG = MSG.replace("]", ")");

        StringBuilder encodedMSG = new StringBuilder();

        for(int i = 0; i < MSG.length(); i++) {

            if(((MSG.charAt(i) >= 97) && (MSG.charAt(i) <= 122)) || ((MSG.charAt(i) >= 65) && (MSG.charAt(i) <= 90))) {

                if(Character.isUpperCase(MSG.charAt(i))) {
                    int charPos = ALPHABET.indexOf(MSG.charAt(i));

                    int key = (offset + charPos) % 26;

                    char rep = ALPHABET.charAt(key);

                    encodedMSG.append(rep);
                } else {
                    int charPos = alphabet.indexOf(MSG.charAt(i));

                    int key = (offset + charPos) % 26;

                    char rep = alphabet.charAt(key);

                    encodedMSG.append(rep);
                }
            } else
                encodedMSG.append(MSG.charAt(i));
        }
        return encodedMSG.toString();
    }

    public static String decipherMessage(String MSG, int offset) {

        StringBuilder decodedMSG = new StringBuilder();

        for(int i = 0; i < MSG.length(); i++) {

            if(((MSG.charAt(i) >= 97) && (MSG.charAt(i) <= 122)) || ((MSG.charAt(i) >= 65) && (MSG.charAt(i) <= 90))) {

                if(Character.isUpperCase(MSG.charAt(i))) {
                    int charPos = ALPHABET.indexOf(MSG.charAt(i));

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
