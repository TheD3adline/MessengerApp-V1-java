public class Cipher {

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public static String cipherMessage(String MSG, int offset) {

        MSG = MSG.toLowerCase();

        StringBuilder encodedMSG = new StringBuilder();

        for(int i = 0; i < MSG.length(); i++) {

            int charPos = alphabet.indexOf(MSG.charAt(i));

            int key = (offset + charPos) % 26;

            char rep = alphabet.charAt(key);

            encodedMSG.append(rep);
        }

        return encodedMSG.toString();
    }

    public static String decipherMessage(String MSG, int offset) {

        MSG = MSG.toLowerCase();

        StringBuilder decodedMSG = new StringBuilder();

        for(int i = 0; i < MSG.length(); i++) {

            int charPos = alphabet.indexOf(MSG.charAt(i));

            int key = (charPos - offset) % 26;

            if(key < 0)
                key = alphabet.length() + key;

            char rep = alphabet.charAt(key);

            decodedMSG.append(rep);
        }

        return decodedMSG.toString();
    }
}
