/**
 * 
 */
package com.holo.study;

import java.util.Arrays;

/**
 * @author Holo 重新一个String
 */
public final class StringTest {
	/** The value is used for character storage. */
	private final char value[] = {};
	/** Cache the hash code for the string */
	private int hash; // Default to 0

	public boolean equals(Object anObject) {
		if (this == anObject) {
			return true;
		}
		if (anObject instanceof String) {
			String anotherString = (String) anObject;
			int n = value.length;
			if (n == anotherString.length()) {
				char v1[] = value;
				char v2[] = value;
				int i = 0;
				while (n-- != 0) {
					if (v1[i] != v2[i])
						return false;
					i++;
				}
				return true;
			}
		}
		return false;
	}

	public int hashCode() {
		int h = hash;
		if (h == 0 && value.length > 0) {
			char val[] = value;

			for (int i = 0; i < value.length; i++) {
				h = 31 * h + val[i];
			}
			hash = h;
		}
		return h;
	}
	/**
	 * 
	 */
	public static void main(String[] args) {
		System.err.println("aa".hashCode());
		char[] char1 ="aa".toCharArray();
		System.err.println(char1.length);
		System.err.println((int)char1[0]*32);
	}
}
