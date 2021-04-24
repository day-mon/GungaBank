package sample.util.operations;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringOperations
{

    /**
     * Uses SHA256 to hash a password
     * <p>
     * An attempt at security in a horribly unsecured program.
     *
     * @param password
     * @return
     */
    public static String hashPassword(String password)
    {
        String generatedPassword = "";

        try
        {
            MessageDigest SHA256 = MessageDigest.getInstance("SHA-256");
            SHA256.update(password.getBytes());
            byte[] bytes = SHA256.digest();

            StringBuilder hash = new StringBuilder();

            for (byte aByte : bytes)
            {
                hash.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = hash.toString();

        }
        catch (NoSuchAlgorithmException e)
        {
            e.getLocalizedMessage();
        }

        return generatedPassword;
    }
}
