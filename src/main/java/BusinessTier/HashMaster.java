package BusinessTier;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class HashMaster {
	
	public static void main(String[] args) {
		String salt = salt();
		//System.out.println(hash(new StringBuilder(salt).append("test").toString()));
		//System.out.println(hash(new StringBuilder("tPL+/JAKJH5M0lLmeiibqbxd5/+sGwJQ").append("test").toString()));
		System.out.println(hash("test", "tPL+/JAKJH5M0lLmeiibqbxd5/+sGwJQXkMlpm9LxDs="));
		System.out.println(salt);
	}

	public static String hash(String pt, String salt) {
		try {

			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			return (new HexBinaryAdapter()).marshal(digest.digest(new StringBuilder(salt).append(pt).toString().getBytes()));
			//return (new HexBinaryAdapter()).marshal(digest.digest(pt.getBytes()));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String salt() {
		final Random r = new SecureRandom();
		byte[] salt = new byte[32];
		r.nextBytes(salt);
		return new String(Base64.getEncoder().encode(salt));
	}

}
