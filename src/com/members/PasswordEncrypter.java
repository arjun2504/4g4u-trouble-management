package com.members;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncrypter {
	public static String encrypt(String plainPassword) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] passwordDigest = md.digest(plainPassword.getBytes());
		BigInteger number = new BigInteger(1, passwordDigest);
		String encryptedPassword = number.toString(16);
		return encryptedPassword;
	}
}
