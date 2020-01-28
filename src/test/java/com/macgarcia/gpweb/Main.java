package com.macgarcia.gpweb;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Main {
	
	public static void main(String[] args) {
		String a = "CPF%2C%201985%23%23%2C%20198509";
		String b = null;
		try {
			b = URLDecoder.decode(a, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.getMessage();
		}
		System.out.println(b);
	}

}
