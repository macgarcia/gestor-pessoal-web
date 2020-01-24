package com.macgarcia.gpweb.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Ferramentas {
	
	public static String md5(String palavra) {
		String s = palavra;
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(s.getBytes(), 0, s.length());
			return new BigInteger(1, m.digest()).toString(16).toLowerCase();
		} catch (NoSuchAlgorithmException ex) {
		}
		return null;
	}
}
