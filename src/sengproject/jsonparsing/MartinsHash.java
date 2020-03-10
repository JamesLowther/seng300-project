package sengproject.jsonparsing;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class MartinsHash {
	private static byte[] getSHA(String password, int uid) throws NoSuchAlgorithmException{
		String toHash = password.concat(Integer.toString(uid));
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		return md.digest(toHash.getBytes(StandardCharsets.UTF_8));
	}
	private static String toHexString(byte[] hash) {
		BigInteger number = new BigInteger(1, hash);
		StringBuilder hexString = new StringBuilder(number.toString(16));
		while(hexString.length()<32) {
			hexString.insert(0, '0');
		}
		return hexString.toString();
	}
	public static String saltAndHash(String password, int uid) {
		try {
			return toHexString(getSHA(password,uid));
		}catch(NoSuchAlgorithmException e) {
			return e.getMessage();
		}
	}
}
