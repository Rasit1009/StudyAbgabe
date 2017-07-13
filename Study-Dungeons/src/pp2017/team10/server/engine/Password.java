package pp2017.team10.server.engine;

import java.math.BigInteger;
import java.security.*;

/*
 * Klasse um das Passwort des Users zu salten und zu hashen.
 * 
 * */
/**
 * Author: Felix Schifferdecker, 5585147
 */

public class Password {
	
	private String salt = "StudyDungeons";
	
	//Funktion zum hashen (teilweise aus dem Internet �bernommen). Gibt das gehashte Passwort zur�ck
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
		while(hashtext.length() < 32 ){
		  hashtext = "0" + hashtext;
		}
		return hashtext;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
