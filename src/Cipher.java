public class Cipher {

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public static String cipherMessage(String MSG, int offset) {

        MSG = MSG.toLowerCase();

        String encodedMSG = "";

        for(int i = 0; i < MSG.length(); i++) {

            int charPos = alphabet.indexOf(MSG.charAt(i));

            int key = (offset + charPos) % 26;

            char rep = alphabet.charAt(key);

            encodedMSG += rep;
        }

        return encodedMSG;
    }

    public static String decipherMessage(String MSG, int offset) {

        MSG = MSG.toLowerCase();

        String decodedMSG = "";

        for(int i = 0; i < MSG.length(); i++) {

            int charPos = alphabet.indexOf(MSG.charAt(i));

            int key = (charPos - offset) % 26;

            if(key < 0)
                key = alphabet.length() + key;


            char rep = alphabet.charAt(key);

            decodedMSG += rep;
        }

        return decodedMSG;
    }
}
