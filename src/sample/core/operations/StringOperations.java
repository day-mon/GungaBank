package sample.core.operations;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringOperations {

    public static String hashPassword(String password) {
        String generatedPassword = "";

        try {
            MessageDigest SHA256 = MessageDigest.getInstance("SHA-256");
            SHA256.update(password.getBytes());
            byte[] bytes = SHA256.digest();

            StringBuilder hash = new StringBuilder();

            for(int i=0; i< bytes.length ;i++)
            {
                hash.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = hash.toString();

        } catch (NoSuchAlgorithmException e) {
            e.getLocalizedMessage();
        }

        return generatedPassword;
    }
}
