package pp2017.team10.server.engine;

import java.math.BigInteger;
import java.security.*;

public class Password {
	
	private String salt = "StudyDungeons";
	public String hashing(String password){
		password = password + salt;
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(password.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 ){
		  hashtext = "0" + hashtext;
		}
		return hashtext;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
