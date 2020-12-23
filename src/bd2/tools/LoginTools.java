package bd2.tools;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class LoginTools {
    public static String charToSha256(char[] hash) throws NoSuchAlgorithmException {
        byte[] res = new byte[hash.length];
        for (int i = 0; i < hash.length; i++) {
            res[i] = (byte) hash[i];
        }

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(res);
        byte[] digest = md.digest();

        return String.format("%064x", new BigInteger(1, digest));
    }

    public static String randomString(int stringLength) {
        String candidates = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < stringLength) {
            int index = (int) (rnd.nextFloat() * candidates.length());
            salt.append(candidates.charAt(index));
        }
        return salt.toString();
    }

    public static String randomPassword(int stringLength) throws NoSuchAlgorithmException {
        var pass = randomString(stringLength);
        return charToSha256(pass.toCharArray());
    }
}
