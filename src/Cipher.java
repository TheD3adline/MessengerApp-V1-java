public class Cipher {

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public static String cipherMessage(String MSG, int offset) {

        MSG = MSG.toLowerCase();

        StringBuilder encodedMSG = new StringBuilder();

        for(int i = 0; i < MSG.length(); i++) {

            if((MSG.charAt(i) >= 97) && (MSG.charAt(i) <= 122)) {
                int charPos = alphabet.indexOf(MSG.charAt(i));

                int key = (offset + charPos) % 26;

                char rep = alphabet.charAt(key);

                encodedMSG.append(rep);
            } else
                encodedMSG.append(MSG.charAt(i));
        }
        return encodedMSG.toString();
    }

    public static String decipherMessage(String MSG, int offset) {

        MSG = MSG.toLowerCase();

        StringBuilder decodedMSG = new StringBuilder();

        for(int i = 0; i < MSG.length(); i++) {

            if((MSG.charAt(i) >= 97) && (MSG.charAt(i) <= 122)) {
                int charPos = alphabet.indexOf(MSG.charAt(i));

                int key = (charPos - offset) % 26;

                if(key < 0)
                    key = alphabet.length() + key;

                char rep = alphabet.charAt(key);

                decodedMSG.append(rep);
            } else
                decodedMSG.append(MSG.charAt(i));
        }
        return decodedMSG.toString();
    }
}
