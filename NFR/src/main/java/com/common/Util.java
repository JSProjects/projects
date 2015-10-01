package com.common;

import java.util.Random;

public class Util {
	private static Random randGen;
	private static char[] numbersAndLetters;
	private static final Object initLock = new Object();

	public static String randomString(int length) {
		 if (length < 1) {
			 return null;
		 }
		 if (randGen == null) {
			 synchronized (initLock) {
				 if (randGen == null) {
					 randGen = new Random();
					 numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
				 }
			 }
		 }
		 char [] randBuffer = new char[length];
		 for (int i=0; i<randBuffer.length; i++) {
			 randBuffer[i] = numbersAndLetters[randGen.nextInt(numbersAndLetters.length)];
		 }
		 return new String(randBuffer);
	}
}
