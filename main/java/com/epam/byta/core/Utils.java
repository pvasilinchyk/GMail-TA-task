package com.epam.byta.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class Utils {

	private static final String GMAIL_PROPERTIES = "gmailtest.prop";
	
	static Properties pr = new Properties();

	static {
		try {
			FileInputStream inp = new FileInputStream(GMAIL_PROPERTIES);
			pr.load(inp);
			inp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String driverType = pr.getProperty("driver");
	private static String userName1 = pr.getProperty("user1");
	private static String passwordUser1 = pr.getProperty("password1");
	private static String userName2 = pr.getProperty("user2");
	private static String passwordUser2 = pr.getProperty("password2");
	private static String userName3 = pr.getProperty("user3");
	private static String passwordUser3 = pr.getProperty("password3");
	private static String msgSubject = Utils.generateCharactersSequency();
	private static String msgText = pr.getProperty("message");
	private static String pathToAttach = pr.getProperty("pathtoattach");
	private static String pathToBigAttach = pr.getProperty("pathtobigattach");

	public static String getPathToAttach() {
		return pathToAttach;
	}

	public static String getPathToBigAttach() {
		return pathToBigAttach;
	}

	public static void setPathToBigAttach(String pathToBigAttach) {
		Utils.pathToBigAttach = pathToBigAttach;
	}

	public static void setPathToAttach(String pathToAttach) {
		Utils.pathToAttach = pathToAttach;
	}

	public static String getDriverType() {
		return driverType;
	}

	public static void setDriverType(String driverType) {
		Utils.driverType = driverType;
	}

	public static String getUserName1() {
		return userName1;
	}

	public static void setUserName1(String userName1) {
		Utils.userName1 = userName1;
	}

	public static String getPasswordUser1() {
		return passwordUser1;
	}

	public static void setPasswordUser1(String passwordUser1) {
		Utils.passwordUser1 = passwordUser1;
	}

	public static String getUserName2() {
		return userName2;
	}

	public static void setUserName2(String userName2) {
		Utils.userName2 = userName2;
	}

	public static String getPasswordUser2() {
		return passwordUser2;
	}

	public static void setPasswordUser2(String passwordUser2) {
		Utils.passwordUser2 = passwordUser2;
	}

	public static String getUserName3() {
		return userName3;
	}

	public static void setUserName3(String userName3) {
		Utils.userName3 = userName3;
	}

	public static String getPasswordUser3() {
		return passwordUser3;
	}

	public static void setPasswordUser3(String passwordUser3) {
		Utils.passwordUser3 = passwordUser3;
	}

	public static String getMsgSubject() {
		return msgSubject;
	}

	public static void setMsgSubject(String msgSubject) {
		Utils.msgSubject = msgSubject;
	}

	public static String getMsgText() {
		return msgText;
	}

	public static void setMsgText(String msgText) {
		Utils.msgText = msgText;
	}

	public static String generateCharactersSequency() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String output = sb.toString();
		return output;
	}
}
