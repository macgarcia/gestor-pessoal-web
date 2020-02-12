package com.macgarcia.gpweb;

public class Main {
	
	public static void main(String[] args) {
		String a = "image/png";
		int b = a.indexOf("/");
		String c = a.substring(b + 1);
		System.out.println(c);
	}

}
